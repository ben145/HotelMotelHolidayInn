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
class RoomService{
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

            if(item.getItem() == newRequirement){
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
            System.out.println("requirement not found");
        }
    }
}
public class Requests implements RequestsInterface{
    public ArrayList<RoomService> requests = new ArrayList<RoomService>();

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
                if (item.getItem() == "Scratchy Toilet Paper") {
                    reqs.add("Scratchy Toilet Paper");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Sheet Set") {
                    reqs.add("Sheet Set");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Small Shampoo") {
                    reqs.add("Small Shampoo");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Soap") {
                    reqs.add("Soap");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Tiny Conditioner") {
                    reqs.add("Tiny Conditioner");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Towels") {
                    reqs.add("Towels");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Washcloths") {
                    reqs.add("Washcloths");numReqs++; }
            }
            if(numReqs > 2) {
                req = new RoomService("Room service", 0, numReqs, reqs);
                requests.add(req);
            }
            reqs.clear();
        //second request
                    reqs.add("Pillow");
                    req = new RoomService("Bring every pillow you have", 0, 1, reqs);
                    requests.add(req);
                    reqs.clear();
        //third
                    reqs.add("Towels");
                    req = new RoomService("Fresh Towels", 0, 1, reqs);
                    requests.add(req);
                    reqs.clear();
        //and so fo(u)rth
                    reqs.add("Wrench");
                    req = new RoomService("Room maintenance (I have broken something in this room)", 12.30, 1, reqs);
                    requests.add(req);
                    reqs.clear();
        //and this one
            numReqs = 0;
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Gin") {
                    reqs.add("Gin");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Rum") {
                    reqs.add("Rum");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Tequila") {
                    reqs.add("Tequila");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Vodka") {
                    reqs.add("Vodka");numReqs++; }
            }
            for(Inventory item: Hotel.inventory) {
                if (item.getItem() == "Whiskey") {
                    reqs.add("Whiskey");numReqs++; }
            }
            if(numReqs > 1) {
                req = new RoomService("Refill minibar", 35.56, numReqs, reqs);
                requests.add(req);
            }
            reqs.clear();
	    //can't forget that
            for(Inventory item : Hotel.inventory){
                if(item.getItem() == "Don Felder") {
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

    //allows authorized staff to add a request to they system
    public void addRequest(String employeeId){
        //check authorization
        //request name
            System.out.println("Enter request to add: ");
            Scanner scanner = new Scanner(System.in);
            String req = scanner.nextLine();
        //request price
            System.out.println("Enter additional charge associated with request: ");
            String input = scanner.nextLine();
            double price = Double.parseDouble(input);
        //request requirements
            System.out.println("Does request require inventory items? (y/n)");
            input = scanner.nextLine();
            int num = 0;
                if(input == "y"){
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
