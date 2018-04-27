<<<<<<< HEAD
package edu.ithaca.bhamula1.hotel;

/**
 * 
 */
public interface RequestsInterface {
    /*
    //active request functions
    String getActiveRequest();
    int getRequestRoomNumber();
    String getActiveRequestEmployeeId();
    boolean requestActive();
    void setActiveRequest(String newRequest);
    void setRequestRoomNumber(int newRoomNumber);
    void setActiveRequestEmployeeId(String newEmployeeId);
    void activateRequest();
    */

    /*request functions*/

    void loadRecs();

    void viewRequests();

    //employees can add possibly requests to the system
    void addRequest(String employeeId);

    //employees can remove a possible request from the system
    void removeRequest(String employeeId);

    //a customer checked in to a room can make a request
    void makeRequest(int roomNumber);
}
=======
package edu.ithaca.bhamula1.hotel;

/**
 * 
 */
public interface RequestsInterface {
    /*
    //active request functions
    String getActiveRequest();
    int getRequestRoomNumber();
    String getActiveRequestEmployeeId();
    boolean requestActive();
    void setActiveRequest(String newRequest);
    void setRequestRoomNumber(int newRoomNumber);
    void setActiveRequestEmployeeId(String newEmployeeId);
    void activateRequest();
    */

    /*request functions*/

    void loadRecs();

    void viewRequests();

    //employees can add possibly requests to the system
    void addRequest(String employeeId);

    //employees can remove a possible request from the system
    void removeRequest(String employeeId);

    //a customer checked in to a room can make a request
    String makeRequest(int roomNumber);
}
>>>>>>> d1ec8b5bc89be206144c520dedcda605cfb30265
