package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Main {

    public static void main(String[] args) {
        HotelInterface hotel = createHotel();
        mainScreen(hotel);
        hotel.saveEmplList();

    }

    public static int onceLoggedIn(CustomerInterface customer, HotelInterface hotel){
        Scanner scan = new Scanner(System.in);

        int option =0;

        System.out.println("Welcome " + customer.getName());

        //maybe if reserved print a check in


        //loop until quit
        while(option!=5) {

            System.out.println("Would you like to \n" +
                    "1)check in \n" +
                    "2) check out \n" +
                    "3) review rooms \n" +
                    "4) reserve room \n" +
                    "5) quit\n");


            option = scan.nextInt();

            while (option < 1 || option > 5) {
                System.out.println("1)check in\n" +
                        "2) check out\n" +
                        "3) review rooms\n" +
                        "4) reserve room\n" +
                        "5) quit\n");

                option = scan.nextInt();
            }


            if (option == 1) {
                System.out.println("Check In");
                //scan.next();

                System.out.println("What room number: ");
                int rmNum = scan.nextInt();

                hotel.checkIn(rmNum, customer);

                if(customer.isCheckedIn()) {
                    checkedIn(customer, hotel);
                }

            } else if (option == 2) {
                System.out.println("Check Out");

                System.out.println("What room number: ");
                int rmNum = scan.nextInt();

                hotel.checkOut(rmNum, customer);

            } else if (option == 3) {
                System.out.println("Review Rooms");
                System.out.println("View Available Rooms  ");
                System.out.println(hotel.viewOrderedAvailableRooms());



        }else if(option ==4){
            System.out.println("Reserve Room");

                System.out.println("\nPlease enter your desired check in month (1-12)");
                int month = scan.nextInt();
                System.out.println("\nPlease enter your desired check in day");
                int day = scan.nextInt();

                System.out.println("\nPlease enter your desired check in year");
                int year = scan.nextInt();

                System.out.println("\n How many nights will you be stay?");
                int nightDuration = scan.nextInt();

                Calendar checkinDate = new GregorianCalendar(year,month-1,day);
                Calendar copyDate = new GregorianCalendar(year,month-1,day);

                System.out.println("Rooms Available For That Date Range:");
                System.out.println(hotel.viewOrderedAvailableRooms());


                System.out.println("Which room would you like to reserve?");
                int rmNum = scan.nextInt();

                System.out.println("WHY DO YOU NOT WORK " + hotel.getRoom(rmNum).canReserve(checkinDate,nightDuration));

                SimpleDateFormat dateFormat2 = new SimpleDateFormat("MM/dd/yyyy");

                System.out.println("WOOOOORK");
                List<Calendar> list = hotel.getRoom(rmNum).getNotAvailTheseDays();
                for(int i= 0; i< list.size(); i++){
                    System.out.println( "NOOOOOO! " + dateFormat2.format(list.get(i).getTime()));
                }

                Calendar copyDate2 = new GregorianCalendar(year,month-1,day);

                System.out.println("STOP CHANGING THE FKN DATE "+  dateFormat2.format(copyDate2.getTime()));

                if(hotel.getRoom(rmNum).canReserve(copyDate2,nightDuration)){
                    hotel.getRoom(rmNum).addReservation(copyDate2, nightDuration);
                    hotel.addReservation(customer, hotel.getRoom(rmNum), copyDate2, nightDuration);

                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

                    System.out.println("you have a reservation!");

                    System.out.println("Check in is at after 2pm on " + dateFormat.format(copyDate.getTime()));


                    Calendar copyDate3 = new GregorianCalendar(year,month-1,day+nightDuration);


                    System.out.println("Check out is before 11 am on " + dateFormat.format(copyDate3.getTime()));


                }else{
                    //shouldn't be able to get here
                    System.out.println("The Room is already reserved during this time");
                }



            }else if (option == 5) {
                return 4;
            }
        }
        return 4;

    }



    public static HotelInterface createHotel(){
        HotelInterface hotel = new Hotel();
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");

        hotel.createAccount("Brad","Keith");
//        System.out.println(hotel.getCustomer("Brad", "Keith").getId());


        return hotel;
    }


    public static void checkedIn(CustomerInterface customer, HotelInterface hotel){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome! We hope you enjoy your stay.");
        int req = 0;
        Requests myReq = new Requests();
        while(req!=5){
            System.out.println("What can we do for you?");
            myReq.viewRequests();
            System.out.println("5) Checkout");
            req = scan.nextInt();
            while(req<1||req>5){
                System.out.println("Invalid option");
                System.out.println("What can we do for you?");
                myReq.viewRequests();
                System.out.println("5) Checkout");
                req = scan.nextInt();
            }
            if(req>0&&req<5){
                System.out.println("Your request will be fulfilled as soon as is possible.");
                System.out.println("Please let us know if there is anything else we can do for you.");
            }
            else if(req==5){
                hotel.checkOut(customer.getRoom(),customer);
            }
        }
    }

    public static void mainScreen(HotelInterface hotel){
        Scanner scan = new Scanner(System.in);

        int firstOption = 0;

        System.out.println("Welcome to {insert hotel name here}");

        //login or room view loop
        while (firstOption != 4) {
            System.out.println("Would you like to\n" +
                    "1) sign in\n" +
                    "2) sign up\n" +
                    "3) view rooms \n" +
                    "4) quit\n\n" +
                    "5) -Staff Login-");

            firstOption = scan.nextInt();

            while (firstOption < 1 || firstOption > 5) {
                System.out.println("1) sign in\n" +
                        "2) sign up\n" +
                        "3) view rooms\n " +
                        "4) quit\n\n"+
                        "5) -Staff Login-");

                firstOption = scan.nextInt();
            }

            if (firstOption == 1) {

                System.out.println("Sign In");


                System.out.println("Type Your First Name: ");
                //scan.next();

                String firstName = scan.next();
                System.out.println(firstName);

                System.out.println("Type Your User ID: ");
                //scan.next();

                String id = scan.next();
                System.out.println(id);
                if (hotel.getCustomer(id) == null) {
                    System.out.println("Incorrect account information");
                } else {
                    hotel.logIn(firstName, id);
                    if (hotel.getCustomer(id).getLoggedIn()) {
                        firstOption = onceLoggedIn(hotel.getCustomer(id), hotel);
                    }
                }


            } else if (firstOption == 2) {
                System.out.println("Sign Up");


                System.out.println("Type Your First Name: ");
                //scan.next();

                String firstName = scan.next();
                //System.out.println(firstName);


                System.out.println("Type Your Last Name: ");
                //scan.next();

                String lastName = scan.next();
                //System.out.println(lastName);


                hotel.createAccount(firstName, lastName);

                String id = hotel.getCustomer(firstName, lastName).getId();
                //System.out.println("Your ID is " + id);

                firstOption = onceLoggedIn(hotel.getCustomer(firstName, lastName), hotel);


                /**
                 * DMF worked
                 */
                //hotel.setCustomer();
                /**
                 * I commented this out to use hotel.setCustomer to est my functions to reserve room
                 * If you run main, select 2, and follow the instructions, I believe everythign is working on my end
                 *  ID is bob
                 */

                //onceLoggedIn(hotel.getCustomer("den", null), hotel);

            } else if (firstOption == 3) {
                System.out.println("View Rooms ");
                System.out.println(hotel.viewOrderedAvailableRooms());
                System.out.println("*If you would like to reserve a room, you must create an account or log in first*");
            } else if (firstOption == 4) {
                System.out.println("Thank you");
            } else if(firstOption == 5){

                // Employee Login call
                employeeLogScreen(hotel);

            }
        }

    }


    /**
     *
     * @param h
     * @author - DMF
     */
    public static void employeeLogScreen(HotelInterface h){

        EmployeeUI eUI = new EmployeeUI();
        eUI.uiInteraction(h);

       // h.printEmployeeList();
    }






}
