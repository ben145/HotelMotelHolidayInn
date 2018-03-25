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
    void selectRoomTest1() {

        boolean has_GID;
        if(select.guest.guestID !=null){
            has_GID = true;
        }else{
            has_GID = false;
        }
        assertEquals(true, has_GID, "No Guest/Customer ID- cannot select room to reserve without c/g-ID");
    }

    /**
     * Testing for non null room number
     */
    @Test
    void selectRoomTest2() {
        boolean has_RNUM;
        if(select.room.rmNum !=null){
            has_RNUM= true;
        }else{
            has_RNUM = false;
        }
        assertEquals(true, has_RNUM, "No Room number- cannot select room with no room number");
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