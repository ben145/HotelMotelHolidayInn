package edu.ithaca.bhamula1.hotel;

public class Customer extends User {
    int roomNumber;
    boolean checkedIn;

    public Customer(String firstname,String lastname){
        super(firstname,lastname);
        checkedIn = false;
    }

    public void checkIn(int roomNumber){

    }

    public void checkOut(int roomNumber){

    }

}
