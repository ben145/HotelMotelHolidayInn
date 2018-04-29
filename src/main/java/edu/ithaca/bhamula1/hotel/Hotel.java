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
    public static List<Inventory> inventory;
    public static List<ActiveRequest> activeRequests;
    private List<Reservation> reservations;
    boolean forTEsts = false;


    public Hotel(boolean test){
        forTEsts = test;
        rooms = new ArrayList<RoomInterface>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        inventory = new ArrayList<Inventory>();
        activeRequests = new LinkedList<ActiveRequest>();
        reservations = new ArrayList<>();

    }

    public Hotel(){
        //this was a hash map. Changed to a array list
        //the index is the room number
//        rooms = new HashMap<>();
        rooms = new ArrayList<RoomInterface>();

        if (rooms.isEmpty()) {
            loadRooms();
        }

        //should this is a linked list instead? better memory
        customers = new ArrayList<>();
        if(customers.isEmpty()){
            loadCustList();
        }

        reservations = new ArrayList<>();


        // List of roles and employees in hotel
        employees = new ArrayList<>();
        if(employees.isEmpty()) {
            setEmplList();
        }

        //hotel inventory w/ sample population
        inventory = new ArrayList<Inventory>();
        testInventory();

        //linked list of active requests
        activeRequests = new LinkedList<ActiveRequest>();

        //printRoomList();
    }

    //only for use in testing checkin and checkout before actual function is added
    public RoomInterface getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public boolean checkIn(int roomNumber, CustomerInterface customer){
        //find room
        Calendar today = new GregorianCalendar();
        today.set(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        Reservation res = getReservation(customer,roomNumber,today);
        RoomInterface current = getRoom(roomNumber);
        if(res!=null){
            if(customer.getId()==res.getCustomer().getId()){
                if(roomNumber==res.getRoom().getRoomNumber()){
                    if(Calendar.getInstance().get(Calendar.YEAR)==res.getCheckInDate().get(Calendar.YEAR)
                            &&Calendar.getInstance().get(Calendar.MONTH)==res.getCheckInDate().get(Calendar.MONTH)
                            &&Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==res.getCheckInDate().get(Calendar.DAY_OF_MONTH)){
                        customer.checkIn(roomNumber);

                        saveCustList();

                        current.checkIn(customer);
                        saveRooms();

                        removeReservation(res);
                        return true;
                    }
                    System.out.println("Your reservation is not for today. Please change reservation to check in today.");
                    return false;
                }
            }
        }
        System.out.println("This reservation was not found in our system for today's date. Please review your reservations and try again.\n");
        return false;
    }

    public boolean checkOut(int roomNumber, CustomerInterface customer){
        //find room
        RoomInterface current = getRoom(roomNumber);
        /*
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
        */
        boolean c = customer.checkOut(roomNumber);
        boolean r = current.checkOut(customer);
        System.out.println("Thank You For Visiting ");
        saveCustList();
        saveRooms();
        return c&r;
    }

    public void addTestRoom(int roomNumber){
        this.rooms.set(roomNumber,new Room(false,roomNumber,100.00,2,"Full","Mini bar",false));
    }


    public void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenitites, boolean checkIn){
        this.rooms.set(roomNumber,new Room(available,roomNumber,price, bedNum, bedType, amenitites, checkIn));
    }


    /**
     * initializes  the array list so we can put the room in the proper index
     * @param numberOfRooms
     * @author Mia
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;

        while(rooms.size() < numberOfRooms){
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
    public String viewOrderedRooms(boolean returning){

        String str="";
        for (RoomInterface rm: rooms) {
            if(rm.getRoomNumber()!=0 ) {

                if (str.equals("")) {


                    if(returning){
                        str+= rm.printDiscountedPrices();
                    }else{
                        str +=  rm.toString();
                    }


                } else {

                    if(returning){
                        str += "\n" + rm.printDiscountedPrices();
                    }else{
                        str += "\n" + rm.toString();

                    }


                }

            }

        }
        return str;
    }


    public String viewOrderedAvailableRooms(Calendar checkin, int nightDuration, boolean returning){

        String str="";
        for (RoomInterface rm: rooms) {
            if(rm.getRoomNumber()!=0 && rm.canReserve(checkin, nightDuration) ) {
                if (str.equals("")) {
                    if(returning){
                        str += rm.printDiscountedPrices();
                    }else{
                        str +=  rm.toString();
                    }
                } else {
                    if(returning){
                        str += "\n" + rm.printDiscountedPrices();
                    }else{
                        str += "\n" + rm.toString();
                    }
                }
            }
        }
        return str;
    }

    //HOtel
    public CustomerInterface logIn (String name, String id, String p){
        CustomerInterface customer = checkCustomer(name,id,p);
        customer.login(id,p);
        if(customer.getLoggedIn()){
            saveCustList();
            return customer;
        }else{
            System.out.println("Invalid login information");
            return null;
        }

    }

    public CustomerInterface checkCustomer(String first, String id, String passW){

        for(CustomerInterface c: customers){
            if(c.getFName().equals(first)&&c.getId().equals(id)&&c.checkPwd(passW)){
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

    public String createAccount (String fname, String lastName){
        CustomerInterface customer = new Customer();
        customer.makeName(fname, lastName);
        String ID = customer.makeID();
        customers.add(customer);
        saveCustList();
        return ID;
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


    public int getNumberOfRooms() {
        //System.out.println("TEsting room size"+rooms.size());
        return rooms.size();

    }

    /**
     *
     * @param cus
     * @param rm
     * @param checkIn
     * @param duration
     */
    public void addReservation(CustomerInterface cus, RoomInterface rm, Calendar checkIn, int duration, String cardInfo){
        Reservation res = new Reservation(cus, rm, checkIn, duration, cardInfo);
        reservations.add(res);
    }

    public Reservation removeReservation(Reservation reservation) {
        Iterator<Reservation> itr = this.reservations.iterator();
        while(itr.hasNext()){
            Reservation curr = itr.next();
            if(curr==reservation){
                reservations.remove(curr);
                return curr;
            }
        }
        return null;
    }

    public Reservation getReservation(CustomerInterface customer, int roomNumber, Calendar checkInDate){
        Iterator<Reservation> itr = this.reservations.iterator();
        while(itr.hasNext()){
            Reservation curr = itr.next();
            if(curr.getCustomer().getId()==customer.getId()&&roomNumber==curr.getRoom().getRoomNumber()){
                if(checkInDate.get(Calendar.YEAR)==curr.getCheckInDate().get(Calendar.YEAR)){
                    if(checkInDate.get(Calendar.MONTH)==curr.getCheckInDate().get(Calendar.MONTH)){
                        if(checkInDate.get(Calendar.DAY_OF_MONTH)==curr.getCheckInDate().get(Calendar.DAY_OF_MONTH)){
                            return curr;
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void viewInventory(){
        for(Inventory x : inventory){
            System.out.println(x.toString_Inventory());
        }
    }

    public  List<Reservation> getReservations(){
        return reservations;
    }

    public List<Reservation> getCustomerReservations(CustomerInterface customer){
        Iterator<Reservation> itr = this.reservations.iterator();
        List<Reservation> myRes = new ArrayList<>();
        while(itr.hasNext()){
            Reservation curr = itr.next();
            if(curr.getCustomer()==customer){
                myRes.add(curr);
            }
        }
        Comparator<Reservation> cmp = new CompareReservationByDate() {
            @Override
            public int compare(Reservation r1, Reservation r2) {
                return super.compare(r1, r2);
            }
        };
        myRes.sort(cmp);
        return myRes;
    }

    /**
     * Save Customer Data when exit program
     */
    @Override
    public void saveCustList(){

        if(!forTEsts) {
            try {
                OutputStream file = new FileOutputStream("./src/main/resources/c.txt");
                OutputStreamWriter write = new OutputStreamWriter(file);
                BufferedWriter bw = new BufferedWriter(write);

                for (int s = 0; s < customers.size(); s++) {
                    CustomerInterface customer = customers.get(s);
                    String line = customer.getFName() + "," + customer.getLName() + "," + customer.getId() + "," + customer.getRoom() +
                            "," + customer.isCheckedIn() + "," + customer.getLoggedIn() + "," + customer.getReturningCustomer() + "," + customer.getPwd();
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
                bw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * loads data stored in c.txt for storing customer list on Hotel instantiation
     * @author - DMF
     */
    @Override
    public void loadCustList(){
        try {
            InputStream file = this.getClass().getResourceAsStream("/c.txt");
            InputStreamReader read = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(read);
            String line;

            while((line = br.readLine())!= null) {
                String [] sArr = line.split(",");
                CustomerInterface setCust = new Customer(sArr[0],sArr[1],Integer.parseInt(sArr[3]),
                        Boolean.parseBoolean(sArr[4]),Boolean.parseBoolean(sArr[5]),Boolean.parseBoolean(sArr[6]),sArr[7]);

                customers.add(setCust);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
    /**
     * Save Customer Data when exit program
     */
    @Override
    public void saveRooms(){
        if(!forTEsts) {
            try {
                OutputStream file = new FileOutputStream("./src/main/resources/rooms.txt");
                OutputStreamWriter write = new OutputStreamWriter(file);
                BufferedWriter bw = new BufferedWriter(write);

                for (int s = 0; s < rooms.size(); s++) {
                    RoomInterface room = rooms.get(s);
                    String line = room.getIfAvailable() + ";" + room.getRoomNumber() + ";" + room.getRoomPrice() + ";" + room.getBedCount() +
                            ";" + room.getBedType() + ";" + room.getAmenities() + ";" + room.getCheckedIn();
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                }
                bw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * loads data stored in rooms.txt for storing room list on Hotel instantiation
     * @author - DMF
     */
    @Override
    public void loadRooms(){
        try {
            InputStream file = this.getClass().getResourceAsStream("/rooms.txt");
            InputStreamReader read = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(read);
            String line;

            while((line = br.readLine())!= null) {
                String [] sArr = line.split(";");
                RoomInterface setRoom = new Room(Boolean.parseBoolean(sArr[0]),Integer.parseInt(sArr[1]),Double.parseDouble(sArr[2]),
                        Integer.parseInt(sArr[3]),sArr[4],sArr[5],Boolean.parseBoolean(sArr[6]));

                rooms.add(setRoom);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    /**
     * prints list of all hotel employees and their data
     * @author - DMF
     */
    @Override
    public void printRoomList(){
        int index = 0;
        Iterator iterator = rooms.iterator();
        while(iterator.hasNext() && index!=rooms.size()){
            System.out.println(rooms.get(index).toString());
            index++;
        }
    }

}