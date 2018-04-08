package edu.ithaca.bhamula1.hotel;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by Ben on 3/23/2018.
 */
public class HotelTest {

    @Test
    void checkIn() {
        Hotel myHotel = new Hotel();
        //myHotel.getRoom(1);
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("John Doe","4321");
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
        //myHotel.getRoom(1);
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("John Doe","4321");
        myHotel.getRoom(1).setReservationName(customer1.getName());
        customer1.setRoom(1);
        assertEquals(false,myHotel.checkOut(1,customer1));
        myHotel.checkIn(1,customer1);
        assertEquals(false,myHotel.checkOut(2,customer1));
        assertEquals(false,myHotel.checkOut(1,customer2));
        assertEquals(true,myHotel.checkOut(1,customer1));
    }



    @Test
    void viewOrderedAvailableRooms(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");


        Assert.assertEquals("Room: 1 Type: 2 double bed(s) Amenities: mini bar Price: $100.0 Available: true\n" +
                "Room: 2 Type: 2 double bed(s) Amenities: mini bar Price: $100.0 Available: true\n" +
                "Room: 4 Type: 2 double bed(s) Amenities: mini bar Price: $100.0 Available: true", hotel.viewOrderedAvailableRooms());
        String st = hotel.viewOrderedAvailableRooms();

    }


    @Test
    void getRoom() {
    }

    @Test
    void addTestRoom() {
    }

    @Test
    void addRoom() {
    }

    @Test
    void setNumberOfRooms() {
    }

    @Test
    void getRooms() {
    }

    @Test
    void logIn() {
    }

    @Test
    void getCustomer() {
    }

    @Test
    void getCustomer1() {
    }

    @Test
    void createAccount() {
    }

    @Test
    void checkValidCust() {
    }

    @Test
    void checkRooms() {
    }

    @Test
    void setCustomer() {
    }


    /**
     * Testing length/number of Employees
     */
    @Test
    void emplListTest() {
        Hotel h = new Hotel();
        int length = h.getEList().size();
        System.out.println(" num empls "+ length);
        h.printEmployeeList();

    }

    /**
     * Testing empl login
     */
    @Test
    void emplLogInTest(){
        Hotel h = new Hotel();

        assertTrue("False - Expected True for hotel.checkEmployeeLogin", h.checkEmployeeLogIn("mogrady","Password4321"));
        assertFalse("True - Expected False for hotel.checkEmployeeLogin", h.checkEmployeeLogIn("ogrady","Password4321"));
        assertFalse("True - Employee List is empty - invalid",h.getEList().isEmpty());


    }
}

