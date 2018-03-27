package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void checkIn() {
        customer brad = new customerImpl("Brad Keith", "1234");
        assertEquals(false,brad.checkIn(2));
        brad.setRoom(1);
        assertEquals(false,brad.checkIn(2));
        assertEquals(false,brad.isCheckedIn());
        assertEquals(true,brad.checkIn(1));
        assertEquals(true,brad.isCheckedIn());
    }


    @Test
    void checkOut() {
        customer brad = new customerImpl("Brad Keith", "1234");
        brad.setRoom(1);
        assertEquals(false,brad.checkOut(2));
        brad.checkIn(1);
        assertEquals(false,brad.checkOut(2));
        assertEquals(true,brad.isCheckedIn());
        assertEquals(true,brad.checkOut(1));
        assertEquals(false,brad.isCheckedIn());
    }

}