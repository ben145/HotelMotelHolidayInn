package edu.ithaca.bhamula1.hotel;

public interface EmployeeUI_Interface {


    void uiInteraction(HotelInterface h);
    void checkEmployeeLogIn(String el, String epwd);
    void employeeLogScreenUI();
    void viewOpenRequests();
    void takeRequests();
    void completeRequest();
    void closeUI();


}
