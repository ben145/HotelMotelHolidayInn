package edu.ithaca.bhamula1.hotel;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class RoomTest {



    //available, room number,price, number of beds,  type of bed,  extras, view

    @Test
    void setRoomTest() {
        //available, room number,price, type of bed,  extras, view
        Room exampleRoom = new Room();
        exampleRoom.setIfAvailable(true);
        exampleRoom.setRoomNumber(12);
        exampleRoom.setRoomPrice(500);
        exampleRoom.setBedCount(1);
        exampleRoom.setBedType("Queen");
        exampleRoom.addAmenities("mini bar");
        exampleRoom.setIfView(true);
    }


    @Test
    void getRoomTest() {
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar", true);
        Assert.assertEquals(true, exampleRoom.getIfAvailable());
        Assert.assertEquals(12, exampleRoom.getRoomNumber());
        Assert.assertEquals(1, exampleRoom.getBedCount);
        Assert.assertEquals(500, exampleRoom.getRoomPrice());
        Assert.assertEquals("", exampleRoom.getBedType());
        Assert.assertEquals(true, exampleRoom.getIfView());
        Assert.assertEquals("", exampleRoom.getAmenities());
    }



    @Test
    void RoomConstructorTest() {
        //available, room number,price, number of beds,  type of bed,  extras, view
        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar", true);
        Assert.assertEquals(true, exampleRoom.getIfAvailable());
        Assert.assertEquals(12, exampleRoom.getRoomNumber());
        Assert.assertEquals(1, exampleRoom.getBedCount);
        Assert.assertEquals(500, exampleRoom.getRoomPrice());
        Assert.assertEquals("", exampleRoom.getBedType());
        Assert.assertEquals("", exampleRoom.getAmenities());
        Assert.assertEquals(true, exampleRoom.getIfView());
    }


}
