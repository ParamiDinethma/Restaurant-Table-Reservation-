package com.dinetime.dinetime.Classes;
import com.dinetime.dinetime.Handlers.StorageHandler;

public class User {
    protected String displayName;
    protected String password;
    protected String email;
    protected int accountType;

    public User( String displayName, String password, String email){
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.accountType = 0;
    }

    public User( String displayName, String password, String email,int accountType){
        this.displayName = displayName;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
    }
    public User(){
        this.displayName=null;
        this.email=null;
        this.accountType=0;
        this.password="12345678";
    }

    public void save() {
        StorageHandler handler = new StorageHandler("users.txt");
        String lineToSave = email + "~" + displayName + "~" + password + "~"+"0";
        handler.saveLine(lineToSave);
    }

    public String getEmail() {
        return email;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getPassword() {
        return password;
    }
    public boolean checkPassword(String password){
        if (password.equals(this.password) ){
            return true;
        }else {
            return false;
        }
    }
}
