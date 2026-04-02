package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.UUID; // for generating unique IDs

public class Reservation {
    private String restaurentName;
    private String reservationDateTime;
    private int reservationSeats;
    private String customerEmail;
    private String tableID;
    private StorageHandler handler = new StorageHandler("reservations.txt");
    public Reservation(String restaurentName, LocalDateTime reservationDateTime, int reservationSeats, String customerEmail) {
        this.restaurentName = restaurentName;
        this.reservationSeats = reservationSeats;
        this.customerEmail = customerEmail;
        setReservationDateTime(reservationDateTime.toString());
        this.tableID = String.valueOf(findTable());
    }

    public Reservation(String restaurentName, String tableID){
        String[] reservationStr = handler.findLine(restaurentName,1,tableID,4);
        this.customerEmail = reservationStr[0];
        this.reservationSeats = Integer.parseInt(reservationStr[3]);
        this.reservationDateTime = reservationStr[2];
        this.restaurentName = reservationStr[1];
        this.tableID = reservationStr[4];
    }

    public void saveResrvation() {
        StorageHandler handler = new StorageHandler("reservations.txt");
        String line = customerEmail + "~" + restaurentName + "~" + reservationDateTime + "~" + reservationSeats + "~"+tableID;
        handler.saveLine(line);
        System.out.println("Reservation saved.");
    }

    public int findTable(){
        Restaurant restaurant = new Restaurant(restaurentName);
        Table[] tables = restaurant.getAllTables();

        for (Table table : tables){
            //check for booked or size
            if (!table.booked && table.size>=this.reservationSeats){
                table.update(table.size,true);
                return table.getTableID();
            }
        }

        return -99;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public void setReservationDateTime(String reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public int getReservationSeats() {
        return reservationSeats;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getReservationDateTime() {
        return reservationDateTime;
    }

    public String getRestaurentName() {
        return restaurentName;
    }

    public String getTableID() {
        return tableID;
    }
    public void delete(){
        //remove reservation with -99
        handler.deleteLine("-99",4);
        //delete the line
        System.out.println("restaurant table id to delete"+restaurentName + this.tableID);
        handler.deleteLine(this.restaurentName,1,tableID,4);
    }
}
