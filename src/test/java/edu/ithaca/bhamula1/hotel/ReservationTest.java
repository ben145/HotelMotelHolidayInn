package edu.ithaca.bhamula1.hotel;


import org.junit.jupiter.api.Test;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {


    @Test
    void emptyConstructorTest() {

        Reservation rev = new Reservation();
        Assert.assertEquals(rev.getCustomer().getName, "");
        Assert.assertEquals(rev.getRoom().getRoomNumeber(), 0);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);


    }

    @Test
    void ConstructorTest() {
        Customer cus = new Customer();
        Room room = new Room(true, 1, 90,1, "full", "things");


        Reservation rev = new Reservation();
        Assert.assertEquals(rev.getCustomer().getName, "");
        Assert.assertEquals(rev.getRoom().getRoomNumeber(), 0);
        Assert.assertEquals(rev.getRoom().getIfAvailable(), false);


    }




}
