package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by Ben on 3/23/2018.
 */
public class HotelTest {



























































    @Test
    void checkIn() {
        Hotel myHotel = new Hotel();
        myHotel.setupTestHotel();
        Customer customer1 = new Customer("Brad","Keith");
        Customer customer2 = new Customer("John","Doe");
        myHotel.fakeReserveRoom(1,"Keith");
        customer1.fakeReserveRoom(1);
        assertEquals(false,myHotel.checkIn(1,customer2));
        assertEquals(false,myHotel.checkIn(2,customer1));
        assertEquals(true, myHotel.checkIn(1,customer1));
    }

    @Test
    void checkOut() {
        Hotel myHotel = new Hotel();
        myHotel.setupTestHotel();
        Customer customer1 = new Customer("Brad","Keith");
        Customer customer2 = new Customer("John","Doe");
        myHotel.fakeReserveRoom(1,"Keith");
        customer1.fakeReserveRoom(1);
        assertEquals(false,myHotel.checkOut(1,customer1));
        myHotel.checkIn(1,customer1);
        assertEquals(false,myHotel.checkOut(2,customer1));
        assertEquals(false,myHotel.checkOut(1,customer2));
        assertEquals(true,myHotel.checkOut(1,customer1));
    }

}