package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;

public interface HotelInterface {

        Room getRoom(int roomNumber);

        boolean checkIn(int roomNumber, Customer customer);

        boolean checkOut(int roomNumber, Customer customer);

        void addTestRoom(int roomNumber);

        void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenitites);

        void setNumberOfRooms(int numberOfRooms);

        ArrayList<Room> getRooms();

        String viewOrderedAvailableRooms();

        void logIn (String name, String id);

        Customer getCustomer(String first, String last);

        Customer getCustomer(String ID);

        void createAccount (String fname, String lastName);

        Customer checkValidCust(String c);

        void checkRooms(int rmNum, String cID);

        void setCustomer();


}
