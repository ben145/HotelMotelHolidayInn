package edu.ithaca.bhamula1.hotel;

public interface RequestsInterface {

    //String getActiveRequest();

    //String getActiveRequestEmployeeId();
    //boolean requestActive();
    //void setActiveRequest(String newRequest);
    //void setRequestRoomNumber(int newRoomNumber);
    //void setActiveRequestEmployeeId(String newEmployeeId);
    //void activateRequest();


    /*request functions*/

    void loadRecs();

    void viewRequests();

    //employees can add possibly requests to the system
    void addRequest();

    //employees can remove a possible request from the system
    void removeRequest();

    //a customer checked in to a room can make a request
    String makeRequest(int roomNumber);
}
