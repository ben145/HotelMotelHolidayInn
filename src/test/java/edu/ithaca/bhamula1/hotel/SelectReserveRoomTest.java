package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @author Denise FUullerton
 * Created: 3/24/18
 */

class SelectReserveRoomTest {

    /**
     * new SelectReserveRoom object to use for tests
     */
    private SelectReserveRoom select = new SelectReserveRoom();

    /**
     * setup for tests
     */
    @BeforeEach
    void setupData(){
        select.setGuestAndRoom();
    }

    /**
     * Testing for non null Guest/customer ID
     */
    @Test
    void checkGuestIDTest() {
        assertEquals(true, select.checkGuestID(), "No Guest/Customer ID- cannot select room to reserve without c/g-ID");
    }

    /**
     * Testing for non null room number
     */
    @Test
    void checkRoomNumTest() {
        assertEquals(true,select.checkRoomNum(), "No Room number- cannot select room with no room number");
    }

    /**
     * Testing for room.available value
     */
    @Test
    void checkRoomAvailableTest(){
        assertEquals(true, select.checkRoomAvailable(), "Error - Room is not available to reserve");
    }

    /**
     * Cannot test - has user input fields that I am not sure how to initiate because
     * the console input does not work in tests
     */
    @Test
    void selectRoomTest() {
        //select.selectRoom();
    }

    /**
     * Testing to verify cust/guest ID is added/saved to the reserved room object
     */
    @Test
    void reserveRoomGuestRoomIDTest() {
        select.reserveRoom();
        assertEquals(select.customer.getReservation(),select.room.getReservationName(), "Err - No guest ID associated with Room");
    }

    /**
     * Testing to verify reserved room number is added/saved to cust/guest object
     */
    @Test
    void reserveRoomGuestRoomRmNumTest() {
        select.reserveRoom();
        assertEquals(54,select.customer.getRoom(), "Err - No Room associated with guest ID");
    }

    /**
     * Testing to room object available flag is changed to false once reserved
     */
    @Test
    void reserveRoomAvailTest() {
        select.reserveRoom();
       assertEquals(false,select.room.getIfAvailable(), "Err - Room is showing available as true, should be false");
    }

    /**
     * Testing to verify the reservation ID that is added to cust/guest object is equivalent to room object reservation ID
     */
    @Test
    void reserveRoomReserveIDTest() {
        select.reserveRoom();
        System.out.println(select.customer.getReservation() + "<--customer   room-->"+ select.room.getReservationName());
        assertEquals(select.customer.getReservation(), select.room.getReservationName(), "Err - guest and room Reservation ID do not Match");
    }

    /**
     * Testing to verify the reservation ID is not empty
     */
    @Test
    void createReservationIDTest(){
        assertEquals(false,select.createReservationID().isEmpty(), "No reservation ID");
    }

}