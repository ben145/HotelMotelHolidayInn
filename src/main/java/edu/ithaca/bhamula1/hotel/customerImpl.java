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
        int diff = 0; diff; i++)
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
}