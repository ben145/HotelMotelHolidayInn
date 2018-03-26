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
        for(int i = 0; i<4; i++) {
            rooms.add(new Room(i));
        }
        customers.add(new Customer("Brad", "Keith"));
    }

    //only for use in testing checkin and checkout before actual function is added
    public Room getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public void checkIn(int roomNumber, Customer customer){
        //find room
        Room current = getRoom(roomNumber);
        //check room is reserved
        if(current.getIsReserved()){

        }
        else{
            System.out.println("Room "+roomNumber+" has not been reserved. A room must be reserved to be checked into.");
        }
        //find customer check matches stored room number
        //set customer as checked in
        //set room to be checked in
    }
}
