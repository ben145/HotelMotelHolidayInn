package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeUITest {

    EmployeeUI eUI = new EmployeeUI();
    EmployeeIMPL empl = new EmployeeIMPL(3,"Front Desk Associate","Brown-McMillin","Benjamin",
            "bbrownmcmillin","Password4321",false,false,false);
    EmployeeIMPL empl2 = new EmployeeIMPL(0,null,null,null,
            null,null,false,false,true);

    @Test
    void setEmployee() {
        eUI.setEmployee(empl);
        assertEquals("Front Desk Associate",eUI.getEmplTitle(),"FAIL - no employee stored");
        eUI.setEmployee(empl2);
        assertEquals(null,eUI.getEmplTitle(),"FAIL - this is not valid employee");
    }

    @Test
    void uiInteraction() {
    }

    @Test
    void employeeLogScreenUI() {
    }

    @Test
    void checkEmployeeLogIn() {
    }

    @Test
    void viewOpenRequestsTest() {
        Hotel testH = new Hotel();
        for(int addR = 0; addR < 3; addR++) {
            ActiveRequest testR = new ActiveRequest("Room service", 250 + addR);
            ActiveRequest testR1 = new ActiveRequest("Bring every pillow you have", 13 + addR);
            ActiveRequest testR3 = new ActiveRequest("Refill minibar", 2 + addR);
            Hotel.activeRequests.add(testR);
            Hotel.activeRequests.add(testR1);
            Hotel.activeRequests.add(testR3);
        }
        eUI.viewOpenRequests();
        assertEquals(9, Hotel.activeRequests.size(),"Error - There should be 9 requests in activeRequests");
        assertEquals("Room service", Hotel.activeRequests.get(3).request, "Error - should be Room service");
        assertEquals(251, Hotel.activeRequests.get(3).roomNumber, "Error - should be 251");
        assertEquals("Refill minibar", Hotel.activeRequests.get(8).request, "Error - should be Refill minibar");
        assertEquals(4, Hotel.activeRequests.get(8).roomNumber, "Error - should be 4");

    }

    @Test
    void viewOpenRequestsTest1() {
        Hotel testH = new Hotel();
        eUI.viewOpenRequests();
        assertEquals(0, Hotel.activeRequests.size(), "Error - There should be no requests in activeRequests");
    }


    @Test
    void takeRequests() {
    }

    @Test
    void completeRequest() {
    }

    @Test
    void closeUI() {
    }
}