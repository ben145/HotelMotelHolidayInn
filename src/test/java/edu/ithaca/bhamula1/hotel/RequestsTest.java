package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RequestsTest {

    /*active requests*/

    ActiveRequest act = new ActiveRequest("Get these tests to pass",666);
    @Test
    void testGetActiveRequest(){ assertEquals("Get these tests to pass", act.getActiveRequest(), "FAIL - request name not stored"); }
    @Test
    void testGetRequestRoomNumber(){
        assertEquals(666, act.getRequestRoomNumber(),"FAIL - room number not stored");
    }
    @Test
    void testGetActiveRequestEmployeeId(){ assertEquals("", act.getActiveRequestEmployeeId(),"FAIL - unknown employee ID"); }
    @Test
    void testRequestActive(){
        assertEquals(false, act.requestActive(),"FAIL - request prematurely active");
    }
    @Test
    void testSetActiveRequest(){
        act.setActiveRequest("now test the setters");
        assertEquals("now test the setters", act.getActiveRequest(), "FAIL - new request name not stored");
    }
    @Test
    void testSetRequestRoomNumber(){
        act.setRequestRoomNumber(69);
        assertEquals(69, act.getRequestRoomNumber(), "FAIL - new room number not stored");
    }
    @Test
    void testSetActiveRequestEmployeeId(){
        act.setActiveRequestEmployeeId("867-5309");
        assertEquals("867-5309", act.getActiveRequestEmployeeId(),"FAIL - employee ID not stored");
    }
    @Test
    public void testActivateRequest(){
        act.activateRequest();
        assertEquals(true, act.requestActive(),"FAIL - request activation not stored");
    }


    /*room service objects*/
    RoomService test2 = new RoomService("she sells seashells by the seashore", 19.99, 0);
    @Test
    void testGetRequestName(){
        assertEquals("she sells seashells by the seashore", test2.getRequestName(), "FAIL - request name not stored");
    }
    @Test
    void testGetAssociatedPrice(){
        assertEquals(19.99, test2.getAssociatedPrice(), "FAIL - cost not stored");
    }
    @Test
    void testGetNumRequirements(){
        assertEquals(0, test2.getNumRequirements(), "FAIL - number of requirements not stored");
    }
    @Test
    void testGetRequirements(){
        assertEquals(null, test2.getRequirements(), "FAIL - requirements not stored");
    }
    @Test
    void testSetRequestName(){
        test2.setRequestName("ice");
        assertEquals("ice", test2.getRequestName(),"FAIL - unknown request name");
    }
    @Test
    void testSetAssociatedPrice(){
        test2.setAssociatedPrice(.82);
        assertEquals(.82, test2.getAssociatedPrice(), "FAIL - unknown associated cost");
    }
    @Test
    void testAddRequirement(){
        test2.addRequirement("ice");
        ArrayList<String> temp = new ArrayList<>();
        temp.add("ice");
        assertEquals(temp, test2.getRequirements(), "FAIL - additional request failed to store");
    }
    @Test
    void testRemoveRequirement(){
        test2.addRequirement("ice");
        test2.removeRequirement("ice");
        assertEquals(null, test2.getRequirements(), "FAIL - request not removed");
    }


    /*requests originale*/
    @Test
    void testLoadRecs() {
    }

    @Test
    void testAddRequest() {
    }

    @Test
    void testRemoveRequest() {
    }

    @Test
    void testViewRequests() {
    }
    @Test
    void testMakeRequest(){

    }
}