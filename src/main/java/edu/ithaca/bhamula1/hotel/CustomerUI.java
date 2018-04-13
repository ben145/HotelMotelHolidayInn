package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;

import static edu.ithaca.bhamula1.hotel.Main.createHotel;
import static edu.ithaca.bhamula1.hotel.Main.employeeLogScreen;

public class CustomerUI implements CustomerUIInterface {
    HotelInterface hotel;
    Scanner scan = new Scanner(System.in);


    public CustomerUI(){
        hotel = createHotel();
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
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a choice from "+lowestChoice+" to "+highestChoice);
            return 0;
        }
    }

    public int mainScreen(){

        int firstOption = 0;

        System.out.println("Welcome to {insert hotel name here}");
        //login or room view loop
        while (firstOption != 5) {
            System.out.println("Would you like to:\n" +
                    "1) sign in\n" +
                    "2) sign up\n" +
                    "3) view rooms \n" +
                    "4) quit\n" +
                    "5) -Staff Login-\n\n");

            while(firstOption == 0) {
                firstOption = checkChoiceInput(scan.next(), 1, 5);
            }

            if(firstOption==1) {
                System.out.println("Sign In");
                System.out.println("Type Your First Name: ");
                String firstName = scan.next();
                //System.out.println(firstName);
                System.out.println("Type Your User ID: ");
                String id = scan.next();
                //System.out.println(id);
                if (hotel.getCustomer(id) == null) {
                    System.out.println("Incorrect account information");
                } else {
                    hotel.logIn(firstName, id);
                    if (hotel.getCustomer(id).getLoggedIn()) {
                        firstOption = loggedIn(hotel.getCustomer(id), hotel);
                    }
                }
            }

            else if(firstOption==2) {
                System.out.println("Sign Up");
                System.out.println("Type Your First Name: ");
                String firstName = scan.next();
                System.out.println("Type Your Last Name: ");
                String lastName = scan.next();
                hotel.createAccount(firstName, lastName);
                String id = hotel.getCustomer(firstName, lastName).getId();
                firstOption = loggedIn(hotel.getCustomer(firstName, lastName), hotel);
            }

            else if(firstOption == 3){
                System.out.println("View Rooms ");
                System.out.println(hotel.viewOrderedAvailableRooms());
                System.out.println("*If you would like to reserve a room, you must create an account or log in first*");
            }

            else if(firstOption == 4){
                System.out.println("Thank you");
            }

            else if(firstOption == 5){
                employeeLogScreen(hotel);
            }
        }
        return 0;
    }

    public int loggedIn(CustomerInterface c, HotelInterface hotel){
        int option =0;
        System.out.println("Welcome " + c.getName());
        while(option!=5) {
            System.out.println("Would you like to \n" +
                    "1)check in \n" +
                    "2) review rooms \n" +
                    "3) reserve room \n" +
                    "4) quit\n");
            while(option == 0) {
                option = checkChoiceInput(scan.next(),1,4);
            }
            if(option == 1){
                System.out.println("Check In");
                System.out.println("What room number: ");
                int rmNum = 0;
                while(rmNum == 0) {
                    rmNum = checkChoiceInput(scan.next(), 1, hotel.getNumberOfRooms());
                }
                hotel.checkIn(rmNum,c);
                if(c.isCheckedIn()){
                   checkedInMenu(c);
                }
            }

            else if(option == 2){
                System.out.println("Review Rooms");
                System.out.println("View Available Rooms  ");
                System.out.println(hotel.viewOrderedAvailableRooms());
            }

            else if(option == 3){
                System.out.println("Reserve Room");
                System.out.println(hotel.viewOrderedAvailableRooms());
                boolean valid = false;
                
            }
        }
        return 4;
    }

    public int checkedInMenu(CustomerInterface c){
        return 4;
    }

    public static void main(String[] args) {
        CustomerUI ui = new CustomerUI();

    }
}
