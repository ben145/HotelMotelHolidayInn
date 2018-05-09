package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/25/2018.
 */
public interface CustomerInterface {

    /**
     * Used to format customer's name, ensuring first letter of first and last
     *  names are capitalized, all other letters made lowercase
     * @param firstName Unformatted customer first name
     * @param lastName  Unformatted customer last name
     * @return concatenated String of first and last names
     */
    String makeName(String firstName, String lastName);

    /**
     * Generates ID for customer based on full name; takes first letter of first
     *  and last names, and appends five random digits
     * @return  newly generated customer ID
     */
    String makeID();

    String getName();

    String getFName();

    void setFName(String first);

    String getLName();

    void setLName(String last);

    String getId();

    void setId(String custId);

    /**
     * Allows customer to login to hotel's main system
     * @param idIn  Customer's hotel ID
     * @param pwdIn Customer's hotel password
     */
    void login(String idIn,String pwdIn);

    /**
     * Logs customer out of hotel system
     */
    void logOut();

    boolean isCheckedIn();

    void setCheckedIn(Boolean ckIn);

    /**
     * Sets properties of customer to express they are checked in
     * @param roomNumber    Number of customer's room
     * @return  True
     * post:    Sets customer's room number as roomNumber and checkedIn status as true
     */
    boolean checkIn(int roomNumber);

    /**
     * Resets properties of customer to express they are checked out
     * @param roomNumber    Number of customer's room
     * @return  True
     * post:    Sets customer's room to non-existent room number and checkedIn status as false
     */
    boolean checkOut(int roomNumber);

    int getRoom();

    void setRoom(int room);

    boolean getLoggedIn();

    void setLoggedIn(boolean logIn);

    String getReservation();

    void setReservation(String r);

    boolean getReturningCustomer();

    void setReturningCustomer(boolean returning);

    /**
     * Checks if inputted password matches the user customer's password
     * @param p Inputted password
     * @return  True if input is the same as customer's password, false otherwise
     */
    boolean checkPwd(String p);

    String getPwd();

}
