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
        myHotel.getRoom(1);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        customer customer1 = new customerImpl("Brad Keith","1234");
        customer customer2 = new customerImpl("John Doe","4321");
        myHotel.getRoom(1).setReservationName(customer1.getName());
        customer1.setRoom(1);
        assertEquals(false,myHotel.checkIn(1,customer2));
        assertEquals(false,myHotel.checkIn(2,customer1));
        assertEquals(true, myHotel.checkIn(1,customer1));
        assertEquals(true,myHotel.getRoom(1).getCheckedIn());
        assertEquals(customer1.isCheckedIn(),true);
    }

    @Test
    void checkOut() {
        Hotel myHotel = new Hotel();
        myHotel.getRoom(1);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        customer customer1 = new customerImpl("Brad Keith","1234");
        customer customer2 = new customerImpl("John Doe","4321");
        myHotel.getRoom(1).setReservationName(customer1.getName());
        customer1.setRoom(1);
        assertEquals(false,myHotel.checkOut(1,customer1));
        myHotel.checkIn(1,customer1);
        assertEquals(false,myHotel.checkOut(2,customer1));
        assertEquals(false,myHotel.checkOut(1,customer2));
        assertEquals(true,myHotel.checkOut(1,customer1));
    }

}