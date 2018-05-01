package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public interface HotelInterface {

    RoomInterface getRoom(int roomNumber);

    boolean checkIn(int roomNumber, CustomerInterface customer);

    boolean checkOut(int roomNumber, CustomerInterface customer);

    void addTestRoom(int roomNumber);

    void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenities, boolean checkIn);

    void setNumberOfRooms(int numberOfRooms);

    ArrayList<RoomInterface> getRooms();

    String viewOrderedRooms(boolean returning);
    String viewOrderedAvailableRooms(Calendar checkin, int nightDuration, boolean returning);

    CustomerInterface logIn (String name, String id, String p);

    CustomerInterface checkCustomer(String first, String id, String passW);

    CustomerInterface getCustomer(String ID);

    String createAccount (String fname, String lastName);

    CustomerInterface checkValidCust(String c);

    //        void checkRooms(int rmNum, String cID, Calendar checkIn, int durration);

    void addReservation(CustomerInterface cus, RoomInterface rm, Calendar checkIn, int duration, String card);

    void setCustomer();

    void setEmplList();
    void saveEmplList();



    List<Reservation> getReservations();


    void printEmployeeList();
    void printLoggedInEmployeeList();
    void printAvailableEmployeeList();
    void printStaffVacancies();
    List getEList();

    int getNumberOfRooms();

    void testInventory();
    //this is where "void viewInventory();" would go if it wasn't static

    public Reservation removeReservation(Reservation reservation);
    public Reservation getReservation(CustomerInterface customer, int roomNumber, Calendar checkInDate);
    public List<Reservation> getCustomerReservations(CustomerInterface customer);

    void saveCustList();
    void loadCustList();

    void saveRooms();
    void loadRooms();
    void printRoomList();


    /**
     * Checks for valid room number - used for loading reservations from saved file
     * @param rNum
     * @return
     */
    RoomInterface checkValidRoom(int rNum);

    /**
     * saves data to reservation_data.txt as updates occur so reservation data can be rebuilt when hotel is instantiated
     * @author - DMF
    */
    void saveReservationData();

    /**
     * loads data stored in reservation_data.txt to restore reservation data when hotel is instantiated
     * @author - DMF
    */
    void loadReservationData();

    void doYouHearWhatIHear();

}
