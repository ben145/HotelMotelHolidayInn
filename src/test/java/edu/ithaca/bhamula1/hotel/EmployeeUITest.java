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
    void viewOpenRequests() {
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