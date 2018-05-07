package edu.ithaca.bhamula1.hotel;

public interface EmployeeUI_Interface {


    String getEmplTitle();

    void setEmployee(EmployeeIMPL e);

    /**
     * All UI interaction below - Employee can make choice if logged in
     * @param h Used to access employee list and requests from Hotel object
     */
    void uiInteraction(HotelInterface h);

    /**
     * Validates inputted employee login and password compared to employee
     *  list; if login is accurate, save employee state
     * @param el    Employee login input
     * @param epwd  Employee password input
     * @author - DMF
     */
    void checkEmployeeLogIn(String el, String epwd);

    /**
     * Before employee can choose options, valid input must be entered --
     *  this method checks for valid input and calls checkEmployeeLogIn to
     *  verify login data
     */
    void employeeLogScreenUI();

    /**
     * Displays active requests made for specific room numbers using the static
     *  list of activeRequests in Hotel; displays message if there are no
     *  active requests
     * @author DMF
     */
    void viewOpenRequests();

    /**
     * Takes an active request and assigns it to an employee and the employee
     *  to the request, and sets employee as unavailable
     * @param req   Request number
     */
    void takeRequests(int req);

    /**
     * Searches for employee's assigned requests to be fulfilled, if their ID
     *  is associated with a request
     * @return  True if employee has request to fulfill, false otherwise
     */
    boolean checkEmpReq();

    /**
     * Fulfills a request associated with the employee and removes it from
     *  list of active requests
     * @param req   Request to be fulfilled
     */
    void completeRequest(int req);

    /**
     * Logs out employee; closes/ends employeeUI and returns to the main screen
     *  menu, saving state of employee
     * @author DMF
     */
    void closeUI();

}
