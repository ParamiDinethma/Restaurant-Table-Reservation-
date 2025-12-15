package com.restaurant.reservationsystem.dto;
import lombok.Data;

@Data
public class ReservationRequest {
    private Long tableId;
    private Long customerId;
    private String date;
    private String time;
    private int partySize;
}
