package edu.ithaca.bhamula1.hotel;

/**
 * 
 */
public interface SRRInterface {

     boolean checkGuestID();

     boolean checkRoomNum();

     boolean checkRoomAvailable();

     void cancelRoom();

     void reserveRoom();

     String createReservationID();

     void cancelReserve(String reservationID);


}
