package edu.ithaca.bhamula1.hotel;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Ben on 3/25/2018.
 */
public class Customer {

    private String name;
    private String id;
    private String fName;
    private String lName;
    private int room;
    private boolean isCheckedIn;
    private boolean isLoggedIn;

    Customer(String nameIn, String idIn) {
        name = nameIn;
        id = idIn;
        isCheckedIn = false;
        isLoggedIn = false;
    }

    Customer(){
        name = makeName();
        id = makeID();
        isCheckedIn = false;
        isLoggedIn = false;
    }

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
        // reformatting so lastName has capital first letter, otherwise lowercase
        lName = lastName.substring(0,1).toUpperCase() + lastName.substring(1,lastName.length());
        return (fName +" "+ lName);
    }

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

    public void login() {
        Scanner scan = new Scanner(System.in);
        boolean successful = false;
        String idIn;
        String option;
        System.out.println("Please enter your ID");
        idIn = scan.next();
        if (idIn.equals(getId())) {
            System.out.println("Log in successful");
            isLoggedIn = true;
        }
        else {
            while (!successful) {
                System.out.println("Incorrect login information. Please try again, or enter [D]one to exit.");
                option = scan.next();
                if (option.toUpperCase().equals("D")) {
                    System.out.println("Thank you and goodbye");
                    successful = true;
                } else if (option.equals(getId())) {
                    System.out.println("Log in successful");
                    isLoggedIn = true;
                    successful = true;
                } else
                    System.out.println("Input undefined. Please try again.");
            }
        }
    }

    public void logout() {
        Scanner scan = new Scanner(System.in);
        boolean successful = false;
        String idIn;
        String option;
        System.out.println("Please enter your ID");
        idIn = scan.next();
        if (idIn.equals(getId())) {
            System.out.println("Log out successful");
            isLoggedIn = false;
        }
        else {
            while (!successful) {
                System.out.println("Incorrect login information. Please try again, or enter [D]one to exit");
                option = scan.next();
                if (option.toUpperCase().equals("D")) {
                    System.out.println("Thank you and goodbye");
                    successful = true;
                } else if (option.equals(getId())) {
                    System.out.println("Log out successful");
                    isLoggedIn = false;
                    successful = true;
                } else
                    System.out.println("Input undefined. Please try again");
            }
        }
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public static void main(String[] args) {
        Customer c = new Customer();
        c.login();
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public boolean checkIn(int roomNumber) {
        if (isLoggedIn) {
            if (this.room > 0) {
                if (!this.isCheckedIn) {
                    if (roomNumber == this.room) {
                        System.out.println("Welcome. Enjoy your stay.");
                        this.isCheckedIn = true;
                        return true;
                    } else {
                        System.out.println("You have not reserved this room.");
                        return false;
                    }
                } else {
                    System.out.println("You are already checked in.");
                    return false;
                }
            } else {
                System.out.println("You have not reserved a room. Please reserve a room to check in.");
                return false;
            }
        } else
            System.out.println("You are not logged in. You need to be logged in to check in.");
            return false;
    }

    public boolean checkOut(int roomNumber) {
        if(roomNumber == room){
            if(isCheckedIn){
                System.out.println("Thank you. We hope you enjoyed your stay.");
                this.isCheckedIn = false;
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

    public void setRoom(int room) {
        this.room = room;
    }
}
