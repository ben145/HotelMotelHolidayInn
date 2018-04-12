package edu.ithaca.bhamula1.hotel;

import java.util.Scanner;

public class CustomerUI implements CustomerUIInterface {

    public CustomerUI(){

    }

    @Override
    public void run() {

    }

    /***
     * Take in a string of input from the user and upper and lower bounds of what number the input should be and
     * output a 0 if the input was invalid and an int betweeb lowestChoice and highestChoice if it was valid
     * @param input String input from the user to be checked for validity
     * @param lowestChoice lowest possible option to be selected in the part of the menu
     * @param higestChoice highest number option to be selected in the part of the menu
     * @return 0 if the entry was invalid and the int version of the input if the input was valid
     */
    public int checkChoiceInput(String input, int lowestChoice, int higestChoice){
        try{
            int choice = Integer.parseInt(input);
            if(choice>=lowestChoice&&choice<=higestChoice){
                return choice;
            }
            else{
                return 0;
            }
        }catch(NumberFormatException e){
            System.out.println("Invalid input.");
            return 0;
        }
    }

    public int mainScreen(HotelInterface hotel){
        Scanner scan = new Scanner(System.in);

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

            firstOption = checkChoiceInput(scan.next(),1,5);

            switch (firstOption){
                case 1:
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

        }
        return 0;
    }

    public int loggedIn(CustomerInterface c, HotelInterface hotel){

    }

    public static void main(String[] args) {
        CustomerUI ui = new CustomerUI();
        ui.mainScreen();
    }
}
