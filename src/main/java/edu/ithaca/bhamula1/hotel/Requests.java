package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Scanner;
import edu.ithaca.bhamula1.hotel.Hotel;

class ActiveRequest{
    String request;
    int roomNumber;
    String employeeId;
    boolean active;

    //constructor
    public ActiveRequest(String request, int roomNumber){
        this.request = request;
        this.roomNumber = roomNumber;
        employeeId = "";
        active = false;
    }

    //some getters with bad but very specific names so they're not confused with other getters
    public String getActiveRequest(){
        return request;
    }
    public int getRequestRoomNumber(){
        return roomNumber;
    }
    public String getActiveRequestEmployeeId(){
        return employeeId;
    }
    public boolean requestActive(){
        return active;
    }

    //setters with equally bad names
    public void setActiveRequest(String newRequest){
        request = newRequest;
    }
    public void setRequestRoomNumber(int newRoomNumber){
        roomNumber = newRoomNumber;
    }
    public void setActiveRequestEmployeeId(String newEmployeeId){
        employeeId = newEmployeeId;
    }
    public void activateRequest(){
        if(active){
            active = false;
        }
        else{
            active = true;
        }
    }
}
class RoomService {
    String request;
    double associatedPrice;
    int numRequirements;
    ArrayList<String> requirements;

    //constructor without requirement list
    public RoomService(String request, double associatedPrice, int numRequirements){
        this.request = request;
        this.associatedPrice = associatedPrice;
        this.numRequirements = numRequirements;
        if(numRequirements > 0){
            Scanner scanner = new Scanner(System.in);
            String input;
            for(int i=0; i<numRequirements; i++){
                System.out.println("Enter requirement " + (i+1) +": ");
                input = scanner.nextLine();
                requirements.add(input);
            }
        }
        else{
            requirements = null;
        }
    }

    //constructor with requirement list
    public RoomService(String request, double associatedPrice, int numRequirements, ArrayList<String> requirements){
        this.request = request;
        this.associatedPrice = associatedPrice;
        this.numRequirements = numRequirements;
        this.requirements = new ArrayList<String>(requirements);
    }

    //some getters
    public String getRequestName(){
        return request;
    }
    public double getAssociatedPrice(){
        return associatedPrice;
    }
    public int getNumRequirements(){
        return numRequirements;
    }
    public ArrayList getRequirements(){ return requirements; }

    //some setters
    public void setRequestName(String newRequest){
        request = newRequest;
    }
    public void setAssociatedPrice(double newPrice){ associatedPrice = newPrice; }
    public void addRequirement(String newRequirement){
        if (requirements == null) {
            this.requirements = new ArrayList<String>();
        }
        boolean found = false;
        for(Inventory item: Hotel.inventory) {

            if(item.getItem().equals(newRequirement)){
                found = true;
                requirements.add(newRequirement);
                numRequirements++;
            }

        }
        if(!found){
            System.out.println(newRequirement + " not found in inventory. Please restock and try again.");
        }
    }
    public void removeRequirement(String toDelete){
        if((requirements != null) && (requirements.contains(toDelete))){
            requirements.remove(toDelete);
            numRequirements--;
            if(numRequirements == 0){
                requirements = null;
            }
        }
        else{
            System.out.println("Requirement not found");
        }
    }
}
public class Requests implements RequestsInterface{
    public ArrayList<RoomService> requests = new ArrayList<>();

    public Requests(){
        loadRecs();
    }

