package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 */
public class SelectReserveRoom {
    public Guest guest;
    public Room room;

    /**
     * Below are two mock classses for room and guest - customer to run tests
     */

    class Guest{
        String guestID;
        String roomNum;
        private String gReserveID;

        //String roomNum;
        private Guest(){
            guestID = null;
            roomNum= null;
            gReserveID = null;
        }

        public Guest (String gID, String rmNum, String rmResID){
            this.guestID = gID;
            this.roomNum = rmNum;
            this.gReserveID = rmResID;
        }
    }
    class Room{
        String rmNum;
        String gID;
        private String rReserveID;
        private boolean available = false;
        //String roomNum;
        private Room(){
            rmNum = null;
            gID = null;
            rReserveID = null;
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
     * SEt some room/Guest data for tests
     *
     */

    public void setGuestAndRoom(){
        Room testRoom;
        Guest testGuest;
        testGuest = new Guest("Guest1",null,null);
        testRoom = new Room("18A",null,null);
        room = testRoom;
        guest = testGuest;
    }


    public SelectReserveRoom(){
        setGuestAndRoom();
    }

    /**
     *
     * @param g
     * @param r
     */
    public SelectReserveRoom(Guest g, Room r){
        guest = g;
        room = r;

    }

    /**
     * Checks for valid guest ID
     */
    public boolean checkGuestID(){
        boolean has_GID;
        if(guest.guestID !=null){
            has_GID = true;
        }else{
            has_GID = false;
        }
        return has_GID;
    }
    /**
     * Checks for valid room number
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
     * When guest chooses to select a room
     * SelectReserveRoom  is instantiated with guest and room
     *
     * Room number will be added to guest and guest ID will be added to room for period
     *
     */
    public void selectRoom(){

        System.out.print("Please enter room number to select room to reserve: ");
        StringBuffer s = new StringBuffer();
        Scanner scan = new Scanner(System.in);
        s.append(scan.next());
        System.out.println(s);
        // print room
        System.out.print("Is this the room you wish to reserve? enter \"Y\" to confirm or \"N\" to change  ");

        if(room.rmNum == s.toString()){
            s.setLength(0);
            s.append(scan.nextLine());
        }
        if(s.toString() == "Y" || s.toString() == "y"){
            room.gID = guest.guestID;
            guest.roomNum = room.rmNum;
            guest.gReserveID += createReservationID();
        }else if(s.toString() == "N" || s.toString() == "n"){
            System.out.print("You entered "+s.toString()+ ". Do you wish to select a room? Enter \\\"Y\\\" for yes or \\\"N\\\" for no  \"");
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

        guest.roomNum = null;
        room.gID = null;

    }

    /**
     *
     * @return
     */

    public String reserveRoom(){

        return "";

    }

    /**
     *
     * @return String reservation ID
     */
    public String createReservationID(){

        String reservationID = guest.guestID+"-";
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
     *
     * @param reservationID
     */
    public void cancelReserve(String reservationID){



        // loop to check guest list for reserveID

    }

    public String getResId(){
        return room.rReserveID;
    }
    public void setResID(String resID){
        room.rReserveID = resID;
    }


}
