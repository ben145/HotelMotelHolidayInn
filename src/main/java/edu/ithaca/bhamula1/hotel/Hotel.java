package edu.ithaca.bhamula1.hotel;

import java.util.*;

/**
 * Created by Ben on 3/22/2018.
 */
public class Hotel {

    //have to set this!
    int numberOfRooms =0;
    private ArrayList<Room> rooms;
    private List<Customer> customers;

    public Hotel(){
        //this was a hash map. Changed to a array list
        //the index is the room number
//        rooms = new HashMap<>();
        rooms = new ArrayList<Room>();

        //should this is a linked list instead? better memory
        customers = new ArrayList<>();
    }

    //only for use in testing checkin and checkout before actual function is added
    public Room getRoom(int roomNumber){
        return rooms.get(roomNumber);
    }

    public boolean checkIn(int roomNumber, Customer customer){
        //find room
        Room current = getRoom(roomNumber);
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

    public boolean checkOut(int roomNumber, Customer customer){
        //find room
        Room current = getRoom(roomNumber);
        if(current.getCheckedIn()){
            if(customer.getName().equals(current.getReservationName())){
                boolean c = customer.checkOut(roomNumber);
                boolean r = current.checkOut(customer);
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



    public ArrayList<Room> getRooms(){
        return rooms;
    }

    /**
     * @Author Mia
     * @return
     */
    public String viewOrderedAvailableRooms(){

        String str="";
        for (Room rm: rooms) {
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
        Customer customer = new Customer(name, id);
        logIn(name, id);
    }

    public Customer getCustomer(String first, String last){

        for(Customer c: customers){
            if(c.getName().equals(first + " " + last)){
                return c;
            }

        }
        return new Customer();
    }

    public Customer getCustomer(String ID){

        for(Customer c: customers){
            if(c.getId().equals(ID)){
                return c;
            }

        }
        return new Customer();
    }

    public void createAccount (String fname, String lastName){
        Customer customer = new Customer();
        customer.makeName(fname, lastName);
        String ID = customer.makeID();
        customer.login(ID);

        customers.add(customer);
    }

}
