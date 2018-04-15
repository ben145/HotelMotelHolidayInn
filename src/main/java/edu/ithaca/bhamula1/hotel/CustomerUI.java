package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;

import static edu.ithaca.bhamula1.hotel.Main.createHotel;
import static edu.ithaca.bhamula1.hotel.Main.employeeLogScreen;

public class CustomerUI implements CustomerUIInterface {
    HotelInterface hotel;
    Scanner scan = new Scanner(System.in);


    public CustomerUI(){
        hotel = createHotel();
        //mainScreen();
    }

    @Override
    public void run() {
        mainScreen();
    }

    /***
     * Take in a string of input from the user and upper and lower bounds of what number the input should be and
     * output a 0 if the input was invalid and an int betweeb lowestChoice and highestChoice if it was valid
     * @param input String input from the user to be checked for validity
     * @param lowestChoice lowest possible option to be selected in the part of the menu
     * @param highestChoice highest number option to be selected in the part of the menu
     * @return 0 if the entry was invalid and the int version of the input if the input was valid
     */
    public int checkChoiceInput(String input, int lowestChoice, int highestChoice){
        try{
            int choice = Integer.parseInt(input);
            if(choice>=lowestChoice&&choice<=highestChoice){
                return choice;
            }
            else{
                System.out.println("Invalid input. Please enter a choice from "+lowestChoice+" to "+highestChoice);
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a choice from "+lowestChoice+" to "+highestChoice);
            return 0;
        }
    }

    public char checkYorN(String yOrN){
        yOrN = yOrN.toLowerCase();
        char choice = yOrN.charAt(0);
        if(choice!='y'&&choice!='n'){
            System.out.println("Invalid input, Please Enter 'yes' or 'no'.");
            return 1;
        }
        //System.out.println(choice);
        return choice;
    }

    public int signIn(String firstName, String ID){
        if (hotel.getCustomer(ID) == null) {
            System.out.println("Incorrect account information");
        } else {
            hotel.logIn(firstName, ID);
            if (hotel.getCustomer(ID).getLoggedIn()) {
                int r = loggedIn(hotel.getCustomer(ID), hotel);
                return r;
            }
        }
        return 0;
    }

    public int mainScreen(){

        int firstOption = 0;

        System.out.println("Welcome to {insert hotel name here}");
        //login or room view loop
        while (firstOption != 4) {
            System.out.println("Would you like to:\n" +
                    "1) sign in\n" +
                    "2) sign up\n" +
                    "3) view rooms \n" +
                    "4) quit\n" +
                    "5) -Staff Login-\n\n");

            while(firstOption == 0) {
                firstOption = checkChoiceInput(scan.nextLine(), 1, 5);
            }

            //SIGN IN
            if(firstOption==1) {
                System.out.println("Sign In");
                System.out.println("Type Your First Name: ");
                String firstName = scan.nextLine();
                //System.out.println(firstName);
                System.out.println("Type Your User ID: ");
                String id = scan.nextLine();
                //System.out.println(id);
                firstOption = signIn(firstName, id);
            }

            //SIGN UP
            else if(firstOption==2) {
                System.out.println("Sign Up");
                System.out.println("Type Your First Name: ");
                String firstName = scan.nextLine();
                System.out.println("Type Your Last Name: ");
                String lastName = scan.nextLine();
                hotel.createAccount(firstName, lastName);
                String id = hotel.getCustomer(firstName, lastName).getId();
                firstOption = loggedIn(hotel.getCustomer(firstName, lastName), hotel);
            }

            //VIEW ROOMS
            else if(firstOption == 3){
                System.out.println("View Rooms ");
                System.out.println(hotel.viewOrderedAvailableRooms());
                System.out.println("*If you would like to reserve a room, you must create an account or log in first*\n");
            }

            //QUIT
            else if(firstOption == 4){
                System.out.println("Thank you");
                return 0;
            }

            //EMPLOYEE LOGIN
            else if(firstOption == 5){
                employeeLogScreen(hotel);
            }
            firstOption = 0;
        }
        return 0;
    }

    public int loggedIn(CustomerInterface c, HotelInterface hotel){
        int option =0;
        System.out.println("Welcome " + c.getName());
        while(option!=5) {
            System.out.println("Would you like to \n" +
                    "1) check in \n" +
                    "2) review rooms \n" +
                    "3) reserve room \n" +
                    "4) cancel room reservation \n" +
                    "5) quit\n");
            while(option == 0) {
                option = checkChoiceInput(scan.nextLine(),1,5);
            }
            //CHECK IN
            if(option == 1){
                System.out.println("Check In");
                System.out.println("What is your room number: ");
                int rmNum = 0;
                while(rmNum == 0) {
                    rmNum = checkChoiceInput(scan.nextLine(), 1, hotel.getNumberOfRooms()-1);
                }
                hotel.checkIn(rmNum,c);
                if(c.isCheckedIn()){
                   checkedInMenu(c);
                }
            }

            //REVIEW ROOMS
            else if(option == 2){
                System.out.println("Review Rooms");
                System.out.println("View Available Rooms  ");
                System.out.println(hotel.viewOrderedAvailableRooms());
                System.out.println();
            }

            //RESERVE ROOM
            else if(option == 3){
                System.out.println("Reserve Room");
                System.out.println(hotel.viewOrderedAvailableRooms());
                boolean valid = false;
                System.out.println("\nWould you like to reserve one of the rooms above?\nEnter Yes or No and hit enter");
                char r = 1;
                while(r==1) {
                    String respond = scan.nextLine();
                    r = checkYorN(respond);
                }
                switch(r){
                    case 'y':
                        System.out.println("\nPlease enter the room number you wish to reserve: ");
                        int rmNum = 0;
                        while(rmNum==0){
                            rmNum=checkChoiceInput(scan.nextLine(),0, hotel.getNumberOfRooms()-1);
                        }
                        if(hotel.getRoom(rmNum).getIfAvailable()) {
                            System.out.println("Please enter your customer ID: ");
                            String custID = scan.nextLine();
                            if(hotel.getCustomer(custID)==null){
                                System.out.println("Invalid ID.");
                            }
                            else {
                                hotel.checkRooms(rmNum, custID);
                                valid = true;
                            }
                        }else{
                            System.out.println("Room not available.");
                        }
                        break;
                    case 'n':
                        System.out.println("Thank you, please visit again");
                        valid = true;
                        break;
                }
            }

            else if(option == 4){
                //CANCEL ROOM RESERVATION
                System.out.println("Cancel Room Reservation");
                if(c.getReservation().equals(null)){
                    System.out.println("You do not currently have a room reserved");
                }
                else {
                    System.out.println("We are sorry to here you will not be staying with us please contact us with any complaints.");
                    System.out.println("Which room do you wish to check out of?");
                    int rmNum = 0;
                    while (rmNum == 0) {
                        rmNum = checkChoiceInput(scan.nextLine(), 1, hotel.getNumberOfRooms() - 1);
                    }
                    //need to actually cancel the reservation
                }

            }

            else if(option == 5){
                return 4;
            }
            option = 0;
        }
        return 4;
    }

    public int checkedInMenu(CustomerInterface c){
        System.out.println("Welcome! We hope you enjoy your stay.");
        //Request Stuff and check out
        return 4;
    }

    public static void main(String[] args) {
        CustomerUI ui = new CustomerUI();
        ui.mainScreen();
    }
}
