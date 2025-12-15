package com.restaurant.reservationsystem.controller;

import com.restaurant.reservationsystem.model.Reservation;
import com.restaurant.reservationsystem.model.Table;
import com.restaurant.reservationsystem.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurant.reservationsystem.dto.ReservationRequest;
import com.restaurant.reservationsystem.dto.PaymentRequest;
import com.restaurant.reservationsystem.dto.ReservationRequest;
import com.restaurant.reservationsystem.dto.PaymentRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/reservations") // Base URL for this controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Helper for converting string dates from URL parameters
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Endpoint 1: Checks table availability
     * GET /api/reservations/available?date=2025-12-20&time=19:00&partySize=4
     */
    @GetMapping("/available")
    public ResponseEntity<List<Table>> getAvailableTables(
            @RequestParam("date") String date,
            @RequestParam("time") String time,
            @RequestParam("partySize") int partySize) {

        try {
            // Combine date and time strings and parse them into a LocalDateTime object
            LocalDateTime requestedTime = LocalDateTime.parse(date + " " + time, FORMATTER);

            List<Table> availableTables = reservationService.findAvailableTables(requestedTime, partySize);

            if (availableTables.isEmpty()) {
                // Returns 204 No Content if no tables are available
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }

            // Returns 200 OK with the list of available tables
            return ResponseEntity.ok(availableTables);
        } catch (Exception e) {
            // Returns 400 Bad Request on parsing errors or invalid input
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    // GET /api/reservations/history/customer/{customerId}
    @GetMapping("/history/customer/{customerId}")
    public ResponseEntity<List<Reservation>> getCustomerHistory(@PathVariable Long customerId) {
        // You would need a new Service method called getHistory(customerId)
        // return ResponseEntity.ok(reservationService.getHistory(customerId));
        return ResponseEntity.ok().build(); // placeholder
    }

    /**
     * Endpoint 2: Creates a new reservation
     * POST /api/reservations
     * Body: JSON object containing booking details
     */
    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            // Assuming ReservationRequest is a simple DTO to hold incoming data (see note below)
            LocalDateTime reservationDateTime = LocalDateTime.parse(request.getDate() + " " + request.getTime(), FORMATTER);

            Reservation newReservation = reservationService.createReservation(
                    request.getTableId(),
                    request.getCustomerId(),
                    reservationDateTime,
                    request.getPartySize()
            );

            // Returns 201 Created
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            // Returns 404 Not Found if Customer or Table ID is invalid
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalStateException e) {
            // Returns 409 Conflict if the table is suddenly unavailable
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    // POST /api/reservations/{id}/pay
    @PostMapping("/{reservationId}/pay")
    public ResponseEntity<?> processPayment(@PathVariable Long reservationId, @RequestBody PaymentRequest request) {
        // This method in the Service would call the Stripe API to process the payment
        // return reservationService.processPayment(reservationId, request.getToken());
        return ResponseEntity.ok("Payment processed successfully."); // placeholder
    }

    // NOTE: You would need to create a simple DTO (Data Transfer Object) class
    // named ReservationRequest.java in a new package (e.g., .dto) to map the JSON input:
    /*
    public class ReservationRequest {
        private Long tableId;
        private Long customerId;
        private String date;
        private String time;
        private int partySize;
        // + getters/setters
    }
    */
}