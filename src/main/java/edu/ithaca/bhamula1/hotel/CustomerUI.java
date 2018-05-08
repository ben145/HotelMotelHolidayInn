package edu.ithaca.bhamula1.hotel;


import java.text.SimpleDateFormat;
import java.util.*;

import static edu.ithaca.bhamula1.hotel.Main.createHotel;
import static edu.ithaca.bhamula1.hotel.Main.employeeLogScreen;

public class CustomerUI implements CustomerUIInterface {
    HotelInterface hotel;
    private Scanner scan = new Scanner(System.in);

    public CustomerUI(){
        hotel = createHotel();
    }

    @Override
    public void run() {
        mainScreen();
    }

    /***
     * Takes in a string of input from the user, returning the user's choice input
     *  if between given bounds, 0 otherwise
     * @param input String input from the user to be checked for validity
     * @param lowestChoice  lowest number option to be selected in the part of the menu
     * @param highestChoice highest number option to be selected in the part of the menu
     * @return  user's input if valid, 0 if input not within bounds or not a number
     */
    public int checkChoiceInput(String input, int lowestChoice, int highestChoice){
        try{
            int choice = Integer.parseInt(input);
            if(choice >= lowestChoice&&choice <= highestChoice)
                return choice;
            else {
                System.out.println("Invalid input. Please enter a choice from "+lowestChoice+" to "+highestChoice);
                return 0;
            }
        } catch(NumberFormatException e){
            System.out.println("Invalid input. Please enter a choice from "+lowestChoice+" to "+highestChoice);
            return 0;
        }
    }

    /**
     * Checks if input begins with a 'y' for 'Yes' or 'n' for 'No'
     * @param yOrN  user input, expected to be 'y'... or 'n'...
     * @return  the first letter of the input if started with 'y' or 'n', returns 1 otherwise
     */
    public char checkYorN(String yOrN){
        yOrN = yOrN.toLowerCase();
        char choice = yOrN.charAt(0);
        if (choice != 'y' && choice != 'n') {
            System.out.println("Invalid input. Please Enter 'yes' or 'no'.");
            return 1;
        }
        return choice;
    }

    /**
     *
     * @param firstName Customer's first name
     * @param ID        Customer's hotel ID
     * @param pass      Customer's password
     * @return 0 if sign in is unsuccessful, 4 if successful
     */
    public int signIn(String firstName, String ID, String pass) {
        System.out.println();
        if (hotel.getCustomer(ID) == null)
            System.out.println("\nOops! Incorrect account information was entered for sign in\nPlease review the menu and try again =o)\n");
        else {
            CustomerInterface cIn = hotel.logIn(firstName,ID,pass);
            if(cIn != null) {
                if (cIn.getLoggedIn())
                    return loggedIn(cIn, hotel);
            }
        }
        System.out.println("\n");
        return 0;
    }

    /**
     * Displays main screen available before any signing or logging in
     */
    public void mainScreen() {

        int firstOption = 0;

        // login or room view loop
        while (firstOption != 4) {
            System.out.println("=========================================================================================");
            System.out.println("Welcome to {insert hotel name here}");
            System.out.println("=========================================================================================");
            System.out.println();
            System.out.println("Would you like to:\n" +
                    "1) Sign in\n" +
                    "2) Sign up\n" +
                    "3) View rooms\n" +
                    "4) Quit\n" +
                    "5) -Staff Login-\n\n");

            // checkChoiceInput returns 0 if not within bounds (1 and 5) or if a non-numeric character
            while (firstOption == 0)
                firstOption = checkChoiceInput(scan.nextLine(), 1, 5);

            // SIGN IN
            if (firstOption == 1) {
                System.out.println("\n=========================================================================================");
                System.out.println("Sign In");
                System.out.println("=========================================================================================\n");
                System.out.println("Type your First Name: ");
                String firstName = scan.nextLine();

                System.out.println("Type your User ID: ");
                String id = scan.nextLine();

                System.out.println("Type your User Password: ");
                String p = scan.nextLine();

                firstOption = signIn(firstName, id, p);
            }

            // SIGN UP
            else if (firstOption == 2) {
                System.out.println("\n=========================================================================================");
                System.out.println("Sign Up");
                System.out.println("=========================================================================================\n");
                System.out.println("Type your First Name: ");
                String firstName = scan.nextLine();
                System.out.println("Type your Last Name: ");
                String lastName = scan.nextLine();
                String id = hotel.createAccount(firstName, lastName);

                firstOption = loggedIn(hotel.getCustomer(id), hotel);
            }

            // VIEW ROOMS
            else if (firstOption == 3){
                System.out.println("\n=========================================================================================");
                System.out.println("View Rooms ");
                System.out.println("=========================================================================================\n");
                System.out.println(hotel.viewOrderedRooms(false));
                System.out.println("*If you would like to reserve a room, you must create an account or log in first*\n");
            }

            // QUIT
            else if (firstOption == 4){
                System.out.println("\n=========================================================================================\n");
                System.out.println("Thank you.\n");
            }

            // EMPLOYEE LOGIN
            else if (firstOption == 5)
                employeeLogScreen(hotel);

            firstOption = 0;
        }
    }

