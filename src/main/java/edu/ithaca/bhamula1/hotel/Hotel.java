package edu.ithaca.bhamula1.hotel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ben on 3/22/2018.
 */
public class Hotel implements HotelInterface {


    private int numberOfRooms = 69;
    private ArrayList<RoomInterface> rooms;
    private List<CustomerInterface> customers;
    public static List<EmployeeIMPL> employees;
    public static List<Inventory> inventory;
    public static List<ActiveRequest> activeRequests;
    private List<Reservation> reservations;
    private boolean forTests = false;


    public Hotel(boolean test) {
        forTests = test;
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        inventory = new ArrayList<>();
        activeRequests = new LinkedList<>();
        reservations = new ArrayList<>();

    }

    // Hotel constructor
    public Hotel() {
        rooms = new ArrayList<>();
        if (rooms.isEmpty())
            loadRooms();

        customers = new ArrayList<>();
        if (customers.isEmpty())
            loadCustList();

        // List of roles and employees in hotel
        employees = new ArrayList<>();
        if (employees.isEmpty())
            setEmplList();

        reservations = new ArrayList<>();
        loadReservationData();

        // Hotel inventory w/ sample population
        inventory = new ArrayList<>();
        testInventory();
        activeRequests = new LinkedList<>();
    }

    public RoomInterface getRoom(int roomNumber) {return rooms.get(roomNumber);}

    /**
     * Checks customer in if reservation date is today, comparing reservation
     *  year, month, and day to current date
     * @param roomNumber    Customer's room number they are checking into
     * @param customer      Customer associated with reservation to be verified
     * @return  True if reservation date is today, false if a different day or
     *          reservation was not found in system
     */
    public boolean checkIn(int roomNumber, CustomerInterface customer){
        Calendar today = new GregorianCalendar();
        today.set(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        Reservation res = getReservation(customer,roomNumber,today);
        RoomInterface current = getRoom(roomNumber);
        if (res != null) {
            if(customer.getId().equals(res.getCustomer().getId())) {
                if(roomNumber == res.getRoom().getRoomNumber()) {
                    if(Calendar.getInstance().get(Calendar.YEAR) == res.getCheckInDate().get(Calendar.YEAR)
                            &&Calendar.getInstance().get(Calendar.MONTH) == res.getCheckInDate().get(Calendar.MONTH)
                            &&Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == res.getCheckInDate().get(Calendar.DAY_OF_MONTH)) {
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

    /**
     * Checks customer out of their room for their stay
     * @param roomNumber    Customer's room number associated with reservation
     * @param customer      Customer wanting to check out
     * @return  True if customer is successfully checked out of their room,
     *          false otherwise
     */
    public boolean checkOut(int roomNumber, CustomerInterface customer){
        RoomInterface current = getRoom(roomNumber);
        boolean c = customer.checkOut(roomNumber);
        boolean r = current.checkOut(customer);
        System.out.println("Thank You For Visiting ");
        saveCustList();
        saveRooms();
        return c&r;
    }

    // Test room with filled in details
    public void addTestRoom(int roomNumber){
        this.rooms.set(roomNumber,new Room(false,roomNumber,100.00,2,"Full","Mini bar",false));
    }

    // Adds details for a room to Hotel
    public void addRoom(int roomNumber, boolean available, double price, int bedNum, String bedType, String amenities, boolean checkIn){
        this.rooms.set(roomNumber,new Room(available, roomNumber,price, bedNum, bedType, amenities, checkIn));
    }

    /**
     * Initializes the array list so rooms can be entered in the proper index
     * @param numberOfRooms Number of rooms Hotel will have
     * @author Mia
     */
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
        while (rooms.size() < numberOfRooms)
            rooms.add(new Room());
    }

    public ArrayList<RoomInterface> getRooms(){return rooms;}

    /**
     * Displays rooms in order by their room number, with discounted prices if
     *  customer has stayed at the Hotel previously
     * @author Mia
     * @return String of all rooms formatted as a vertical list
     */
    public String viewOrderedRooms(boolean returning) {
        String str = "";
        for (RoomInterface rm: rooms) {
            if (rm.getRoomNumber() != 0) {
                if (str.equals("")) {
                    if (returning)
                        str += rm.printDiscountedPrices();
                    else
                        str +=  rm.toString();
                }
                else {
                    if (returning)
                        str += "\n" + rm.printDiscountedPrices();
                    else
                        str += "\n" + rm.toString();
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
        if(customer==null){
            System.out.println("Invalid login information");
            return null;
        }
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
                String [] sArr = line.split(",");
                EmployeeIMPL empl = new EmployeeIMPL(Integer.parseInt(sArr[0]),sArr[1], sArr[2],sArr[3],sArr[4], sArr[5],
                        Boolean.parseBoolean(sArr[6]),Boolean.parseBoolean(sArr[7]), Boolean.parseBoolean(sArr[8]) );
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
        catch (NullPointerException e){
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
        saveReservationData();
    }

    public Reservation removeReservation(Reservation reservation) {
        Iterator<Reservation> itr = this.reservations.iterator();
        while(itr.hasNext()){
            Reservation curr = itr.next();
            if(curr==reservation){
                reservations.remove(curr);
                saveReservationData();
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

        if(!forTests) {
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
        if(!forTests) {
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
        System.out.println("Loading........");
        doYouHearWhatIHear("src/main/resources/hotel_greeting.wav");

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

    /**
     * checks to verify valid customer takes in a string for cust ID
     * @param rNum
     * @return returns customer object to be passed to SelectReserveRoom
     * @author - DMF
     */
    public RoomInterface checkValidRoom(int rNum){

        for(Iterator<RoomInterface> roomIterator = rooms.iterator(); roomIterator.hasNext();){
            RoomInterface currentRoom = roomIterator.next();
            if(Objects.equals(currentRoom.getRoomNumber(),rNum)){

                return currentRoom;
            }
        }
        System.out.println("Invalid Room number");
        return null;
    }

    @Override
    public void saveReservationData(){
        if(!forTests) {
            try {
                OutputStream file = new FileOutputStream("./src/main/resources/reservation_data.txt");
                OutputStreamWriter write = new OutputStreamWriter(file);
                BufferedWriter bw = new BufferedWriter(write);

                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
                for (int s = 0; s < reservations.size(); s++) {
                    Reservation res = reservations.get(s);
                    String line = res.customer.getId() + ";" + res.room.getRoomNumber() + ";" + res.getNightDuration() + ";" + dateFormat.format(res.getCheckInDate().getTime()) +
                            ";" + res.getCardPayment();
                   // System.out.println(res.getCheckInDate());
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

    @Override
    public void loadReservationData(){
        try {
            InputStream file = this.getClass().getResourceAsStream("/reservation_data.txt");
            InputStreamReader read = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(read);
            String line;

            while((line = br.readLine())!= null) {
                String [] sArr = line.split(";");
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");
                Calendar resDate = Calendar.getInstance();
                try{
                    resDate.setTime(dateFormat.parse(sArr[3]));                    //resDate.setTime(date);
                }catch(Exception e){

                }
                Reservation setRes = new Reservation(checkValidCust(sArr[0]),checkValidRoom(Integer.parseInt(sArr[1])),
                        resDate,Integer.parseInt(sArr[2]),sArr[4]);

                reservations.add(setRes);
                setRes.getRoom().addReservation(setRes.getCheckInDate(),setRes.getNightDuration());
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public void doYouHearWhatIHear(String wav){

        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(wav)));
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
//            while (clip.isRunning())
//                Thread.sleep(10);
            clip.close();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

}