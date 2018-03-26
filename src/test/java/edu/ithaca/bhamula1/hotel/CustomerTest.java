package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {




















































































    

    @Test
    void checkIn() {
        Customer brad = new Customer("Brad", "Keith");
        brad.fakeReserveRoom(1);
        assertEquals(false,brad.checkIn(2));
        assertEquals(false,brad.getCheckedIn());
        assertEquals(true,brad.checkIn(1));
        assertEquals(true,brad.getCheckedIn());
    }

    @Test
    void checkOut() {
        Customer brad = new Customer("Brad", "Keith");
        brad.fakeReserveRoom(1);
        brad.checkIn(1);
        assertEquals(false,brad.checkOut(2));
        assertEquals(true,brad.getCheckedIn());
        assertEquals(true,brad.checkOut(1));
        assertEquals(false,brad.getCheckedIn());
    }
}