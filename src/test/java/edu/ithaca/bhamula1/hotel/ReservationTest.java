package edu.ithaca.bhamula1.hotel;


import org.junit.jupiter.api.Test;

import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ReservationTest {


    @Test
    void emptyConstructorTest() {
        Reservation rev = new Reservation();

        Assert.assertEquals(rev.getCustomer().getName(), "");
        Assert.assertEquals(rev.getRoom().getRoomNumber(), 0);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);
        Assert.assertEquals(rev.getNightDurration(), -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
        Assert.assertEquals(dateFormat.format(rev.getCheckInDate().getTime()), "01/01/001");
    }

    @Test
    void ConstructorTest() {
        Customer cus = new Customer("Mia", "Kimmich Mitchell", "miakimmichmitchell", 1, false);
        Room room = new Room(false, 1, 90,1, "full", "things");

        GregorianCalendar theDate = new GregorianCalendar(2018,Calendar.APRIL, 15);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

        Reservation rev = new Reservation(cus, room, theDate, 2 );

        Assert.assertEquals(rev.getCustomer().getName(), "Mia Kimmich Mitchell");
        Assert.assertEquals(rev.getRoom().getRoomNumber(), 1);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);
        Assert.assertEquals(rev.getNightDurration(), 2);
        Assert.assertEquals(dateFormat.format(rev.getCheckInDate().getTime()), "04/15/2018");



    }




}
