package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Denise FUullerton
 * Created: 3/24/18
 */
public class SelectReserveRoom_2 implements SRRInterface {
    public Customer Customer;
    public Room room;

    /**
     * Below are two mock classses for room and Customer - customer to run tests
     */

    class Customer{
        String CustomerID;
        String roomNum;
        String gReserveID;

        //String roomNum;
        private Customer(){
//            CustomerID = null;
//            roomNum= null;
//            gReserveID = null;
        }

        public Customer (String gID, String rmNum, String rmResID){
            this.CustomerID = gID;
            this.roomNum = rmNum;
            this.gReserveID = rmResID;
        }
    }
    class Room{
        String rmNum;
        String gID;
        String rReserveID;
        boolean available = false;
        //String roomNum;
        private Room(){
//            rmNum = null;
//            gID = null;
//            rReserveID = null;
        }

        public Room (String rNum, String guID, String rmResID){
            this.rmNum =rNum;
            this.gID = guID;
            this.rReserveID = rmResID;
            if(guID==null){
                this.available = true;
            }else{
                this.available = false;
            }
        }
    }

    /**
     * Default constructor
     */
    public SelectReserveRoom_2(){

    }
    /**
     * Set some room/Customer objects and data for tests
     *
     */
    public void setCustomerAndRoom(){
        Room testRoom;
        Customer testCustomer;
        testCustomer = new Customer("Customer1",null,null);
        testRoom = new Room("18A",null,null);
        room = testRoom;
        Customer = testCustomer;
    }


    /**
     * Constructor for SelectReserveRoom object
     * Passing in pointers to customer/Customer object and room object so that room and Customer data
     * can be updated with reservation data
     * @param g Customer/customer object
     * @param r room object -
     */
    public SelectReserveRoom_2(Customer g, Room r){
        Customer = g;
        room = r;

    }

    /**
     * Checks for valid Customer/customer ID
     * verifies a Customer id is attached to selection
     * @return boolean
     */
    public boolean checkCustomerID(){
        boolean has_GID;
        if(Customer.CustomerID !=null){
            has_GID = true;
        }else{
            has_GID = false;
        }
        return has_GID;
    }

    @Override
    public boolean checkGuestID() {
        return false;
    }

    /**
     * Checks for valid room number
     * verifies a room number is attached to room object passed in
     * @return boolean
     */
    public boolean checkRoomNum(){
        boolean has_RNUM;
        if(room.rmNum != null){
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
     * When Customer chooses to select a room
     * SelectReserveRoom  is instantiated with Customer and room
     *
     *
     */
    public void selectRoom(){

        System.out.println("Please enter room number to select room to reserve: ");
        StringBuffer s = new StringBuffer();
        Scanner scan = new Scanner(System.in);
        s.append(scan.next());
        System.out.println(s);
        // print room
        System.out.println("Is this the room you wish to reserve? enter \"Y\" to confirm or \"N\" to change  ");

        if(room.rmNum == s.toString()){
            s.setLength(0);
            s.append(scan.nextLine());
        }
        if(s.toString() == "Y" || s.toString() == "y"){
            System.out.println("Your Reservation ID is "+ reserveRoom()+ " for Room "+room.rmNum);
        }else if(s.toString() == "N" || s.toString() == "n"){
            System.out.println("You entered "+s.toString()+ ". Do you wish to select a room? Enter \\\"Y\\\" for yes or \\\"N\\\" for no  \"");
            s.setLength(0);
            s.append(scan.nextLine());
            if(s.toString() == "Y" || s.toString() == "y") {
                selectRoom();
            }
        }else{
            System.out.println("Invalid input, you entered "+s.toString()+". Please try again.");
            selectRoom();
        }
    }

    /**
     *
     */
    public void cancelRoom(){
        Customer.roomNum = null;
        room.gID = null;

    }

    /**
     * Sets values in each appropriate object
     * Room number will be added to Customer/customer
     * Customer/Customer ID will be added to room
     * Reservation ID will be added to both room and customer/Customer objects
     * Room available will be updated to false
     * returning reservation ID to display for reservation confirmation
     * @return String reservation ID
     */
    public void reserveRoom(){

        room.gID = Customer.CustomerID;
        Customer.roomNum = room.rmNum;
        String reserveID = createReservationID();
        Customer.gReserveID = reserveID;
        room.rReserveID = reserveID;
        room.available = false;
        //return Customer.gReserveID;
    }

    /**
     * Creates reservation ID based on customer/Customer ID
     * + 4 random characters selected from A-H and 0-9
     * + date in format Mddyyyy
     * returns the concatenation in string form
     * @return String reservation ID
     */
    public String createReservationID(){

        String reservationID = Customer.CustomerID+"-";
        String randomGen = "ABCDEGH0123456789";
        SimpleDateFormat sd = new SimpleDateFormat("Mddyyy");
        String date = sd.format(new Date());
        int num = randomGen.length();
        Random r = new Random();

        for(int i = 0; i < 4; i++){
            reservationID += randomGen.charAt(r.nextInt(num));
        }
        reservationID += "-"+date;
        return reservationID;
    }

    /**
     * To be built to cancel reservation
     * @param reservationID
     */
    public void cancelReserve(String reservationID){
        // loop to check Customer list for reserveID

    }


    /**
     * Getter to get reservation ID - may not use
     * @return
     */
    public String getResId(){
        return room.rReserveID;
    }

    /**
     * Setter to set reservation ID - May not use
     * @param resID
     *
     */
    public void setResID(String resID){
        room.rReserveID = resID;
    }
}

