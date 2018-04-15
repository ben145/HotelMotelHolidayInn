package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class Requests implements RequestsInterface{
    public ArrayList<String> orderables = new ArrayList<String>();

    public Requests(){
        loadRecs();
    }

    //some orderables any guest can order
    public void loadRecs(){
        orderables.add("Room service");
        orderables.add("Bring every pillow you have");
        orderables.add("Fresh towels");
        orderables.add("Room maintenance (I have broken something in this room)");
    	orderables.add("Refill minibar");
	    orderables.add("I would like to be serenaded with The Eagles' magnum opus 'Hotel California'");
    }

    //view the orderables a customer can order
    public void viewRequests(){
        for(int i = 0; i< orderables.size(); i++){
            System.out.println((i+1)+") "+ orderables.get(i));
        }
    }

    //allows authorized staff to add an orderable to the system
    public void addRequest(){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your Employee ID now.");
        String idIn = s.nextLine();

        // TODO: check authorization using idIn

	    System.out.println("Enter request to add: ");
        String itemIn = s.nextLine();
        //check if orderable already exists
        // TODO: need all orderable items to be stored lowercase
        if (orderables.contains(itemIn.toLowerCase()))
            System.out.println("That request can already be made");

        //request requirements
        System.out.println("Will the request require inventory items? (y/n)");
        String willRequire = s.nextLine();
        if (willRequire.toLowerCase().equals("y")) {
            boolean isRequired = true;
            while (isRequired) {
                System.out.println("Enter inventory item required: ");
                String reqInv = s.nextLine();
                System.out.println("Enter number of "+reqInv+"s required: ");
                int numIn = Integer.parseInt(s.nextLine());

                // TODO: make it so inventory is associated with request

                System.out.println("Are more inventory items required?");
                willRequire = s.nextLine();
                if (willRequire.toLowerCase().equals("n"))
                    isRequired = false;
            }
        }
        orderables.add(itemIn);
        System.out.println("Request of "+itemIn+" added to options list");
    }

    //allows authorized staff to remove a orderable from the system
    public void removeRequest(){
    	//check authorization
	
        //remove orderables
        viewRequests();
        System.out.println("Enter request number to delete: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        orderables.remove(input);

        System.out.println("Request removed from options list");
    }

    /**
     * Constructor for Request class
     * roomNumber: number assigned to room of originating request
     * employeeID: ID of employee requested by customer to take request
     * orderable: item desired by customer to be brought to their room
     * isTaken: boolean that determines if request has been accepted yet
     */
    class Request {
        private int roomNumber;
        private String employeeID;
        private String orderable;
        private boolean isTaken;

        public Request(int rn, String eid, String o) {
            roomNumber = rn;
            employeeID = eid;
            orderable = o;
            isTaken = false;
        }
}

    //allows a customer to make a request
}
