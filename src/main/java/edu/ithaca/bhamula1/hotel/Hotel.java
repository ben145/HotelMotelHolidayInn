package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ben on 3/22/2018.
 */
public class Hotel {
    private List<Room> rooms;
    private List<Customer> customers;

    public Hotel(){
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
    }

    //only for use to test checkIn and checkOut while there is no other way to add customers and rooms
    public void setupTestHotel(){
        for(int i = 1; i<5; i++) {
            rooms.add(new Room(i));
        }
        customers.add(new Customer("Brad", "Keith"));
    }

    //only for use in testing checkin and checkout before actual function is added
    public Room getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public void fakeReserveRoom(int roomNumber,String lastName){
        rooms.get(roomNumber).fakeReserveRoom(lastName);
    }

    public boolean checkIn(int roomNumber, Customer customer){
        //find room
        Room current = getRoom(roomNumber);
        //check room is reserved
        if(current.getIsReserved()){
            if(customer.getLastName().equals(current.getLastNameReserved())){
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
}
