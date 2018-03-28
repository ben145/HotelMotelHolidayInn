package edu.ithaca.bhamula1.hotel;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ben on 3/25/2018.
 */
public class customerImpl implements customer {

    private String name;
    private String id;
    private String fName;
    private String lName;
    private int room;
    private boolean checkedIn;

    //adding reservation to cust class
    private String reservation;

    customerImpl(String nameIn, String idIn) {
        name = nameIn;
        id = idIn;
    }

    customerImpl(){
        name = makeName();
        id = makeID();
    }

    @Override
    public String makeName() {
        String firstName, lastName;
        Scanner scan = new Scanner(System.in);
        // getting first name and storing in firstName
        System.out.println("Please enter your first name");
        firstName = scan.next();
        // reformatting so firstName has capital first letter, lowercase otherwise
        fName = firstName.substring(0,1).toUpperCase() + firstName.substring(1,firstName.length());
        // getting last name and storing in lastName
        System.out.println("Please enter your last name");
        lastName = scan.next();
        // reformatting so lastName has capital first letter, lowercase otherwise
        lName = lastName.substring(0,1).toUpperCase() + lastName.substring(1,lastName.length());
        return (fName +" "+ lName);
    }

    @Override
    public String makeID() {
        String flName = fName.substring(0,1) + lName.substring(0,1);
        Random rand = new Random();
        double num = rand.nextInt(100000);
        int diff = 0;
        String numStr = Double.toString(num);
        String extraZeroes = "";
        if (numStr.length() < 5) {
            diff = 5 - numStr.length();
            for (int i = 0; i < diff; i++)
                extraZeroes = extraZeroes + "0";
        }
        return (flName + extraZeroes + numStr);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    /**
     * Used to get the room associated with the customer
     * @return int
     */
    public int getRoom(){ return room; }

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

    @Override
    public void login() {
        Scanner k = new Scanner(System.in);
        String idIn;
        System.out.println("Please enter your ID");
        idIn = k.next();
        String custID = "abc123";
        if (idIn.equals(custID))
            System.out.println("Log in successful");
    }

    public static void main(String[] args) {
        customerImpl c = new customerImpl();
        c.login();
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    @Override
    public boolean checkIn(int roomNumber) {
        if(this.room>0){
            if(!this.checkedIn){
                if(roomNumber==this.room){
                    System.out.println("Welcome. Enjoy your stay.");
                    this.checkedIn=true;
                    return true;
                }
                else{
                    System.out.println("You have not reserved this room.");
                    return false;
                }
            }
            else{
                System.out.println("You are already checked in.");
                return false;
            }
        }
        else {
            System.out.println("You have not reserved a room. Please reserve a room to check in.");
            return false;
        }
    }

    @Override
    public boolean checkOut(int roomNumber) {
        if(roomNumber==room){
            if(checkedIn){
                System.out.println("Thank you. We hope you enjoyed your stay.");
                this.checkedIn=false;
                this.room = -1;
                return true;
            }
            else{
                System.out.println("You are not checked in. You must be checked in to check out.");
                return false;
            }
        }
        else {
            System.out.println("Incorrect Room.");
            return false;
        }
    }

    @Override
    public void setRoom(int room) {
        this.room = room;
    }
}