package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/25/2018.
 */
public interface CustomerInterface {

    String makeName(String firstName, String lastName);

    String makeID();

    String getName();

    String getId();

    void login(String idIn);

    void logOut();

    boolean isCheckedIn();

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