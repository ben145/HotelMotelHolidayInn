package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/22/2018.
 */
public class Room {


































































































































































































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
        if(customer.getLastName().equals(this.lastNameReserved)&&this.checkedIn){
            this.checkedIn=false;
            this.lastNameReserved=null;
            return true;
        }
        else {
            return false;
        }
    }
}
