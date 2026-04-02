package com.dinetime.dinetime.Classes;

import com.dinetime.dinetime.Handlers.StorageHandler;

public class Admin extends User{

    private StorageHandler restaurantHandler = new StorageHandler("restaurants.txt");
    private StorageHandler userHandler = new StorageHandler("users.txt");
    private StorageHandler adminHandler = new StorageHandler("admin.txt");


    public Admin() {
        super();
        String[][] lines = adminHandler.readAllLines();
        this.email = lines[0][0];
        this.displayName= lines[1][0];
        this.password = lines[2][0];
        this.accountType= -99;
    }


    public Restaurant[] getAllRestaurants(){

        String[][] allRestaurants = restaurantHandler.readAllLines();
        Restaurant[] allRestaurantObjects = new Restaurant[allRestaurants.length];

        for (int i = 0; i < allRestaurants.length; i++) {
            String[] restaurantStr = allRestaurants[i];
            Restaurant restaurantObj = new Restaurant(restaurantStr[1]);
            allRestaurantObjects[i]=restaurantObj;
        }
        return allRestaurantObjects;
    }

    public User[] getAllUsers(){
        String[][] allUsers = userHandler.readAllLines();
        User[] users = new User[allUsers.length];
        for (int i = 0; i < allUsers.length; i++) {
            String[] u = allUsers[i];
            User user = new User(u[1],u[2],u[0],Integer.parseInt(u[3]));
            users[i] = user;
        }
        return users;
    }
    public Customer[] getAllCustomers(){
        String[][] allCustomers = userHandler.getAllLines("1",3);
        Customer[] customers = new Customer[allCustomers.length];
        for (int i = 0; i < allCustomers.length; i++){
            String[] u = allCustomers[i];
            Customer customer = new Customer(u[1],u[2],u[0]);
            customers[i] = customer;
        }
        return customers;
    }
    public Owner[] getAllOwners(){
        String[][] allOwners = userHandler.getAllLines("2",3);
        Owner[] owners = new Owner[allOwners.length];
        for (int i = 0; i < allOwners.length; i++){
            String[] u = allOwners[i];
            Owner owner = new Owner(u[1],u[2],u[0]);
            owners[i] = owner;
        }
        return owners;
    }
    public void changeAdminName(String name){
        adminHandler.updateLine(this.displayName,0,name);
    }
    public void changeAdminEmail(String email){
        adminHandler.updateLine(this.email,0,email);

    }
    public void changeAdminPassword(String password){
        adminHandler.updateLine(this.password,0,password);

    }
}
