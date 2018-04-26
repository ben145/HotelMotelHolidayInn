package edu.ithaca.bhamula1.hotel;

import java.util.*;

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

    String getBedType();

    String getAmenities();

    String toString();

    boolean checkIn(CustomerInterface customer);

    boolean checkOut(CustomerInterface customer);

    void setReservationName(String name);

    String getReservationName();

    boolean getCheckedIn();

    int addReservation(Calendar date, int nightDuration);

    List<Calendar> getNotAvailTheseDays();

    boolean canReserve(Calendar date, int nightDuration);

    void printNotAvailDates();

    public void removeReservation(Calendar date, int nightDuration);

    String printDiscountedPrices();

    }