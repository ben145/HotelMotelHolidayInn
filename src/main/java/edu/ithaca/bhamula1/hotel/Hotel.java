package edu.ithaca.bhamula1.hotel;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by Ben on 3/22/2018.
 */
public class Hotel implements HotelInterface {

    //have to set this!
    int numberOfRooms =0;
    private ArrayList<RoomInterface> rooms;
    private List<CustomerInterface> customers;
    private List<EmployeeIMPL> employees;
    private List<InventoryInterface> inventory;
    private List<ActiveRequest> activeRequests;
    public Hotel(){
        //this was a hash map. Changed to a array list
        //the index is the room number
//        rooms = new HashMap<>();
        rooms = new ArrayList<RoomInterface>();

        //should this is a linked list instead? better memory
        customers = new ArrayList<>();

        // List of roles and employees in hotel
        employees = new ArrayList<>();
        if(employees.isEmpty()) {
            setEmplList();
        }

        //hotel inventory w/ sample population
        inventory = new ArrayList<InventoryInterface>();
        testInventory();
    }

    //only for use in testing checkin and checkout before actual function is added
    public RoomInterface getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public boolean checkIn(int roomNumber, CustomerInterface customer){
        //find room
        RoomInterface current = getRoom(roomNumber);
        //check room is reserved
        if(!current.getIfAvailable()){
            if(customer.getName().equals(current.getReservationName())){
                boolean c = customer.checkIn(roomNumber);
                boolean r = current.checkIn(customer);
                return c&r;
            }
            else{
                System.out.println("You have not reserved this room. You must have reserved a room to check in.");
                return false;
            }
        }
        else{
            System.out.println("Room "+roomNumber+" has not been reserved. A room must be reserved to be checked into.");
            return false;
        }
        //find customer check matches stored room number
        //set customer as checked in
        //set room to be checked in
    }

    public boolean checkOut(int roomNumber, CustomerInterface customer){
        //find room
        RoomInterface current = getRoom(roomNumber);
        if(current.getCheckedIn()){
            if(customer.getName().equals(current.getReservationName())){
                boolean c = customer.checkOut(roomNumber);
                boolean r = current.checkOut(customer);
                System.out.println("Thank You For Visiting ");
                return c&r;
            }
            else{
                System.out.println("You are not checked into this room. You must be checked in to checkout.");
                return false;
            }
        }
        else {
            System.out.println("This room is not checked into.");
            return false;
        }
    }

    public void addTestRoom(int roomNumber){
        this.rooms.set(roomNumber,new Room(false,roomNumber,100.00,2,"Full","Mini bar"));
    }


