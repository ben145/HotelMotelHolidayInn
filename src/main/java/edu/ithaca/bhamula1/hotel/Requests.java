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

public class Requests implements RequestsInterface{
    public ArrayList<String> requests = new ArrayList<String>();

    public Requests(){
        loadRecs();
    }

    //some requests any guest can make
    public void loadRecs(){
        requests.add("Room service");
        requests.add("Bring every pillow you have");
        requests.add("Fresh towels");
        requests.add("Room maintenance (I have broken something in this room)");
    	requests.add("Refill minibar");
	    requests.add("I would like to be serenaded with The Eagles' magnum opus 'Hotel California'");
    }

    //view the requests a customer can make
    public void viewRequests(){
        for(int i=0; i<requests.size();i++){
            System.out.println((i+1)+") "+requests.get(i));
        }
    }

    //allows authorized staff to add a request to they system
    public void addRequest(String employeeId){
        //check authorization

	    System.out.println("Enter request to add: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        //request requirements
        System.out.println("Does request require inventory items? (y/n)");
        input = scanner.nextLine();
            if(input == "y"){
            boolean requirements = true;
            String input2;
            while(requirements){
                    System.out.println("Enter inventory item required: ");
                    input = scanner.nextLine();
            System.out.println("Enter number of above items required: ");
            System.out.println();
            input2 = scanner.nextLine();
            Integer.parseInt(input2);

            //prolly have to associate inventory with request at some point
                }
            }
            requests.add(input);
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
    public void makeRequest(int roomNumber){
        viewRequests();
        System.out.println("Enter request number: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        //if()
        try {
            int inputInt = Integer.parseInt(input);
            if(inputInt>0&&inputInt<requests.size()) {
                input = requests.get(inputInt);
                ActiveRequest newRequest = new ActiveRequest(input, roomNumber);
                Hotel.activeRequests.add(newRequest);
                System.out.println("Your request has been accepted and will be fulfilled as soon as possible");
            }
            else{
                System.out.println("Invalid Choice.");
            }
        }catch (NumberFormatException e){
            System.out.println("Invalid Input.");
        }
    }
}
