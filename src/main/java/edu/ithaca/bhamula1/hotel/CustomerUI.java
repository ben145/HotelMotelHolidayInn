package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
                System.out.println(hotel.viewOrderedRooms(false));
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
                Reservation res  = hotel.getReservation(c,rmNum);
                if(hotel.checkIn(rmNum,c)){
                    Scanner scan1 = new Scanner(System.in);

                    System.out.println("Would you like change the card on file. Type '1' for  yes or '0' for no ");
                    int yesOrNo = scan1.nextInt();
                    while(yesOrNo != 0 && yesOrNo != 1){
                        System.out.println("Try again");
                        yesOrNo = scan1.nextInt();
                    }

                    if(yesOrNo == 1){
                        System.out.println("Type the new card number");
                        String card = scan1.next();

                        while(card.length()>16 || card.length()< 14){
                            System.out.println("Try again");
                            card = scan1.nextLine();
                        }

                        res.setCardPayment(card);
                    }

                    System.out.println("Would you like the room charged to the card on file or pay in cash? Type '1' for card or type '0' for cash");
                    int cardOrCash = scan1.nextInt();
                    while(cardOrCash != 0 && cardOrCash != 1){
                        System.out.println("Try again");
                        cardOrCash = scan1.nextInt();
                    }
                    if(cardOrCash ==1){
                        res.setPaymentType(Reservation.PaymentType.CARD);
                    }else{
                        res.setPaymentType(Reservation.PaymentType.CASH);
                    }

                    checkedInMenu(c,rmNum);
                    hotel.getRoom(rmNum).removeReservation(res.getCheckInDate(),res.getNightDurration());
                }
            }

            //REVIEW ROOMS
            else if(option == 2){
                boolean returning = false;
                if(c.getReturningCustomer()){
                    returning = true;
                }

                System.out.println("Review Rooms");
                System.out.println("View Rooms  ");
                System.out.println(hotel.viewOrderedRooms(true));
                System.out.println();
            }

            //RESERVE ROOM
            else if(option == 3){
                System.out.println("Reserve Room");
                //System.out.println(hotel.viewOrderedAvailableRooms());
                boolean valid = false;


////                //change later
//                Calendar resDate = new GregorianCalendar(2018,3,26);
//
//                hotel.getRoom(1).addReservation(resDate,1);
//                hotel.addReservation(c,hotel.getRoom(1),resDate,1, "1234123412341234");
//

                        int currYear = Calendar.getInstance().get(Calendar.YEAR);
                        System.out.println("Please enter the year you would like to reserve your room for. We only book up to three years ahead:");
                        int resYear = 0;
                        while(resYear==0){
                            resYear = checkChoiceInput(scan.nextLine(),currYear,currYear+3);
                        }
                        int currMonth = Calendar.getInstance().get(Calendar.MONTH);
                        System.out.println("Please enter the month you would like to reserve for as a number("+(currMonth+1)+"-12):");
                        int resMonth = 0;
                        currMonth++;
                        while(resMonth==0){
                            //resMonth = checkChoiceInput(scan.nextLine(),1,12);
                            if(resYear==currYear){
                                resMonth = checkChoiceInput(scan.nextLine(),currMonth,12);
                            }
                            else{
                                resMonth = checkChoiceInput(scan.nextLine(),1,12);
                            }
                        }
                        resMonth= resMonth-1;
                        currMonth = currMonth - 1;
                        Calendar calcCal = new GregorianCalendar(resYear,resMonth,1);
                        int daysInMonth = calcCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                        System.out.println("What day of the month will your reservation start on?");
                        int resDay = 0;
                        int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                        while(resDay==0){
                            if(resYear==currYear&&resMonth==currMonth){
                                resDay = checkChoiceInput(scan.nextLine(), currDay, daysInMonth);
                            }
                            else {
                                resDay = checkChoiceInput(scan.nextLine(), 1, daysInMonth);
                            }
                        }
                        System.out.println("How many nights will you be staying?");
                        int stayDuration = 0;
                        while(stayDuration==0){
                            stayDuration = checkChoiceInput(scan.nextLine(),1,21);
                        }

                        Calendar resDate = new GregorianCalendar(resYear,resMonth,resDay);

                        boolean returning = false;
                        if(c.getReturningCustomer()){
                            returning = true;
                        }
                        System.out.println(hotel.viewOrderedAvailableRooms(resDate,stayDuration, returning));
                        System.out.println("\nPlease enter the room number you wish to reserve: ");
                        int rmNum = 0;
                        while(rmNum==0){
                            rmNum=checkChoiceInput(scan.nextLine(),0, hotel.getNumberOfRooms()-1);
                        }
                        if(hotel.getRoom(rmNum).canReserve(resDate,stayDuration)) {

                                System.out.println("Please enter a card number:");
                                String cardNum  = scan.nextLine();
                                while(cardNum.length()< 15 && cardNum.length()<13){
                                    System.out.println("Could not process card. Try typing it in again ");
                                    cardNum = scan.nextLine();
                                }


                                hotel.getRoom(rmNum).addReservation(resDate,stayDuration);
                                hotel.addReservation(c,hotel.getRoom(rmNum),resDate,stayDuration, cardNum);

                                valid = true;
                                System.out.println("Your reservation has been made.");
                                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                System.out.println("Check in is at after 2pm on " + dateFormat.format(resDate.getTime()));
                                Calendar copyDate3 = new GregorianCalendar(resYear,resMonth-1,resDay+stayDuration);
                                System.out.println("Check out is before 11 am on " + dateFormat.format(copyDate3.getTime()));

                        }else{
                            System.out.println("Room not available.");
                        }



            }

            else if(option == 4){
                //CANCEL ROOM RESERVATION
                System.out.println("Cancel Room Reservation");
                System.out.println("We are sorry to hear you will not be staying with us please contact us with any complaints... or compliments :)");
                System.out.println("Which room do you wish to cancel?");
                int rmNum = 0;
                while (rmNum == 0) {
                    rmNum = checkChoiceInput(scan.nextLine(), 1, hotel.getNumberOfRooms() - 1);
                }
                //actually cancel the reservation
                Reservation removed = hotel.removeReservation(c,rmNum);
                if (removed!=null){
                    hotel.getRoom(rmNum).removeReservation(removed.getCheckInDate(),removed.getNightDurration());
                    System.out.println("Your reservation has been cancelled.");
                }
                else{
                    System.out.println("This reservation does not exist.");
                }
                System.out.println();
            }

            else if(option == 5){
                return 4;
            }
            System.out.println();
            option = 0;
        }
        return 4;
    }

    public int checkedInMenu(CustomerInterface c,int rmNum){
        System.out.println("Welcome! We hope you enjoy your stay.");
        Requests myRequests = new Requests();
        int choice = 0;
        while(choice!=3){
            choice=0;
            System.out.println("What can we do for you?");
            System.out.println("1) View Requests");
            System.out.println("2) Make a Request");
            System.out.println("3) Checkout");
            while(choice==0){
                choice = checkChoiceInput(scan.nextLine(),1,3);
            }
            if(choice==1){
                myRequests.viewRequests();
            }
            else if(choice==2){
                myRequests.viewRequests();
                System.out.println("Do you wish to make a request?");
                char yOrN = 1;
                while(yOrN==1){
                    yOrN = checkYorN(scan.nextLine());
                }
                switch (yOrN){
                    case 'y':
                        myRequests.makeRequest(rmNum);
                        break;
                    case 'n':
                        System.out.println("Let us know if we can help with anything.");
                        break;
                }
            }else {
                //checkout
                c.setReturningCustomer(true);
                System.out.println("Thank you for choosing to stay with us!");
                System.out.println("Would you like to print a receipt? Type '1' for yes or '0' for no");
                int wantReceipt = scan.nextInt();

                while(wantReceipt != 1 && wantReceipt != 0){
                    System.out.println("Try again");
                    wantReceipt = scan.nextInt();
                }

                if(wantReceipt==1){

                    //print the reciept

                }

            }
            System.out.println();
        }
        return 4;
    }

    public static void main(String[] args) {
        CustomerUI ui = new CustomerUI();
        ui.mainScreen();
    }
}
