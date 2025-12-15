package com.restaurant.reservationsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many reservations can be made by one customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false) // Foreign key column
    private Customer customer;

    // Many reservations can be for the same table (at different times)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private Table table;

    private LocalDateTime reservationTime; // The start time of the booking
    private int partySize;               // Number of guests
    private int durationMinutes;         // How long the table is reserved for (e.g., 90)

    @Enumerated(EnumType.STRING) // Stores the enum name as a string in the DB
    private ReservationStatus status = ReservationStatus.PENDING;

    public Reservation() {}
}