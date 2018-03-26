package edu.ithaca.bhamula1.hotel;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class RoomTest {
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


//    @Test
//    void getRoomTest() {
//        Room exampleRoom = new Room(true, 212, 500, 1, "queen", "mini bar");
//        Assert.assertEquals(true, exampleRoom.getIfAvailable());
//        Assert.assertEquals(212, exampleRoom.getRoomNumber());
//        Assert.assertEquals(1, exampleRoom.getBedCount());
//        Assert.assertEquals(500, exampleRoom.getRoomPrice(), range);
//        Assert.assertEquals("queen", exampleRoom.getBedType());
//        Assert.assertEquals("mini bar", exampleRoom.getAmenities());
//    }



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

}
