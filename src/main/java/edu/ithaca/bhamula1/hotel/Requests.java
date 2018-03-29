package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class Requests {
    public ArrayList<String> requests = new ArrayList<String>();
    public ArrayList<String> tierTwoRequests;

    public Requests(){
        loadRecs();
    }

    //some requests any guest can make
    public void loadRecs(){
        requests.add("Room service");
        requests.add("Bring every pillow you have");
        requests.add("Fresh towels");
        requests.add("Room maintenance (I have broken something in this room)");
    }

    //some next level requests
    public void loadAdvancedRecs(){
        tierTwoRequests.add("Pink champagne on ice");
        requests.add("Refill minibar");
        requests.add("tim roth and a big fuck-off cleaver");
    }

    //allows authorized staff to add a request to they system
    public void addRequest(){
        System.out.println("Enter request tier (1-2): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input=="1"){
            System.out.println("Enter request to add: ");
            input = scanner.nextLine();
            requests.add(input);
        }
        else{
            System.out.println("Enter request to add: ");
            input = scanner.nextLine();
            tierTwoRequests.add(input);
        }
    }

    //view the requests a customer can make
    public void viewRequests(){
        //loadRecs();
        for(int i=0; i<requests.size();i++){
            System.out.println((i+1)+") "+requests.get(i));
        }
        //check customer for tier status,if higher:
        /*
        * loadAdavncedRecs();
        * for(int i=0; i<tierTworequests.size();i++){
        *    System.out.println(tierTwoRequests.get(i));
        * }
        */
    }
}
