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

    Customer(String nameIn, String idIn) {
        name = nameIn;
        id = idIn;
        loggedIn = false;
    }

    Customer(){
//        name = makeName();
//        id = makeID();

        name = "";
        id = "";
        loggedIn = false;
        this.returningCustomer = false;
    }

    public Customer (String fname, String lName, String id, int room, boolean checkedIn){
        this.name = fname+ " "+ lName;
        this.id = fname+lName;
        this.fName = fname;
        this.lName = lName;
        this.room = room;
        this.checkedIn = checkedIn;
        this.loggedIn = true;
        this.returningCustomer  = false;
    }
    public Customer (String fname, String lName, String id, int room, boolean ckedIn, boolean logIn, boolean rCust){
        this.id = fname+lName;
        this.fName = fname;
        this.lName = lName;
        this.room = room;
        this.checkedIn = ckedIn;
        this.loggedIn = logIn;
        this.returningCustomer  = rCust;
    }

    //set name
    public String makeName(String firstName, String lastName) {
//        String firstName, lastName;
////        Scanner scan = new Scanner(System.in);
////        // getting first name and storing in firstName
////        System.out.println("Please enter your first name");
////        firstName = scan.next();
        // reformatting so firstName has capital first letter, lowercase otherwise



        fName = firstName.substring(0,1).toUpperCase() + firstName.substring(1,firstName.length()-1);


//        // getting last name and storing in lastName
//        System.out.println("Please enter your last name");
//        lastName = scan.next();
        // reformatting so lastName has capital first letter, otherwise lowercase




        lName = lastName.substring(0,1).toUpperCase() + lastName.substring(1,lastName.length()-1);

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

    public String getName() {
        return name;
    }

    public String getFName() {return fName;}

    public void setFName(String first){fName = first;}

    public String getLName() {return lName;}

    public void setLName(String last) {lName = last;}

    public String getId() {return id;}

    public void setId(String custid) { id=custid;}


    //ONly one log in right now
    public void login(String idIn) {
//        Scanner k = new Scanner(System.in);
//        String idIn;
//        System.out.println("Please enter your ID");
//        idIn = k.next();
//        String custID = "abc123";
        if (idIn.equals(this.id)){
            loggedIn = true;
        }
        else{
            System.out.println("Incorrect Password.");
        }

    }

    public void logOut(){
        loggedIn = false;
    }

//    public static void main(String[] args) {
//        Customer c = new Customer();
//        c.login();
//    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public boolean checkIn(int roomNumber) {
        this.checkedIn = true;
        return true;
    }

    public boolean checkOut(int roomNumber) {
        this.checkedIn=false;
        this.room = -1;
        return true;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public boolean getLoggedIn(){
        return this.loggedIn;
    }


    /**
     * Reservation data type and functions to store room reservatin ID in Customer's class
     * @author - DMF
     */
    //adding reservation to cust class
    private String reservation;

    /**
     * Used to get the reservation associated with the room and customer
     * @return String
     */
    public String getReservation(){return reservation;}

    /**
     * sets the reservation associated with the room
     * @param r String
     */
    public void setReservation(String r){ reservation = r;}

    public int getRoom() {
        return room;
    }

    public boolean getReturningCustomer(){
        return this.returningCustomer;
    }

    public void setReturningCustomer(boolean returning){
        this.returningCustomer = returning;
    }

    public void setCheckedIn(Boolean ckIn){checkedIn=ckIn;}

    public void setLoggedIn(boolean logIn){loggedIn = logIn;}


}