    /**
     * Displays options for customers to make once logged in
     * @param c     Customer, to access their first name
     * @param hotel Hotel, to access reservation date to match against user input
     * @return  4
     */
    public int loggedIn(CustomerInterface c, HotelInterface hotel) {
        int option = 0;

        System.out.println("\n=========================================================================================");

        System.out.println("Welcome " + c.getFName()+"");

        while (option != 6) {
            System.out.println("=========================================================================================\n");
            System.out.println("=========================================================================================\n");
            System.out.println("Would you like to \n" +
                    "1) Check in \n" +
                    "2) Review rooms \n" +
                    "3) Reserve room \n" +
                    "4) Cancel room reservation \n" +
                    "5) View reservations \n" +
                    "6) Quit\n");
            while (option == 0)
                option = checkChoiceInput(scan.nextLine(),1,6);

            // CHECK IN
            if (option == 1) {
                System.out.println("\n=========================================================================================");
                System.out.println("Check In");
                System.out.println("=========================================================================================\n");
                System.out.println("What is your room number: ");
                int rmNum = 0;
                while(rmNum == 0) {
                    rmNum = checkChoiceInput(scan.nextLine(), 1, hotel.getNumberOfRooms());
                }
                System.out.println();
                Calendar today = new GregorianCalendar();
                today.set(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                Reservation res = hotel.getReservation(c,rmNum,today);
                if(hotel.checkIn(rmNum,c)){

                    System.out.println("\n=========================================================================================\n");

                    System.out.println("Would you like change the card on file. (Enter 'yes' or 'no') ");
                    char yesOrNo = 1;
                    while (yesOrNo == 1)
                        yesOrNo = checkYorN(scan.nextLine());

                    if (yesOrNo == 'y') {
                        System.out.println("\n=========================================================================================\n");
                        System.out.println("Type the new card number");
                        String card = scan.nextLine();

                        while (card.length() > 16 || card.length() < 14) {
                            System.out.println("Try again");
                            card = scan.nextLine();
                        }
                        res.setCardPayment(card);
                    }

                    System.out.println("\n=========================================================================================\n");
                    System.out.println("Would you like the room charged to the card on file or pay in cash? Type '1' for card or type '2' for cash");
                    int cardOrCash = 0;
                    while (cardOrCash == 0)
                        cardOrCash = checkChoiceInput(scan.nextLine(),1,2);
                    if (cardOrCash ==1)
                        res.setPaymentType(Reservation.PaymentType.CARD);
                    else
                        res.setPaymentType(Reservation.PaymentType.CASH);
                    c.setCheckedIn(true);
                    hotel.saveCustList();
                    checkedInMenu(c,res);

                    hotel.getRoom(rmNum).removeReservation(res.getCheckInDate(),res.getNightDuration());
                }
            }

            // REVIEW ROOMS
            else if (option == 2) {
                System.out.println("\n=========================================================================================");

                boolean returning = false;
                if (c.getReturningCustomer())
                    returning = true;

                System.out.println("Review Rooms");
                System.out.println("=========================================================================================\n");
                System.out.println("View Rooms  ");
                System.out.println(hotel.viewOrderedRooms(returning));
                System.out.println();
            }

            // RESERVE ROOM
            else if (option == 3) {
                System.out.println("\n=========================================================================================");
                System.out.println("Reserve Room");
                System.out.println("=========================================================================================\n");

                int currYear = Calendar.getInstance().get(Calendar.YEAR);
                System.out.println("Please enter the year you would like to reserve your room for. We only book up to three years ahead:");
                int resYear = 0;
                while (resYear == 0)
                    resYear = checkChoiceInput(scan.nextLine(),currYear,currYear+3);

                int currMonth = Calendar.getInstance().get(Calendar.MONTH);
                int x = 1;
                if (currYear == resYear)
                    x = currMonth+1;

                System.out.println("Please enter the month you would like to reserve for as a number("+x+"-12):");
                int resMonth = 0;
                currMonth++;
                while (resMonth == 0) {
                    if (resYear == currYear)
                        resMonth = checkChoiceInput(scan.nextLine(),currMonth,12);
                    else
                        resMonth = checkChoiceInput(scan.nextLine(),1,12);
                }
                resMonth = resMonth - 1;
                currMonth = currMonth - 1;
                Calendar calcCal = new GregorianCalendar(resYear,resMonth,1);
                int daysInMonth = calcCal.getActualMaximum(Calendar.DAY_OF_MONTH);
                System.out.println("What day of the month will your reservation start on?");
                int resDay = 0;
                int currDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                while (resDay == 0) {
                    if (resYear == currYear && resMonth == currMonth)
                        resDay = checkChoiceInput(scan.nextLine(), currDay, daysInMonth);
                    else
                        resDay = checkChoiceInput(scan.nextLine(), 1, daysInMonth);
                }
                System.out.println("How many nights will you be staying?");
                int stayDuration = 0;
                while (stayDuration == 0)
                    stayDuration = checkChoiceInput(scan.nextLine(),1,21);

                Calendar resDate = new GregorianCalendar(resYear,resMonth,resDay);

                boolean returning = false;
                if (c.getReturningCustomer())
                    returning = true;

                System.out.println(hotel.viewOrderedAvailableRooms(resDate,stayDuration, returning));
                System.out.println("\nPlease enter the room number you wish to reserve: ");
                int rmNum = 0;
                while (rmNum == 0)
                    rmNum = checkChoiceInput(scan.nextLine(),1, hotel.getNumberOfRooms()-1);
                if (hotel.getRoom(rmNum).canReserve(resDate,stayDuration)) {
                    System.out.println("Please enter a card number:");
                    String cardNum  = scan.nextLine();
                    while (cardNum.length() < 16 && cardNum.length() < 14) {
                        System.out.println("Could not process card. Try typing it in again ");
                        cardNum = scan.nextLine();
                    }

                    hotel.getRoom(rmNum).addReservation(resDate,stayDuration);
                    hotel.addReservation(c,hotel.getRoom(rmNum),resDate,stayDuration, cardNum);

                    System.out.println("Your reservation has been made.");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                    System.out.println("Check in is at after 2pm on " + dateFormat.format(resDate.getTime()));
                    Calendar copyDate3 = new GregorianCalendar(resYear,resMonth,resDay);
                    copyDate3.add(Calendar.DATE,stayDuration);
                    System.out.println("Check out is before 11 am on " + dateFormat.format(copyDate3.getTime()));
                }
                    else
                        System.out.println("Room not available.");
            }

            // CANCEL ROOM RESERVATION
            else if (option == 4){
                System.out.println("\n=========================================================================================");
                System.out.println("Cancel Room Reservation");
                System.out.println("=========================================================================================\n");
                System.out.println("We are sorry to hear you will not be staying with us please contact us with any complaints... or compliments :)");
                List<Reservation> reservations = hotel.getCustomerReservations(c);
                Iterator<Reservation> itr = reservations.iterator();
                System.out.println("Your current reservations:");
                if (!itr.hasNext())
                    System.out.println("You do not have any reservations");
                else {
                    System.out.println("Please enter the number printed next to the reservation you would like to cancel");
                    int ndx = 1;
                    while (itr.hasNext()) {
                        System.out.println(ndx+") "+itr.next().toString());
                        ndx++;
                    }
                    int pick = 0;
                    while (pick == 0)
                        pick = checkChoiceInput(scan.nextLine(),1,reservations.size());
                    Reservation removed = hotel.removeReservation(reservations.get(pick-1));
                    if (removed != null){
                        hotel.getRoom(reservations.get(pick-1).getRoom().getRoomNumber()).removeReservation(removed.getCheckInDate(),removed.getNightDuration());
                        System.out.println("Your reservation has been cancelled.");
                    }
                    else
                        System.out.println("This reservation does not exist.");
                }
            }

            // VIEW RESERVATIONS
            else if (option == 5){
                System.out.println("\n=========================================================================================");
                System.out.println("Your Reservations");
                System.out.println("=========================================================================================\n");
                List<Reservation> reservations = hotel.getCustomerReservations(c);
                Iterator<Reservation> itr = reservations.iterator();
                if (!itr.hasNext())
                    System.out.println("You do not have any reservations");
                else {
                    while (itr.hasNext())
                        System.out.println(itr.next().toString());
                }
            }

            else if(option == 6){
                System.out.println("\n=========================================================================================\n");
                return 4;
            }
            System.out.println();
            option = 0;
        }
        return 4;
    }

    /**
     * Displays options for customers to make once checked in; if checking out,
     *  an option for a receipt and to take a survey is given
     * @param c     Customer, to access their name and room number
     * @param res   Their reservation, to access their expenses to produce receipt
     */
    public void checkedInMenu(CustomerInterface c, Reservation res){
        System.out.println("\n=========================================================================================");
        System.out.println("Welcome! "+c.getFName()+" We hope you enjoy your stay in room "+c.getRoom());
        System.out.println("=========================================================================================\n");
        int choice = 0;
        while (choice != 3) {
            choice=0;
            System.out.println("What can we do for you?");
            System.out.println("1) View Requests");
            System.out.println("2) Make a Request");
            System.out.println("3) Checkout");
            System.out.println("\n4) -Staff Login-\n");
            while (choice==0)
                choice = checkChoiceInput(scan.nextLine(),1,4);

            if (choice == 1) {
                System.out.println("\n=========================================================================================");
                System.out.println("Available Requests");
                System.out.println("=========================================================================================\n");
                res.viewAvailableRequests();
                System.out.println("=========================================================================================");
            }
            else if (choice == 2) {
                System.out.println();
                System.out.println("=========================================================================================");
                System.out.println("Make a Request");
                System.out.println("=========================================================================================");
                System.out.println();
                //res.viewAvailableRequests();
//                System.out.println("Do you wish to make a request?");
//                char yOrN = 1;
//                while(yOrN==1){
//                    yOrN = checkYorN(scan.nextLine());
//                }
//                switch (yOrN){
//                    case 'y':
                        res.makeRequestFromReservation();
//                        break;
//                    case 'n':
//                        System.out.println("Let us know if we can help with anything.");
//                        break;
//                }
                System.out.println("\n=========================================================================================\n");
            }
            else if (choice == 4)
                employeeLogScreen(hotel);
            else {
                //checkout
                c.setReturningCustomer(true);
                c.setCheckedIn(false);
                hotel.saveCustList();
                System.out.println("\n=========================================================================================");
                System.out.println("Thank you for choosing to stay with us! \n");
                System.out.println("=========================================================================================\n");
                System.out.println("Would you like to print a receipt? Enter 'yes' or 'no'");
                char wantReceipt = 1;

                while (wantReceipt == 1)
                    wantReceipt = checkYorN(scan.nextLine());

                if (wantReceipt == 'y') {
                    System.out.println("\n=========================================================================================");
                    System.out.println("Your Receipt");
                    System.out.println("=========================================================================================\n");
                    Map<String, Double> costs = res.getPaymentTracker();

                    double totalCost = 0;
                    for (String thing : costs.keySet()) {
                        System.out.println(thing + " " + costs.get(thing));
                        totalCost += costs.get(thing);
                    }

                    System.out.println("\nTotal Cost: "+ totalCost);

                }
                System.out.println("=========================================================================================");
                System.out.println("Please give us feedback");
                System.out.println("=========================================================================================");
                System.out.println("Would you like to fill out an optional survey?('yes' or 'no')");
                char answer = 1;
                while(answer == 1)
                    answer = checkYorN(scan.nextLine());

                if (answer == 'y') {
                    Rating r = new Rating();
                    r.startRating();
                    System.out.println(r.toString(c));
                    r.saveRating(c);
                }
            }
            System.out.println();
        }
    }

}