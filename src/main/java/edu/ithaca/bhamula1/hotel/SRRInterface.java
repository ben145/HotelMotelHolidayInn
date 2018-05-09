package edu.ithaca.bhamula1.hotel;

/**
 * 
 */
public interface SRRInterface {

    /**
     * Checks for valid guest/customer ID attached to selection
     * @return True if customer has ID, false otherwise
     */
     boolean checkGuestID();

    /**
     * Checks for valid room number attached to room object passed in
     * @return True if customer has room (number), false otherwise
     */
     boolean checkRoomNum();

     boolean checkRoomAvailable();

    /**
     * Removes reserved status from room
     * @post customer's room number set to 0; name of reservation to null
     */
     void cancelRoom();

    /**
     * Reserves a room for a customer while updating relevant details
     * @post customer has room number and reservation; room has reservation
     *  and made unavailable for duration of reservation
     */
     void reserveRoom();

    /**
     * Creates reservation ID based on customer/guest ID and random characters
     * @return Reservation ID string
     */
     String createReservationID();

}