package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

public class Table {
    private int tableID;
    private String restaurant;

    public int size;
    public boolean booked;
    private StorageHandler handler = new StorageHandler("tables.txt");

    // Constructor for creating a new table (auto-generates UUID)
    public Table(int tableID, String restaurant, int size, boolean booked) {
        this.tableID = tableID;
        this.restaurant = restaurant;
        this.size = size;
        this.booked = booked;
    }

    //File load constructor
    public Table(int tableID, String restaurant) {
        this.tableID = tableID;
        this.restaurant = restaurant;
        String[] data = handler.findLine(restaurant,0,String.valueOf(tableID),1);
        this.size = Integer.parseInt(data[2]);
        this.booked = Boolean.parseBoolean(data[3]);
    }
    // Save table to file
    public void save() {
        String line = restaurant + "~" + tableID + "~" + size + "~" + booked;
        handler.saveLine(line);
    }

    public void update(int psize){
        String line = restaurant + "~" + tableID + "~" + psize + "~" + booked;
        String id = String.valueOf(tableID);
        handler.updateLine(restaurant,0,id,1,line);

    }
    public void update(int psize,boolean pbooked){
        String line = restaurant + "~" + tableID + "~" + psize + "~" + String.valueOf(pbooked);
        String id = String.valueOf(tableID);
        handler.updateLine(restaurant,0,id,1,line);
    }
    public void update(Boolean pbooked){
        String line = restaurant + "~" + tableID + "~" + size + "~" + String.valueOf(pbooked);
        String id = String.valueOf(tableID);
        handler.updateLine(restaurant,0,id,1,line);

    }
    // Getters and setters
    public int getTableID() {
        return tableID;
    }

    public void setTableID(int tableID) {
        this.tableID = tableID;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String isBooked() {
        if (this.booked)
            return "Not Available";
        else
            return "Available";
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }



}
