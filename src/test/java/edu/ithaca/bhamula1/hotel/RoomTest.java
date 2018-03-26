package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void checkIn() {
        Room one = new Room(1);
        one.fakeReserveRoom("Keith");
        Customer customer1 = new Customer("Brad","Keith");
        Customer customer2 = new Customer("John","Doe");
        assertEquals(false, one.checkIn(customer2));
        assertEquals(true,one.checkIn(customer1));
    }

    @Test
    void checkOut() {
    }
}