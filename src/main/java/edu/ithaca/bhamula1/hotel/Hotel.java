package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ben on 3/22/2018.
 */
public class Hotel {
    private Map<Integer,Room> rooms;
    private List<customer> customers;

    public Hotel(){
        rooms = new HashMap<>();
        customers = new ArrayList<>();
    }

    //only for use in testing checkin and checkout before actual function is added
    public Room getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public boolean checkIn(int roomNumber, customer customer){
        //find room
        Room current = getRoom(roomNumber);
        //check room is reserved
        if(!current.getIfAvailable()){
            if(customer.getName().equals(current.getReservationName())){
                boolean c = customer.checkIn(roomNumber);
                boolean r = current.checkIn(customer);
                return c&r;
            }
            else{
                System.out.println("You have not reserved this room. You must have reserved a room to check in.");
                return false;
            }
        }
        else{
            System.out.println("Room "+roomNumber+" has not been reserved. A room must be reserved to be checked into.");
            return false;
        }
        //find customer check matches stored room number
        //set customer as checked in
        //set room to be checked in
    }

    public boolean checkOut(int roomNumber, customer customer){
        //find room
        Room current = getRoom(roomNumber);
        if(current.getCheckedIn()){
            if(customer.getName().equals(current.getReservationName())){
                boolean c = customer.checkOut(roomNumber);
                boolean r = current.checkOut(customer);
                return c&r;
            }
            else{
                System.out.println("You are not checked into this room. You must be checked in to checkout.");
                return false;
            }
        }
        else {
            System.out.println("This room is not checked into.");
            return false;
        }
    }

    public void addTestRoom(int roomNumber){
        this.rooms.put(roomNumber,new Room(false,1,100.00,2,"Full","Mini bar"));
    }
}