    public void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenitites){
        this.rooms.set(roomNumber,new Room(available,roomNumber,price, bedNum, bedType, amenitites));
    }


    /**
     * initializes  the array list so we can put the room in the proper index
     * @param numberOfRooms
     * @author Mia
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;

        while(rooms.size() <numberOfRooms){
            rooms.add(new Room());
        }
    }



    public ArrayList<RoomInterface> getRooms(){
        return rooms;
    }

    /**
     * @Author Mia
     * @return
     */
    public String viewOrderedAvailableRooms(){

        String str="";
        for (RoomInterface rm: rooms) {
            if(rm.getRoomNumber()!=0 && rm.getIfAvailable()) {

                if (str.equals("")) {
                    str +=  rm.toString();
                } else {
                    str += "\n" + rm.toString();
                }


            }

        }
        return str;
    }


    public void logIn (String name, String id){
        CustomerInterface customer = getCustomer(id);
        customer.login(id);
    }

    public CustomerInterface getCustomer(String first, String last){

        for(CustomerInterface c: customers){
            if(c.getName().equals(first + " " + last)){
                return c;
            }

        }
        return null;
    }

    public CustomerInterface getCustomer(String ID){

        for(CustomerInterface c: customers){
            if(c.getId().equals(ID)){
                return c;
            }

        }
        return null;
    }

    public void createAccount (String fname, String lastName){
        CustomerInterface customer = new Customer();
        customer.makeName(fname, lastName);
        String ID = customer.makeID();
        //customer.login(ID);

        customers.add(customer);
    }

    /**
     * checks to verify valid customer takes in a string for cust ID
     * @param c
     * @return returns customer object to be passed to SelectReserveRoom
     * @author - DMF
     */
    public CustomerInterface checkValidCust(String c){
        CustomerInterface g;
        boolean found = false;
        for(Iterator<CustomerInterface> customerIterator = customers.iterator(); customerIterator.hasNext();){
            CustomerInterface cust = customerIterator.next();
            if(Objects.equals(cust.getId(),c) && !found){
                return cust;
            }
        }
        if(!found){
            System.out.println("Invalid Customer ID");
        }
        return null;
    }

    /**
     * function to call function to check for valid customer and to check room is available
     * If both are valid, proceed with the reservation
     * @param rmNum
     * @param cID
     * @Author - DMF
     */
    public void checkRooms(int rmNum, String cID) {
        for (RoomInterface rm : rooms) {
            if (rm.getRoomNumber() == rmNum) {
                System.out.println(rm.getRoomNumber());
                if (rm.getIfAvailable() == true) {
                    CustomerInterface cust = checkValidCust(cID);
                    if (cust.getId() != null) {
                        SelectReserveRoom selRes = new SelectReserveRoom(checkValidCust(cID), rm);
                        if(selRes.checkRoomAvailable()){
                            String resID = selRes.createReservationID();
                            cust.setReservation(resID);
                            cust.setRoom(rmNum);
                            rm.setReservationName(cust.getName());
                            rm.setIfAvailable(false);
                            //rm.setReservationName(resID);
                            System.out.println("Your reservation ID for room "+ cust.getRoom() + " is "+ cust.getReservation());
                        }
                    }
                }
            }
        }
    }


    /**
     * Faux person to test selectReserveRoom functions
     * can be deleted
     */
    public void setCustomer(){
        CustomerInterface cust = new Customer("den","bob");
        System.out.println(cust.getId());
        customers.add(cust);
    }

    /**
     * loads data stored in e.txt to employee list on Hotel instantiation
     * @author - DMF
     */
    @Override
    public void setEmplList(){
        try {
            InputStream file = this.getClass().getResourceAsStream("/e.txt");
            InputStreamReader read = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(read);
            String line;
            while((line = br.readLine())!= null) {
                EmployeeIMPL empl = new EmployeeIMPL();
                String [] sArr = line.split(",");
                empl.setE_TitleNum(Integer.parseInt(sArr[0]));
                empl.setE_Title(sArr[1]);
                empl.setE_LastName(sArr[2]);
                empl.setE_FirstName(sArr[3]);
                empl.setE_LogID(sArr[4]);
                empl.setE_PWD(sArr[5]);
                empl.setE_Available(Boolean.parseBoolean(sArr[6]));
                empl.setPositionVacant(Boolean.parseBoolean(sArr[7]));
                employees.add(empl);
            }

        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * Save Employee Data when exit program
     */
    @Override
    public void saveEmplList(){

        try {
            OutputStream file = new FileOutputStream("./src/main/resources/e.txt");
            OutputStreamWriter write = new OutputStreamWriter(file);
            BufferedWriter bw = new BufferedWriter(write);

            for(int s = 0; s < employees.size(); s++){
                Employee emp = employees.get(s);
                String line = emp.getE_TitleNum()+","+ emp.getE_Title()+","+emp.getE_LastName()+","+emp.getE_FirstName()+
                        ","+emp.getE_LogID()+","+emp.getE_PWD()+","+emp.getE_LoggedIn()+","+emp.getE_Available()+
                        ","+emp.getPositionVacant();
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }
    /**
     * prints list of all vacant hotel positions
     * @author - DMF
     */
    @Override
    public void printStaffVacancies(){
        int index = 0;
        Iterator iterator = employees.iterator();
        while(iterator.hasNext() && index!=employees.size()){
            if(employees.get(index).getPositionVacant()){
                System.out.println(employees.get(index).toString());
            }
            index++;
        }
    }



    /**
     * prints list of all hotel employees and their data
     * @author - DMF
     */
    @Override
    public void printEmployeeList(){
        int index = 0;
        Iterator iterator = employees.iterator();
        while(iterator.hasNext() && index!=employees.size()){
            if(!employees.get(index).getPositionVacant()){
                System.out.println(employees.get(index).toString());
            }
            index++;
        }
    }

    /**
     * prints list of hotel employees and their data
     * @author - DMF
     */
    @Override
    public void printLoggedInEmployeeList(){
        int index = 0;
        Iterator iterator = employees.iterator();
        while(iterator.hasNext() && index!=employees.size()){
            //System.out.println(index + "  " +employees.size());
            //System.out.println(employees.get(index).toString());
            if(employees.get(index).getE_LoggedIn()){
                System.out.println(employees.get(index).toString());
            }
            index++;
        }
    }

    /**
     * prints list of Available hotel employees and their data
     * @author - DMF
     */
    @Override
    public void printAvailableEmployeeList(){

        int index = 0;
        Iterator iterator = employees.iterator();
        while(iterator.hasNext() && index!=employees.size()){
            //System.out.println(index + "  " +employees.size());
            //System.out.println(employees.get(index).toString());
            if(employees.get(index).getE_Available()){
                System.out.println(employees.get(index).toString());
            }
            index++;
        }
    }

    @Override
    public List getEList(){
        return employees;
    }

    /*
      * reads in the stock stock file i pulled straight out of my ass
      * populates hotel inventory
    */
    public void testInventory(){
        try {
            InputStream file = this.getClass().getResourceAsStream("/stock.txt");
            InputStreamReader streamReader = new InputStreamReader(file);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;
            while((line = bufferedReader.readLine())!= null) {
                Inventory item = new Inventory();
                String[] input = line.split(",");
                item.setItem(input[0]);
                item.setQuantity(Integer.parseInt(input[1]));
                inventory.add(item);
            }

        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
