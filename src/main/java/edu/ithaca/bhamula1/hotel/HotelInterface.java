package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Calendar;
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

        void checkRooms(int rmNum, String cID, Calendar checkIn, int durration);

        void addReservation(CustomerInterface cus, RoomInterface rm, Calendar checkIn, int duration);

        void setCustomer();

        void setEmplList();
        void saveEmplList();


        void printEmployeeList();
        void printLoggedInEmployeeList();
        void printAvailableEmployeeList();
        void printStaffVacancies();
        List getEList();


}
