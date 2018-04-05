package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class Requests {
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

    //allows authorized staff to add a request to they system
    public void addRequest(){
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
		input2 = scanner.nextLine();
		Integer.parseInt(input2);

		//prolly have to associate inventory with request at some point
            }
        }
        requests.add(input);
	System.out.println("Request added to options list");
    }

    //allows authorized staff to add a request to the system
    public void removeRequest(){
    	//check authorization
	
	//remove requests
	viewRequests();
	System.out.println("Enter request number to delete: ");
	Scanner scanner = new Scanner(System.in);
	String input = scanner.nextLine();
	requests.remove(input);

	System.out.println("Request removed from options list");
    }

    //view the requests a customer can make
    public void viewRequests(){
        for(int i=0; i<requests.size();i++){
            System.out.println((i+1)+") "+requests.get(i));
        }
    }
}
