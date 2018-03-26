package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/22/2018.
 */
public class Room {
    private boolean isReserved;
    private boolean checkedIn;
    private int number;
    private String lastNameReserved;

    public Room(int num){
        number = num;
        isReserved = false;
        checkedIn = false;
    }

    public boolean getIsReserved(){
        return this.isReserved;
    }

    public void fakeReserveRoom(String lastName){
        this.isReserved = true;
        lastNameReserved = lastName;
    }

    public String getLastNameReserved() {
        return lastNameReserved;
    }

    public boolean checkIn(Customer customer){
        if(customer.getLastName().equals(this.lastNameReserved)){
            this.checkedIn=true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkOut(Customer customer){
        return true;
    }
}
