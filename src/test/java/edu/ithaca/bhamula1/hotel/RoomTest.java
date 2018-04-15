package edu.ithaca.bhamula1.hotel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
class RoomTest {


    @Test
    void checkIn() {
        Room one = new Room(false,1,100.00,2,"Full","Mini bar");
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("John Doe","4321");
        one.setReservationName(customer1.getName());
        assertEquals(false, one.checkIn(customer2));
        assertEquals(true,one.checkIn(customer1));
        assertEquals(true,one.getCheckedIn());
        assertEquals(customer1.getName(),one.reservationName);
    }

    @Test
    void checkOut() {
        Room one = new Room(false,1,100.00,2,"Full","Mini bar");
        Customer customer1 = new Customer("Brad Keith","1234");
        Customer customer2 = new Customer("John Doe","4321");
        one.setReservationName(customer1.getName());
        assertEquals(false,one.checkOut(customer1));
        one.checkIn(customer1);
        assertEquals(false,one.checkOut(customer2));
        assertEquals(true,one.checkOut(customer1));
        assertEquals(false,one.checkIn(customer1));
        assertEquals(null,one.getReservationName());
    }


    //available, room number,price, number of beds,  type of bed,  extras

    //to test doubles
    double range = 0.01;


    @Test
    void setAndGetandEmptyConstructorRoomTest() {
        //available, room number,price, type of bed,  extras
        Room exampleRoom = new Room();

        Assert.assertEquals(false, exampleRoom.getIfAvailable());
        Assert.assertEquals(0, exampleRoom.getRoomNumber());
        Assert.assertEquals(0, exampleRoom.getBedCount());
        Assert.assertEquals(0, exampleRoom.getRoomPrice(), range);
        Assert.assertEquals("", exampleRoom.getBedType());
        Assert.assertEquals("", exampleRoom.getAmenities());



        exampleRoom.setIfAvailable(true);
        exampleRoom.setRoomNumber(12);
        exampleRoom.setRoomPrice(500);
        exampleRoom.setBedCount(1);
        exampleRoom.setBedType("queen");
        exampleRoom.addAmenities("mini bar");
        exampleRoom.addAmenities("view");
        exampleRoom.addAmenities("tv");


        Assert.assertEquals(true, exampleRoom.getIfAvailable());
        Assert.assertEquals(12, exampleRoom.getRoomNumber());
        Assert.assertEquals(1, exampleRoom.getBedCount());
        Assert.assertEquals(500, exampleRoom.getRoomPrice(), range);
        Assert.assertEquals("queen", exampleRoom.getBedType());
        Assert.assertEquals("mini bar, view, tv", exampleRoom.getAmenities());
    }






    @Test
    void roomConstructorTest() {
        //available, room number,price, number of beds,  type of bed,  extras, view
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");
        Assert.assertEquals(true, exampleRoom.getIfAvailable());
        Assert.assertEquals(212, exampleRoom.getRoomNumber());
        Assert.assertEquals(1, exampleRoom.getBedCount());
        Assert.assertEquals(500, exampleRoom.getRoomPrice(), range);
        Assert.assertEquals("queen", exampleRoom.getBedType());
        Assert.assertEquals("mini bar", exampleRoom.getAmenities());
    }


    @Test
    void toStringTest(){
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar, view");

        Assert.assertEquals("Room: 212 Type: 1 queen bed(s) Amenities: mini bar, view Price: $500.0 Available: true", exampleRoom.toString());

    }


    @Test
    public void addReservationTest(){
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");

        Calendar firstRes = new GregorianCalendar(2018, Calendar.APRIL, 15);
        exampleRoom.addReservation(firstRes, 1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

        List<Calendar> notAvailDays = exampleRoom.getNotAvailTheseDays();
        Assert.assertEquals(dateFormat.format(notAvailDays.get(0).getTime()), "04/15/2018" );


        List<Calendar> notAvailDays2 = exampleRoom.getNotAvailTheseDays();
        Calendar secondRes = new GregorianCalendar(2018, Calendar.APRIL, 5);
        exampleRoom.addReservation(secondRes, 3);

        Assert.assertEquals(dateFormat.format(notAvailDays.get(0).getTime()), "04/05/2018" );
        Assert.assertEquals(dateFormat.format(notAvailDays.get(1).getTime()), "04/06/2018" );
        Assert.assertEquals(dateFormat.format(notAvailDays.get(2).getTime()), "04/07/2018" );


//        for(int i=0; i<notAvailDays2.size(); i++){
//            System.out.println(dateFormat.format(notAvailDays2.get(i).getTime()));
//        }

    }


    @Test
    public void conflictingReservationDates(){
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");

        Calendar firstRes = new GregorianCalendar(2018, Calendar.APRIL, 15);
        exampleRoom.addReservation(firstRes, 2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

        List<Calendar> notAvailDays = exampleRoom.getNotAvailTheseDays();
        Assert.assertEquals(dateFormat.format(notAvailDays.get(0).getTime()), "04/15/2018" );
        Assert.assertEquals(dateFormat.format(notAvailDays.get(1).getTime()), "04/16/2018" );


        List<Calendar> notAvailDays2 = exampleRoom.getNotAvailTheseDays();
        Calendar secondRes = new GregorianCalendar(2018, Calendar.APRIL, 14);
        exampleRoom.addReservation(secondRes, 3);

        Assert.assertEquals(dateFormat.format(notAvailDays2.get(0).getTime()), "04/15/2018" );
        Assert.assertEquals(dateFormat.format(notAvailDays2.get(1).getTime()), "04/16/2018" );

    }


    @Test
    public void canReserveTestGood(){
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");

        Calendar firstRes = new GregorianCalendar(2018, Calendar.APRIL, 15);
        exampleRoom.addReservation(firstRes, 1);

        exampleRoom.canReserve(firstRes, 1);



        Assert.assertEquals(exampleRoom.canReserve(firstRes, 1), true );

        Calendar secondRes = new GregorianCalendar(2018, Calendar.APRIL, 5);
        exampleRoom.addReservation(secondRes, 3);

        Assert.assertEquals(exampleRoom.canReserve(secondRes, 3), true );



    }



    @Test
    public void canReserveTestBAD(){
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");

        Calendar firstRes = new GregorianCalendar(2018, Calendar.APRIL, 15);
        exampleRoom.addReservation(firstRes, 1);

        Assert.assertEquals(exampleRoom.canReserve(firstRes, 1), true );


        Calendar secondRes = new GregorianCalendar(2018, Calendar.APRIL, 14);
        exampleRoom.addReservation(secondRes, 3);
        Assert.assertEquals(exampleRoom.canReserve(secondRes,3), false );

//        Calendar thirdRes = new GregorianCalendar(2018, Calendar.APRIL, 15);
//        Assert.assertEquals(exampleRoom.canReserve(thirdRes,3), false );
//

    }


}
