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
    void selectRoomTest3() {
    }
    @Test
    void selectRoomTest4() {
    }





    @Test
    void cancelRoom() {
    }

    @Test
    void reserveRoom() {
    }

    @Test
    void cancelReserve() {
    }
}