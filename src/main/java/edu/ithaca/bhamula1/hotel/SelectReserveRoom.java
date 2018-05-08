package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Denise FUullerton
 * Created: 3/24/18
 */
public class SelectReserveRoom implements SRRInterface{
    public CustomerInterface customer;
    public RoomInterface room;

    /**
     * Constructor for SelectReserveRoom object
     * Passing in pointers to customer object and room object so that room and Customer data
     * can be updated with reservation data
     * @param c customer object
     * @param r room object -
     */
    public SelectReserveRoom(CustomerInterface c, RoomInterface r){
        this.customer = c;
        this.room = r;
    }

    // Default constructor
    public SelectReserveRoom(){}

    /**
     * Sets some Room and Guest objects and data for tests
     */
    public void setGuestAndRoom(){
        RoomInterface testRoom;
        CustomerInterface testGuest;
        testGuest = new Customer("BobFrog" , "stuft-Shirt");
        testRoom = new Room(true, 54, 60.00, 8, "FIRM", "STUFFS", false);
        room = testRoom;
        customer = testGuest;
    }

    public boolean checkGuestID(){
        return customer.getId() != null;
    }

    public boolean checkRoomNum() {
        return customer.getRoom() != 0;
    }

    public boolean checkRoomAvailable() {
        return room.getIfAvailable();
    }

    public void cancelRoom() {
        customer.setRoom(0);
        room.setReservationName(null);
    }

    public void reserveRoom() {
        customer.setRoom(room.getRoomNumber());
        String reserveID = createReservationID();
        customer.setReservation(reserveID);
        room.setReservationName(reserveID);
        room.setIfAvailable(false);
    }

    public String createReservationID() {

        String reservationID = customer.getId()+"-";
        String randomGen = "ABCDEGH0123456789";
        SimpleDateFormat sd = new SimpleDateFormat("Mddyyyy");
        String date = sd.format(new Date());
        int num = randomGen.length();
        Random r = new Random();

        for (int i = 0; i < 4; i++)
            reservationID += randomGen.charAt(r.nextInt(num));

        reservationID += "-" + date;
        System.out.println(reservationID);
        return reservationID;
    }


}
