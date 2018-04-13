package edu.ithaca.bhamula1.hotel;


import org.junit.jupiter.api.Test;

import org.junit.Assert;

public class ReservationTest {


    @Test
    void emptyConstructorTest() {
        Reservation rev = new Reservation();

        Assert.assertEquals(rev.getCustomer().getName(), "");
        Assert.assertEquals(rev.getRoom().getRoomNumber(), 0);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);
    }

    @Test
    void ConstructorTest() {
        Customer cus = new Customer("Mia", "Kimmich Mitchell", "miakimmichmitchell", 1, false);
        Room room = new Room(false, 1, 90,1, "full", "things");
        Reservation rev = new Reservation(cus, room);


        Assert.assertEquals(rev.getCustomer().getName(), "Mia Kimmich Mitchell");
        Assert.assertEquals(rev.getRoom().getRoomNumber(), 1);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);


    }




}
