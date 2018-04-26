package edu.ithaca.bhamula1.hotel;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        //myHotel.getRoom(1).setReservationName(customer1.getName());
        myHotel.addReservation(customer1,myHotel.getRoom(1), new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH)),2, "");
        assertEquals(false,myHotel.checkIn(1,customer2));
        assertEquals(false,myHotel.checkIn(2,customer1));
        assertEquals(true, myHotel.checkIn(1,customer1));
        //assertEquals(true,myHotel.getRoom(1).getCheckedIn());
        //assertEquals(customer1.isCheckedIn(),true);
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
        //myHotel.getRoom(1).setReservationName(customer1.getName());
        customer1.setRoom(1);
        myHotel.addReservation(customer1,myHotel.getRoom(1), Calendar.getInstance(),2, " ");
        //assertEquals(false,myHotel.checkOut(1,customer1));
        boolean poop = myHotel.checkIn(1,customer1);
        //assertEquals(false,myHotel.checkOut(2,customer1));
        //assertEquals(false,myHotel.checkOut(1,customer2));
        assertEquals(true,myHotel.checkOut(1,customer1));
    }



    @Test
    void viewOrderedRooms(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");


        Assert.assertEquals("Room: 1 Type: 2 double bed(s) Amenities: mini bar Price: $100.0\n" +
                "Room: 2 Type: 2 double bed(s) Amenities: mini bar Price: $100.0\n" +
                "Room: 3 Type: 2 double bed(s) Amenities: mini bar Price: $100.0\n"+
                "Room: 4 Type: 2 double bed(s) Amenities: mini bar Price: $100.0", hotel.viewOrderedRooms(false));
        String st = hotel.viewOrderedRooms(false);

    }

    @Test
    void viewOrderedAvailableRoomsNotReturningTest(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);
        Customer customer = new Customer("Mia", "Kimmich", "mk", -1, false);
        Calendar checkIN = new GregorianCalendar(1,1-1,1);
        Calendar checkDate = new GregorianCalendar(1,1-1,1);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");

        //this all happens when the user reserves a room in the ui
        hotel.getRoom(2).addReservation(checkDate,2);
        hotel.addReservation(customer, hotel.getRoom(2), checkIN, 2, "" );

//        System.out.println(hotel.getReservations());

        //looking for a hotel starting on 1/1/1 for 3 nights
        Assert.assertEquals(hotel.viewOrderedAvailableRooms(checkDate, 3, false),
                "Room: 1 Type: 2 double bed(s) Amenities: mini bar Price: $100.0\n" +
                        "Room: 3 Type: 2 double bed(s) Amenities: mini bar Price: $100.0\n" +
                        "Room: 4 Type: 2 double bed(s) Amenities: mini bar Price: $100.0");

    }



    @Test
    void viewOrderedAvailableRoomsReturningTest(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);
        Customer customer = new Customer("Mia", "Kimmich", "mk", -1, false);
        Calendar checkIn = new GregorianCalendar(1,1-1,1);
        Calendar checkDate = new GregorianCalendar(1,1-1,1);

        hotel.addRoom(2, true, 50,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 50,2, "double", "mini bar");

        //this all happens when the user reserves a room in the ui
        hotel.getRoom(2).addReservation(checkDate,2);
        hotel.addReservation(customer, hotel.getRoom(2), checkIn, 2, "" );

        //looking for a hotel starting on 1/1/1 for 3 nights
        Assert.assertEquals(hotel.viewOrderedAvailableRooms(checkDate, 3, true),
                "Room: 1 Type: 2 double bed(s) Amenities: mini bar Price: $90.0\n" +
                        "Room: 3 Type: 2 double bed(s) Amenities: mini bar Price: $45.0\n" +
                        "Room: 4 Type: 2 double bed(s) Amenities: mini bar Price: $90.0");


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

//        assertTrue("False - Expected True for hotel.checkEmployeeLogin", h.checkEmployeeLogIn("mogrady","Password4321"));
//        assertFalse("True - Expected False for hotel.checkEmployeeLogin", h.checkEmployeeLogIn("ogrady","Password4321"));
        assertFalse("True - Employee List is empty - invalid",h.getEList().isEmpty());


    }

    @Test
    void setEmplList() {
    }

    @Test
    void printStaffVacancies() {
    }

    @Test
    void printEmployeeList() {
    }

    @Test
    void printLoggedInEmployeeList() {
    }

    @Test
    void printAvailableEmployeeList() {
    }

    @Test
    void checkEmployeeLogIn() {
    }

    @Test
    void getEList() {
    }

    @Test
    void getReservationTest(){
        Hotel myHotel = new Hotel();
        Customer customer1 = new Customer("Brad Keith","1234");
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        myHotel.addReservation(customer1,myHotel.getRoom(1), Calendar.getInstance(),2, "");
        myHotel.addReservation(customer1,myHotel.getRoom(2), Calendar.getInstance(),2, "");
        assertEquals(customer1.getName(),myHotel.getReservation(customer1,1).getCustomer().getName());
        assertEquals(1,myHotel.getReservation(customer1,1).getRoom().getRoomNumber());
    }



//    @Test
//    void setCustomerCardInformationTest(){
//
//        Hotel hotel = new Hotel();
//        hotel.setNumberOfRooms(5);
//        Customer customer1 = new Customer("Brad Keith","1234");
//        RoomInterface rm = hotel.getRoom(1);
//
//        GregorianCalendar checkIn = new GregorianCalendar(2018,Calendar.APRIL, 1);
//
//
//
//        //    public Reservation (CustomerInterface customer, RoomInterface room, Calendar checkInDate, int nightDurration, String cardPayment){
//        Reservation res = new Reservation(customer1, rm, checkIn, 2, ""  );
//
//
////        Assert.assertEquals(true, hotel.getCardInformation("1234123412341234"));
//
//    }



}

