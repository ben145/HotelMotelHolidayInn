package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Denise FUullerton
 * Created: 3/24/18
 */
public class SelectReserveRoom {
    public Customer customer;
    public Room room;


    /**
     * Default constructor
     */
    public SelectReserveRoom(){

    }
    /**
     * Set some room/Guest objects and data for tests
     *
     */
    public void setGuestAndRoom(){
        Room testRoom;
        Customer testGuest;
        testGuest = new Customer("BobFrog" , "stuft-Shirt");
        testRoom = new Room(true, 54, 60.00, 8, "FIRM", "STUFFS");
        room = testRoom;
        customer = testGuest;
    }


    /**
     * Constructor for SelectReserveRoom object
     * Passing in pointers to customer object and room object so that room and Customer data
     * can be updated with reservation data
     * @param c customer object
     * @param r room object -
     */
    public SelectReserveRoom(Customer c, Room r){
        this.customer = c;
        this.room = r;

    }

    /**
     * Checks for valid guest/customer ID
     * verifies a guest id is attached to selection
     * @return boolean
     */
    public boolean checkGuestID(){
        boolean has_GID;
        if(customer.getName() !=null){
            has_GID = true;
        }else{
            has_GID = false;
        }
        return has_GID;
    }
    /**
     * Checks for valid room number
     * verifies a room number is attached to room object passed in
     * @return boolean
     */
    public boolean checkRoomNum(){
        boolean has_RNUM;
        if(room.roomNumber != 0){
            has_RNUM= true;
        }else{
            has_RNUM = false;
        }
        return has_RNUM;
    }

    /**
     * Checks for room available
     * verifies room object is available to reserve
     * @return boolean
     */
    public boolean checkRoomAvailable(){
        boolean isAvailable;
        if(room.available){
            isAvailable = true;
        }else{
            isAvailable = false;
        }
        return isAvailable;
    }


    /**
     *
     */
    public void cancelRoom(){
        customer.setRoom(0);
        // not private, do not need getters and setters?
        room.setReservationName(null);
    }

    /**
     * Sets values in each appropriate object
     * Room number will be added to guest/customer
     * Guest/Customer ID will be added to room
     * Reservation ID will be added to both room and customer/guest objects
     * Room available will be updated to false
     * returning reservation ID to display for reservation confirmation
     * @return String reservation ID
     */
    public void reserveRoom(){

        customer.setRoom(room.roomNumber);
        String reserveID = createReservationID();
        customer.setReservation(reserveID);
        room.setReservationName(reserveID);
        room.available = false;
    }

    /**
     * Creates reservation ID based on customer/guest ID
     * + 4 random characters selected from A-H and 0-9
     * + date in format Mddyyyy
     * returns the concatenation in string form
     * @return String reservation ID
     */
    public String createReservationID(){

        String reservationID = customer.getId()+"-";
        String randomGen = "ABCDEGH0123456789";
        SimpleDateFormat sd = new SimpleDateFormat("Mddyyy");
        String date = sd.format(new Date());
        int num = randomGen.length();
        Random r = new Random();

        for(int i = 0; i < 4; i++){
            reservationID += randomGen.charAt(r.nextInt(num));
        }
        reservationID += "-"+date;
        System.out.println(reservationID);
        return reservationID;
    }

    /**
     * To be built to cancel reservation
     * @param reservationID
     */
    public void cancelReserve(String reservationID){
        // loop to check guest list for reserveID

    }

}
