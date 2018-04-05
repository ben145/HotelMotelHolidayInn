package edu.ithaca.bhamula1.hotel;

/**
 * 
 */
public interface RoomInterface {

    void setIfAvailable(boolean avail);
    void setRoomNumber(int num);
    void setRoomPrice(double price);
    void setBedCount(int num);
    void setBedType(String type);
    void addAmenities(String amenities);
    boolean getIfAvailable();
    int getRoomNumber();
    int getBedCount();
    double getRoomPrice();
    String getBedType ();
    String getAmenities();
    String toString();
    boolean checkIn(Customer customer);
    boolean checkOut(Customer customer);
    void setReservationName(String name);
    String getReservationName();
    boolean getCheckedIn();


}
