package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

import java.time.LocalDateTime;

public class Customer extends User {

    public Customer(String displayName, String password, String email) {
        super(displayName, password, email);
        this.accountType = 1;
    }

    public void save() {
        StorageHandler handler = new StorageHandler("users.txt");
        String line = email + "~" + displayName + "~" + password + "~1";
        handler.saveLine(line);
    }
    public void update(String displayName, String password, String email){
        StorageHandler handler = new StorageHandler("users.txt");
        String updateLine = email + "~" + displayName + "~" + password + "~1";
        handler.updateLine(this.email,0,updateLine);
    }
    // Add a reservation (auto-generates ID)
    public void addReservation(String restaurantName, LocalDateTime dateTime, int seats) {
        Reservation reservation = new Reservation(restaurantName, dateTime, seats, this.email);
        reservation.saveResrvation();
    }

    // Update by reservation ID
    public void updateReservation(String reservationID, String newRestaurantName, LocalDateTime newDateTime, int newSeats) {
        StorageHandler handler = new StorageHandler("reservations.txt");
        String[][] all = handler.readAllLines();

        for (String[] res : all) {
            if (res.length >= 5 && res[0].equals(reservationID)) {
                String newLine = reservationID + "~" + email + "~" + newRestaurantName + "~" + newDateTime + "~" + newSeats + "~";
                handler.updateLine(reservationID, 0, newLine);
                System.out.println("Reservation updated.");
                return;
            }
        }
        System.out.println("Reservation ID not found.");
    }

    // Delete by reservation ID
    public void deleteReservation(String reservationID) {
        StorageHandler handler = new StorageHandler("reservations.txt");
        handler.deleteLine(reservationID, 0);
        System.out.println("Reservation deleted.");
    }

    public Reservation[] getAllReservations() {
        StorageHandler handler2 = new StorageHandler("reservations.txt");
        String[][] reservationsStr = handler2.getAllLines(this.email,0);
        System.out.println(reservationsStr.length+",,");
        Reservation[] reservations = new Reservation[reservationsStr.length];
        for (int i = 0; i < reservationsStr.length; i++) {
            String[] reservationTemp = reservationsStr[i];
            Reservation reservationObj = new Reservation(reservationTemp[1],LocalDateTime.parse(reservationTemp[2]),Integer.parseInt(reservationTemp[3]),this.email);
            reservationObj.setTableID(reservationTemp[4]);
            reservations[i]=reservationObj;
        }
        return reservations;
    }

    // Get all reservations for this customer

}
