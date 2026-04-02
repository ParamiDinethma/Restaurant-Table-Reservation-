package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

import java.time.LocalDateTime;


public class Restaurant {
    private String name;
    private String type;
    private String imageLink;
    private String descrption;
    private String ownerEmail;
    private StorageHandler handler = new StorageHandler("restaurants.txt");

    public Restaurant(String name, String type, String imageLink, String description, String ownerEmail) {
        this.name = name;
        this.type = type;
        this.imageLink = imageLink;
        this.descrption = description;
        this.ownerEmail = ownerEmail;
    }

    //Constructor to get saved restaurants from txt.
    public Restaurant(String name){
        String[] data = handler.findLine(name, 1);
        if (data != null && data.length >= 5) {
            this.name = name;
            this.type = data[2];
            this.imageLink = data[3];
            this.descrption = data[4];
            this.ownerEmail = data[0];
        }
    }

    public String getDescription() {
        return descrption;
    }

    public void setDescription(String description) {
        this.descrption = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void save() {
        StorageHandler handler = new StorageHandler("restaurants.txt");
        String lineToSave = ownerEmail + "~"+name + "~" + type + "~" + imageLink+"~" + descrption;
        handler.saveLine(lineToSave);
    }

    public void delete(){
        handler.deleteLine(this.name,1);
    }

    public String getOwnerEmail(){
        return this.ownerEmail;
    }

    public void update(String newName,String newType,String newLink,String newDescription){
        String line = this.ownerEmail+"~"+newName+"~"+newType+"~"+newLink+"~"+newDescription;
        handler.updateLine(this.name,1,line);
    }

    public boolean nameAlreadyTaken(){
        String[] data = handler.findLine(name,1);
        if (data == null)
            return false;
        else
            return true;
    }
    public Table[] getAllTables(){
        StorageHandler tableHandler = new StorageHandler("tables.txt");
        String[][] allTables = tableHandler.getAllLines(this.name,0);
        System.out.println(allTables.length);
        Table[] allTableObjects = new Table[allTables.length];
        for (int i = 0; i < allTables.length; i++) {
            String[] tableStr = allTables[i];
            Table tableObj = new Table(
                    Integer.parseInt(tableStr[1]),
                    tableStr[0],
                    Integer.parseInt(tableStr[2]),
                    Boolean.parseBoolean(tableStr[3]));
            allTableObjects[i] = tableObj;
        }

        return allTableObjects;
    }
    public Reservation[] getAllReservation(){
        StorageHandler tableHandler = new StorageHandler("reservations.txt");
        String[][] allReservations = tableHandler.getAllLines(this.name,1);
        System.out.println(allReservations.length+"reservationstr");
        Reservation[] allRestaurantObjects = new Reservation[allReservations.length];
        for (int i = 0; i < allReservations.length; i++) {
            String[] reservationStr = allReservations[i];
            Reservation reservationObj = new Reservation(reservationStr[1],LocalDateTime.parse(reservationStr[2]),Integer.parseInt(reservationStr[3]),reservationStr[0]);
            allRestaurantObjects[i] = reservationObj;
            reservationObj.setTableID(reservationStr[4]);
        }
        return allRestaurantObjects;
    }
    public void completeReservation(int tableId){
        Table table = new Table(tableId,this.name);
        table.update(false);
        Reservation reservation = new Reservation(this.name,String.valueOf(tableId));
        reservation.delete();
    }
}
