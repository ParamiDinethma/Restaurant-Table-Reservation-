package com.restaurant.reservationsystem.service;

import com.restaurant.reservationsystem.model.Customer;
import com.restaurant.reservationsystem.model.Reservation;
import com.restaurant.reservationsystem.model.ReservationStatus;
import com.restaurant.reservationsystem.model.Table;
import com.restaurant.reservationsystem.repository.CustomerRepository;
import com.restaurant.reservationsystem.repository.ReservationRepository;
import com.restaurant.reservationsystem.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stripe.exception.StripeException; // Fixes Unhandled exception
import com.stripe.model.PaymentIntent; // Fixes Cannot resolve symbol 'PaymentIntent'
// ... ensure other Autowired dependencies are correctly imported

import com.stripe.model.PaymentIntent; // Fixes 'Cannot resolve symbol PaymentIntent'
import com.stripe.exception.StripeException; // Fixes 'Cannot resolve symbol StripeException' and unhandled exception
import com.stripe.Stripe; // Needed for Stripe.apiKey assignment
import com.stripe.param.PaymentIntentCreateParams; // Needed for PaymentIntent creation
// Also ensure you have the PaymentService imports:
import com.restaurant.reservationsystem.service.PaymentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private static final int DEFAULT_DURATION_MINUTES = 90; // Default time slot for a booking

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository; // Inject CustomerRepository too

    /**
     * Finds all tables available at the requested time for the given party size.
     */
    public List<Table> findAvailableTables(LocalDateTime requestedTime, int partySize) {

        // 1. Get all tables that meet the capacity requirement.
        List<Table> suitableTables = tableRepository.findByCapacityGreaterThanEqual(partySize);
        List<Long> suitableTableIds = suitableTables.stream()
                .map(Table::getId)
                .collect(Collectors.toList());

        // Handle case where no tables meet the capacity
        if (suitableTableIds.isEmpty()) {
            return List.of(); // Returns an empty list of tables
        }

        // 2. Define the reservation time window (e.g., 90 minutes).
        LocalDateTime endTime = requestedTime.plusMinutes(DEFAULT_DURATION_MINUTES);

        // 3. Find all CONFIRMED reservations that conflict with the requested time.
        // The repository handles the complex time overlap query using the MS SQL DATEADD function.
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(
                suitableTableIds, requestedTime, endTime
        );

        // 4. Identify the IDs of tables that are reserved during this slot.
        Set<Long> reservedTableIds = conflictingReservations.stream()
                .map(r -> r.getTable().getId())
                .collect(Collectors.toSet());

        // 5. Filter the suitable tables to remove those that are reserved.
        return suitableTables.stream()
                .filter(t -> !reservedTableIds.contains(t.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new reservation if a specific table is available.
     * This method assumes the controller has verified the Customer exists.
     */
    public Reservation createReservation(Long tableId, Long customerId, LocalDateTime time, int partySize) {

        // 1. Fetch Entities
        Table table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Table ID: " + tableId));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID: " + customerId));

        // 2. Perform a final double-check of availability for the chosen table
        List<Table> availableTables = findAvailableTables(time, partySize);
        if (availableTables.stream().noneMatch(t -> t.getId().equals(tableId))) {
            throw new IllegalStateException("Table ID " + tableId + " is not available at " + time);
        }

        // 3. Create and save the reservation object
        Reservation newReservation = new Reservation();
        newReservation.setCustomer(customer);
        newReservation.setTable(table);
        newReservation.setReservationTime(time);
        newReservation.setPartySize(partySize);
        newReservation.setDurationMinutes(DEFAULT_DURATION_MINUTES);
        newReservation.setStatus(ReservationStatus.CONFIRMED);

        return reservationRepository.save(newReservation);
    }

    /**
     * Retrieves all reservations for a specific customer.
     */
    public List<Reservation> getCustomerReservationHistory(Long customerId) {
        // We will add a custom finder method to ReservationRepository in the next step to support this easily.
        // For now, let's use a simpler pattern:
        return reservationRepository.findAll().stream()
                .filter(r -> r.getCustomer().getId().equals(customerId))
                .collect(Collectors.toList());
    }
    @Autowired
    private PaymentService paymentService;

    public Reservation finalizeBookingWithPayment(Long reservationId, String paymentToken) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found."));

        // 1. Calculate the deposit amount (e.g., $10.00 = 1000 cents)
        Long depositAmountCents = 1000L;

        try {
            // 2. Process the payment using the token received from the client
            PaymentIntent intent = paymentService.createPaymentIntent(
                    depositAmountCents,
                    "usd",
                    paymentToken,
                    "Deposit for Reservation #" + reservationId
            );

            // 3. Check if payment was successful (status == 'succeeded' or 'requires_capture')
            if ("succeeded".equals(intent.getStatus())) {
                // 4. Update reservation status and save the Stripe ID
                reservation.setStatus(ReservationStatus.CONFIRMED);
                // You should add a field like 'stripeId' to your Reservation model
                // reservation.setStripeId(intent.getId());
                return reservationRepository.save(reservation);
            } else {
                throw new IllegalStateException("Payment failed or requires further action. Status: " + intent.getStatus());
            }

        } catch (StripeException e) {
            // Log the error and throw an exception
            throw new RuntimeException("Stripe payment error: " + e.getMessage(), e);
        }
    }

}