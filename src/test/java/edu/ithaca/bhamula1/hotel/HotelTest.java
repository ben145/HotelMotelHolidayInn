package edu.ithaca.bhamula1.hotel;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Ben on 3/23/2018.
 */
public class HotelTest {

    @Test
    void checkIn() {
        Hotel myHotel = new Hotel(true);
        //myHotel.getRoom(1);
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("John Doe","4321");

        myHotel.addReservation(customer1,myHotel.getRoom(2),
                new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)),2, "");


        for(int rr = 0; rr< myHotel.getReservations().size(); rr++){
            System.out.println(myHotel.getReservations().get(rr).toString());
        }
        assertEquals(false,myHotel.checkIn(1,customer2));
        assertEquals(false,myHotel.checkIn(1,customer1));
        assertEquals(true, myHotel.checkIn(2,customer1));
        //assertEquals(true,myHotel.getRoom(1).getCheckedIn());
        //assertEquals(customer1.isCheckedIn(),true);
    }

    @Test
    void checkOut() {
        Hotel myHotel = new Hotel(true);
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
        Hotel hotel = new Hotel(true);
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(1, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(4, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(3, false, 100,2, "double", "mini bar", false);

        String test = " Room number: 1\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n\n"+
                " Room number: 2\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n\n"+
                " Room number: 3\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n\n"+
                " Room number: 4\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n";

        Assert.assertEquals(test, hotel.viewOrderedRooms(false));
        String st = hotel.viewOrderedRooms(false);

    }

    @Test
    void viewOrderedAvailableRoomsNotReturningTest(){

        Hotel hotel = new Hotel(true);
        hotel.setNumberOfRooms(5);
        Customer customer = new Customer("Mia", "Kimmich", "mk", -1, false);
        Calendar checkIN = new GregorianCalendar(1,1-1,1);
        Calendar checkDate = new GregorianCalendar(1,1-1,1);

        hotel.addRoom(2, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(1, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(4, true, 100,2, "double", "mini bar", false);
        hotel.addRoom(3, true, 100,2, "double", "mini bar", false);


        //this all happens when the user reserves a room in the ui
        hotel.getRoom(2).addReservation(checkDate,2);

        hotel.addReservation(customer, hotel.getRoom(2), checkIN, 2, "" );

//        System.out.println(hotel.getReservations());
            String test = " Room number: 1\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n\n"+
            " Room number: 3\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n\n"+
            " Room number: 4\n\tType: 2 double bed(s)\n\tAmenities: mini bar\n\tPrice: $100.0\n";

        //looking for a hotel starting on 1/1/1 for 3 nights
        Assert.assertEquals(hotel.viewOrderedAvailableRooms(checkDate, 3, false),test);
    }

    @Test
    void viewOrderedAvailableRoomsReturningTest(){
        Hotel hotel = new Hotel(true);
        hotel.setNumberOfRooms(5);
        Customer customer = new Customer("Mia", "Kimmich", "mk", -1, false);
        Calendar checkIn = new GregorianCalendar(1,1-1,1);
        Calendar checkDate = new GregorianCalendar(1,1-1,1);

        hotel.addRoom(1, true, 100,2, "double", "mini bar",false);
        hotel.addRoom(4, true, 100,2, "double", "mini bar",false);
        hotel.addRoom(3, true, 100,2, "double", "mini bar",false);
        hotel.addRoom(2, true, 100,2, "double", "mini bar",false);

        //this all happens when the user reserves a room in the ui
       // hotel.getRoom(2).addReservation(checkDate,2);
       hotel.addReservation(customer, hotel.getRoom((2-1)), checkIn, 2, "" );

        String test = "Room: 1 Type: 2 double bed(s) Amenities: mini bar Price: $90.0\n"+
                "Room: 2 Type: 2 double bed(s) Amenities: mini bar Price: $90.0\n"+
                "Room: 3 Type: 2 double bed(s) Amenities: mini bar Price: $90.0\n"+
                "Room: 4 Type: 2 double bed(s) Amenities: mini bar Price: $90.0";

        //looking for a hotel starting on 1/1/1 for 3 nights
        Assert.assertEquals(hotel.viewOrderedAvailableRooms(checkDate, 3, true), test);
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
        System.out.println(" num rms "+ h.getRooms().size());

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
        Hotel myHotel = new Hotel(true);
        Customer customer1 = new Customer("Brad Keith","1234");
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        Calendar today = Calendar.getInstance();
        myHotel.addReservation(customer1,myHotel.getRoom(1), today,2, "1234567890987");
        myHotel.addReservation(customer1,myHotel.getRoom(2), today,2, "1234567890987");
        assertEquals(customer1.getName(),myHotel.getReservation(customer1,1,today).getCustomer().getName());
        assertEquals(1,myHotel.getReservation(customer1,1,today).getRoom().getRoomNumber());
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

    @Test
    void getCustomerReservationsTest(){
        Hotel myHotel = new Hotel(true);
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("Bill Joe","5678");
        myHotel.setNumberOfRooms(5);
        for(int i = 1; i<5; i++){
            myHotel.addTestRoom(i);
        }
        List<Calendar> dates1 = new ArrayList<>();
        List<Calendar> dates2 = new ArrayList<>();
        for(int i = 0; i<3; i++){
            dates1.add(new GregorianCalendar());
        }
        for(int i = 0; i<2; i++){
            dates2.add(new GregorianCalendar());
        }
        dates1.get(2).set(2019,6,23);
        myHotel.addReservation(customer1,myHotel.getRoom(1),dates1.get(2),2,"1234567890987");
        dates1.get(1).set(2019,6,20);
        myHotel.addReservation(customer1,myHotel.getRoom(2),dates1.get(1),2,"1234567890987");
        dates2.get(1).set(2021,6,7);
        myHotel.addReservation(customer2,myHotel.getRoom(1),dates2.get(1),2,"1234567890987");
        dates2.get(0).set(2018,2,7);
        myHotel.addReservation(customer2,myHotel.getRoom(1),dates2.get(0),2,"1234567890987");
        dates1.get(0).set(2018,8,24);
        myHotel.addReservation(customer1,myHotel.getRoom(1),dates1.get(0),2,"1234567890987");
        List<Reservation> c1Res = myHotel.getCustomerReservations(customer1);
        Iterator<Reservation> itr1 = c1Res.iterator();
        Reservation previous = itr1.next();
        int ndx = 0;
        assertEquals(customer1,previous.getCustomer());
        assertEquals(dates1.get(ndx),previous.getCheckInDate());
        assertEquals(2,previous.getNightDurration());
        Reservation curr;
        while(itr1.hasNext()){
            curr = itr1.next();
            ndx++;
            assertEquals(customer1,curr.getCustomer());
            assertEquals(dates1.get(ndx),curr.getCheckInDate());
            assertEquals(2,curr.getNightDurration());
            Comparator<Reservation> cmp = new CompareReservationByDate() {
                @Override
                public int compare(Reservation r1, Reservation r2) {
                    return super.compare(r1, r2);
                }
            };
            assertEquals(-1,cmp.compare(previous,curr));
            previous = curr;
        }
        List<Reservation> c2Res = myHotel.getCustomerReservations(customer2);
        Iterator<Reservation> itr2 = c2Res.iterator();
        previous = itr2.next();
        ndx = 0;
        assertEquals(customer2,previous.getCustomer());
        assertEquals(dates2.get(ndx),previous.getCheckInDate());
        assertEquals(2,previous.getNightDurration());
        while(itr2.hasNext()){
            curr = itr2.next();
            ndx++;
            assertEquals(customer2,curr.getCustomer());
            assertEquals(dates2.get(ndx),curr.getCheckInDate());
            assertEquals(2,curr.getNightDurration());
            Comparator<Reservation> cmp = new CompareReservationByDate() {
                @Override
                public int compare(Reservation r1, Reservation r2) {
                    return super.compare(r1, r2);
                }
            };
            assertEquals(-1,cmp.compare(previous,curr));
            previous = curr;
        }
    }



}

