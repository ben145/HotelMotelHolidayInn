package edu.ithaca.bhamula1.hotel;

/**
 *
 */
public class SelectReserveRoom {
    private Guest guest;
    private Room room;


    class Guest{
        String guestID;
        String roomNum;

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
     *
     */
    public void selectRoom(){

    }

    /**
     *
     */
    public void cancelRoom(){

    }

    /**
     *
     * @return
     */

    public String reserveRoom(){

        String reservationID = "";

        return reservationID;
    }


    /**
     *
     * @param reservationID
     */
    public void cancelReserve(String reservationID){

    }



}
