package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/25/2018.
 */
public interface customer {

    public String makeName();
    public String makeID();
    public String getName();
    public String getId();
    public void login();
    public boolean isCheckedIn();
    public boolean checkIn(int roomNumber);
    public boolean checkOut(int roomNumber);
    public void setRoom(int room);
}
