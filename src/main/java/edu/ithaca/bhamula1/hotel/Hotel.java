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

    public void setupTestHotel(){
        for(int i = 0; i<4; i++) {
            rooms.add(new Room(i));
        }
        customers.add(new Customer("Brad", "Keith"));
    }

    public void checkIn(int roomNumber, Customer customer){
        //find room
        //check room is reserved
        //find customer check matches stored room number
        //set customer as checked in
        //set room to be checked in
    }
}
