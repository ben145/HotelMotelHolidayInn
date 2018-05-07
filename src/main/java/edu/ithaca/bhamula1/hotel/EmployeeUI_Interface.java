package edu.ithaca.bhamula1.hotel;

public interface EmployeeUI_Interface {


    String getEmplTitle();
    void uiInteraction(HotelInterface h);
    void checkEmployeeLogIn(String el, String epwd);
    void employeeLogScreenUI();
    void viewOpenRequests();
    void takeRequests(int req);
    boolean checkEmpReq();
    void completeRequest(int req);
    void closeUI();


}
