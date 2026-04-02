package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

public class Owner extends User{

    public Owner(String displayName, String password, String email) {
        super(displayName, password, email);
        this.accountType = 2;
    }

    public void save() {
        StorageHandler handler = new StorageHandler("users.txt");
        String lineToSave = email + "~" + displayName + "~" + password + "~"+"2";
        handler.saveLine(lineToSave);
    }

    public void update(String displayName, String password, String email){
        StorageHandler handler = new StorageHandler("users.txt");
        String updateLine = email + "~" + displayName + "~" + password + "~2";
        handler.updateLine(this.email,0,updateLine);
    }

    public void addRestaurant(String name, String type, String imageLink, String description){
        Restaurant newRestaurant = new Restaurant(name,type,imageLink,description,this.email);
        //Save new restaurant
        newRestaurant.save();
        // send the success msg
        System.out.println("Restaurant "+name+" Added Successfully");
    }

    public Restaurant[] getAllRestaurants() {
        StorageHandler handler = new StorageHandler("restaurants.txt");
        String[][] ownersRestaurants = handler.getAllLines(this.email, 0);
        Restaurant[] restaurants = new Restaurant[ownersRestaurants.length];

        for (int i = 0; i < ownersRestaurants.length; i++) {
            String[] restaurant = ownersRestaurants[i];
            restaurants[i] = new Restaurant(
                    restaurant[1],  // name
                    restaurant[2],  // type
                    restaurant[3],  // link
                    restaurant[4],  // description
                    restaurant[0]   // email
            );
        }
        return restaurants;
    }

    public void updateRestaurant(String oldName, String newName, String newType, String newImageLink, String newDescription) {
        String updatedLine = this.email + "~" + newName + "~" + newType + "~" + newImageLink + "~" + newDescription;

        StorageHandler handler = new StorageHandler("restaurants.txt");
        handler.updateLine(oldName, 1, updatedLine); // Index 1 = restaurant name column

        System.out.println("Restaurant " + oldName + " updated to " + newName);
    }

    public void deleteRestaurant(String restaurantName) {
        StorageHandler handler = new StorageHandler("restaurants.txt");
        handler.deleteLine(restaurantName, 1); // Index 1 = restaurant name

        System.out.println("Restaurant " + restaurantName + " deleted successfully.");
    }

    @Override
    public String getDisplayName() {
        return super.getDisplayName();
    }

    @Override
    public int getAccountType() {
        return super.getAccountType();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }
}