    //some requests any guest can make
    public void loadRecs(){
        RoomService req;
        //first request
            ArrayList<String> reqs = new ArrayList<>();
            int numReqs = 0;
            for(Inventory item: Hotel.inventory) {
                if (item.getItem().equals("Scratchy Toilet Paper")) {
                    reqs.add("Scratchy Toilet Paper");numReqs++; }

                if (item.getItem().equals("Sheet Set")) {
                    reqs.add("Sheet Set");numReqs++; }

                if (item.getItem().equals("Small Shampoo")) {
                    reqs.add("Small Shampoo");numReqs++; }

                if (item.getItem().equals("Soap")) {
                    reqs.add("Soap");numReqs++; }

                if (item.getItem().equals("Tiny Conditioner")) {
                    reqs.add("Tiny Conditioner");numReqs++; }

                if (item.getItem().equals("Towels")) {
                    reqs.add("Towels");numReqs++; }

                if (item.getItem().equals("Washcloths")) {
                    reqs.add("Washcloths");numReqs++; }
            }
            if(numReqs > 2) {
                req = new RoomService("Room service", 0, numReqs, reqs);
                requests.add(req);
            }
            reqs.clear();
        //second request
            for(Inventory item: Hotel.inventory) {
                if(item.getItem().equals("Pillow")){
                    reqs.add("Pillow");
                    req = new RoomService("Bring every pillow you have", 0, 1, reqs);
                    requests.add(req);
                    reqs.clear();
                }
        //third
                if (item.getItem().equals("Towels")) {
                    reqs.add("Towels");
                    req = new RoomService("Fresh Towels", 0, 1, reqs);
                    requests.add(req);
                    reqs.clear();
                }
        //and so fo(u)rth
                if (item.getItem().equals("Wrench")) {
                    reqs.add("Wrench");
                    req = new RoomService("Room maintenance (I have broken something in this room)", 12.30, 1, reqs);
                    requests.add(req);
                    reqs.clear();
                }
            }
        //and this one
            numReqs = 0;
            for(Inventory item: Hotel.inventory) {
                if (item.getItem().equals("Gin")) {
                    reqs.add("Gin");numReqs++; }

                if (item.getItem().equals("Rum")) {
                    reqs.add("Rum");numReqs++; }

                if (item.getItem().equals("Tequila")) {
                    reqs.add("Tequila");numReqs++; }

                if (item.getItem().equals("Vodka")) {
                    reqs.add("Vodka");numReqs++; }

                if (item.getItem().equals("Whiskey")) {
                    reqs.add("Whiskey");numReqs++; }
            }
            if(numReqs > 1) {
                req = new RoomService("Refill minibar", 35.56, numReqs, reqs);
                requests.add(req);
            }
            reqs.clear();
	    //can't forget that
            for(Inventory item : Hotel.inventory){
                if(item.getItem().equals("Don Felder")) {
                    reqs.add("Don Felder");
                    reqs.add("Don Henley");
                    reqs.add("Glenn Frey");
                    reqs.add("Joe Walsh");
                    reqs.add("Randy Meisner");
                    req = new RoomService("I would like to be serenaded with The Eagles' magnum opus 'Hotel California'", 6.66, 5, reqs);
                    requests.add(req);
                }
            }
            reqs.clear();
    }

    //view the requests a customer can make
    public void viewRequests(){
        for(int i=0; i<requests.size();i++){
            System.out.println((i+1)+") "+requests.get(i).getRequestName());
        }
    }

    /**
     * Allows authorized staff to add a request to the system
     * @param employeeId ID of authorized employee
     */
    public void addRequest(String employeeId){
        //check authorization
        Scanner scanner = new Scanner(System.in);
        boolean authorized = false;
        while (!authorized) {
            System.out.print("Enter your employee ID: ");
            String idInput = scanner.nextLine();
            // not sure if easy way to loop through all employees and check
            // inputID against them for authorization, simple test for now - Ben
            if (idInput.length() == 7)
                authorized = true;
        }
        //request name
            System.out.println("Enter request to add: ");
            String req = scanner.nextLine();
        //request price
            System.out.println("Enter additional charge associated with request: ");
            String input = scanner.nextLine();
            double price = Double.parseDouble(input);
        //getting request requirements
            System.out.println("Does request require inventory items? (y/n)");
            input = scanner.nextLine();
            int num = 0;
            // allows any input starting with 'y' or 'Y'
                if(input.substring(0,1).toLowerCase().equals("y")){
                    System.out.println("Enter number of Requirements: ");
                    input = scanner.nextLine();
                    num = Integer.parseInt(input);
                }
        RoomService newReq = new RoomService(req,price,num);
        System.out.println("Request added to options list");
    }

    //allows authorized staff to remove a request to the system
    public void removeRequest(String employeeId){
    	//check authorization
	
        //remove requests
        viewRequests();
        System.out.println("Enter request number to delete: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        requests.remove(input);

        System.out.println("Request removed from options list");
    }

    //allows a customer to make a request
    public String makeRequest(int roomNumber){
        viewRequests();
        System.out.println("Enter request number: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            //compensating for 0
            int inputInt = (Integer.parseInt(input));
            if(inputInt>0&&inputInt<requests.size()) {
                input = requests.get(inputInt-1).getRequestName();
                ActiveRequest newRequest = new ActiveRequest(input, roomNumber);
                Hotel.activeRequests.add(newRequest);

                System.out.println("Your request has been accepted and will be fulfilled as soon as possible");
                return input + "," + requests.get(inputInt-1).getAssociatedPrice();
            }
            else{
                System.out.println("Invalid Choice.");
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid Input.");
        }
        return "";
    }
}