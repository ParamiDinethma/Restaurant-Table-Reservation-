package com.restaurant.reservationsystem.repository;

import com.restaurant.reservationsystem.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Finds confirmed reservations that conflict with a requested time slot.
     * The conflict logic checks for reservations where:
     * 1. The existing reservation's start time is BEFORE the requested end time.
     * 2. The existing reservation's END time (start + duration) is AFTER the requested start time.
     */
    @Query(value = "SELECT r FROM Reservation r " +
            "WHERE r.status = 'CONFIRMED' AND " +
            "r.table.id IN :tableIds AND " +
            "r.reservationTime < :endTime AND " +
            "FUNCTION('DATEADD', 'MINUTE', r.durationMinutes, r.reservationTime) > :startTime")
    List<Reservation> findConflictingReservations(
            @Param("tableIds") List<Long> tableIds,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
    List<Reservation> findByCustomerIdOrderByReservationTimeDesc(Long customerId);
}
