package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SelectReserveRoomTest {

    private SelectReserveRoom select = new SelectReserveRoom();


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

    @Test
    void checkRoomAvailableTest(){
        assertEquals(true, select.checkRoomAvailable(), "Error - Room is not available to reserve");
    }

    @Test
    void selectRoomTest() {
        //select.selectRoom();
    }

    @Test
    void reserveRoomGuestRoomIDTest() {
        select.reserveRoom();
        assertEquals("Guest1",select.room.gID, "Err - No guest ID associated with Room");
    }

    @Test
    void reserveRoomGuestRoomRmNumTest() {
        select.reserveRoom();
        assertEquals("18A",select.guest.roomNum, "Err - No Room associated with guest ID");
    }

    @Test
    void reserveRoomAvailTest() {
        select.reserveRoom();
       assertEquals(false,select.room.available, "Err - Room is showing available as true, should be false");
    }

    @Test
    void reserveRoomReserveIDTest() {
        select.reserveRoom();
        assertEquals(select.guest.gReserveID, select.room.rReserveID, "Err - guest and room Reservation ID do not Match");
    }


    @Test
    void createReservationIDTest(){
        assertEquals(false,select.createReservationID().isEmpty(), "No reservation ID");
    }

}