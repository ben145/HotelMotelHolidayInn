package edu.ithaca.bhamula1.hotel;

public class Customer extends User {
    private int roomNumber;
    private boolean checkedIn;

    public Customer(String firstname,String lastname){
        super(firstname,lastname);
        checkedIn = false;
    }

    public void fakeReserveRoom(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public void checkIn(int roomNumber){

    }

    public void checkOut(int roomNumber){

    }

    public boolean getCheckedIn(){
        return this.checkedIn;
    }

}
