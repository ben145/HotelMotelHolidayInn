package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.List;

public interface HotelInterface {

        RoomInterface getRoom(int roomNumber);

        boolean checkIn(int roomNumber, CustomerInterface customer);

        boolean checkOut(int roomNumber, CustomerInterface customer);

        void addTestRoom(int roomNumber);

        void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenitites);

        void setNumberOfRooms(int numberOfRooms);

        ArrayList<RoomInterface> getRooms();

        String viewOrderedAvailableRooms();

        void logIn (String name, String id);

        CustomerInterface getCustomer(String first, String last);

        CustomerInterface getCustomer(String ID);

        void createAccount (String fname, String lastName);

        CustomerInterface checkValidCust(String c);

        void checkRooms(int rmNum, String cID);

        void setCustomer();

        void setEmplList();
        void saveEmplList();

        boolean checkEmployeeLogIn(String el, String epwd);

        void printEmployeeList();
        void printLoggedInEmployeeList();
        void printAvailableEmployeeList();
        void printStaffVacancies();


}
