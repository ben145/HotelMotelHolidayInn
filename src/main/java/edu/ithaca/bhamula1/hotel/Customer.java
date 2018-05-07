package edu.ithaca.bhamula1.hotel;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ben on 3/25/2018.
 */
public class Customer implements CustomerInterface {

    private String name;
    private String id;
    private String fName;
    private String lName;
    private int room;
    private boolean returningCustomer;

    private boolean checkedIn;
    private boolean loggedIn;
    private String pwd;

    Customer(String nameIn, String idIn) {
        name = nameIn;
        id = idIn;
        loggedIn = false;
    }


    // Default constructor
    Customer() {
        name = "";
        id = "";
        loggedIn = false;
        returningCustomer = false;
    }

    /**
     * Customer constructor
     * @param fName     Customer's first name
     * @param lName     Customer's last name
     * @param id        Customer's temporary ID for hotel stay
     * @param room      Number of room customer is staying in
     * @param checkedIn True or false, if customer is checked in or not
     */
    public Customer (String fName, String lName, String id, int room, boolean checkedIn){
        this.name = fName+ " "+ lName;
        this.id = fName+lName;
        this.fName = fName;
        this.lName = lName;
        this.room = room;
        this.checkedIn = checkedIn;
        this.loggedIn = true;
        this.returningCustomer  = false;
    }

    /**
     * Alternate Customer constructor
     * @param fName     Customer's first name
     * @param lName     Customer's last name
     * @param room      Number of room customer is staying in
     * @param ckedIn    True or false, if customer is checked in or not
     * @param logIn     True or false, if customer is logged in or not
     * @param rCust     True or false, if customer has stayed at this hotel or not
     * @param p         Customer's password
     */
    public Customer (String fName, String lName, int room, boolean ckedIn, boolean logIn, boolean rCust, String p){
        this.id = fName+lName;
        this.fName = fName;
        this.lName = lName;
        this.room = room;
        this.checkedIn = ckedIn;
        this.loggedIn = logIn;
        this.returningCustomer  = rCust;
        this.pwd = p;
    }

    public String makeName(String firstName, String lastName) {

        fName = firstName.substring(0,1).toUpperCase() + firstName.substring(1,firstName.length());
        lName = lastName.substring(0,1).toUpperCase() + lastName.substring(1,lastName.length());
        name = firstName + " " + lastName;
        return (fName +" "+ lName);
    }

    public String makeID() {
        String flName = fName.substring(0,1) + lName.substring(0,1);
        Random rand = new Random();
        int num = rand.nextInt(100000);
        int diff = 0;
        String numStr = Integer.toString(num);
        String extraZeroes = "";
        if (numStr.length() < 5) {
            diff = 5 - numStr.length();
            for (int i = 0; i < diff; i++)
                extraZeroes = extraZeroes + "0";
        }

        String yourId = (flName + extraZeroes + numStr);
        id = yourId;
        System.out.println("Your login ID is " + yourId);
        return yourId;
    }

    public String getName() {return fName+" "+lName;}

    public String getFName() {return fName;}

    public void setFName(String first){fName = first;}

    public String getLName() {return lName;}

    public void setLName(String last) {lName = last;}

    public String getId() {return id;}

    public void setId(String custId) { id = custId;}

    public void login(String idIn,String pwdIn) {
        if (idIn.equals(this.id)&&pwdIn.equals(this.pwd))
            loggedIn = true;
        else
            System.out.println("Problem logging in");
    }

    public void logOut(){loggedIn = false;}

    public boolean isCheckedIn() {return checkedIn;}

    public void setCheckedIn(Boolean ckIn){checkedIn=ckIn;}

    public boolean checkIn(int roomNumber) {
        this.room = roomNumber;
        this.checkedIn = true;
        return true;
    }

    public boolean checkOut(int roomNumber) {
        this.checkedIn=false;
        this.room = -1;
        return true;
    }

    public int getRoom() {return room;}

    public void setRoom(int room) {
        this.room = room;
    }

    public boolean getLoggedIn(){
        return this.loggedIn;
    }

    public void setLoggedIn(boolean logIn){loggedIn = logIn;}

    // Reservation data type and functions to store room reservation ID in Customer's class
    private String reservation;

    public String getReservation(){return reservation;}

    public void setReservation(String r){reservation = r;}

    public boolean getReturningCustomer(){return this.returningCustomer;}

    public void setReturningCustomer(boolean returning){this.returningCustomer = returning;}

    public boolean checkPwd(String p){return (this.pwd.equals(p));}

    public String getPwd(){return pwd;}

}