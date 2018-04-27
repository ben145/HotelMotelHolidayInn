package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/25/2018.
 */
public interface CustomerInterface {

    String makeName(String firstName, String lastName);

    String makeID();

    String getName();

    String getFName();

    void setFName(String first);

    String getLName();

    void setLName(String last);

    String getId();

    void setId(String custid);

    void login(String idIn);

    void setLoggedIn(boolean logIn);

    void logOut();

    boolean isCheckedIn();

    void setCheckedIn(Boolean ckIn);

    boolean checkIn(int roomNumber);

    boolean checkOut(int roomNumber);

    int getRoom();

    void setRoom(int room);

    boolean getLoggedIn();

    String getReservation();

    void setReservation(String r);

    boolean getReturningCustomer();

    void setReturningCustomer(boolean returning);

}
