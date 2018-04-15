package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestsTest {

    /*active requests*/

    ActiveRequest act = new ActiveRequest("Get these tests to pass",666);
    @Test
    void testGetActiveRequest(){
        assertEquals("Get these tests to pass", act.getActiveRequest(), "FAIL - request name not stored");
    }
    @Test
    void testGetRequestRoomNumber(){
        assertEquals(666, act.getRequestRoomNumber(),"FAIL - room number not stored");
    }
    @Test
    void testGetActiveRequestEmployeeId(){
        assertEquals("", act.getActiveRequestEmployeeId(),"FAIL - unknown employee ID");
    }
    @Test
    void testRequestActive(){
        assertEquals(false, act.requestActive(),"FAIL - request prematurely active");
    }
    @Test
    void testSetActiveRequest(String newRequest){
        act.setActiveRequest("now test the setters");
        assertEquals("now test the setters", act.getActiveRequest(), "FAIL - new request name not stored");
    }
    @Test
    void testSetRequestRoomNumber(int newRoomNumber){
        act.setRequestRoomNumber(69);
        assertEquals(69, act.getRequestRoomNumber(), "FAIL - new room number not stored");
    }
    @Test
    void testSetActiveRequestEmployeeId(String newEmployeeId){
        act.setActiveRequestEmployeeId("867-5309");
        assertEquals("867-5309", act.getActiveRequestEmployeeId(),"FAIL - employee ID not stored");
    }
    @Test
    public void testActivateRequest(){
        act.activateRequest();
        assertEquals(true, act.requestActive(),"FAIL - request activation not stored");
    }

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