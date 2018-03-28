package edu.ithaca.bhamula1.hotel;

import java.util.*;

public class Main {


    public static int onceLoggedIn(Customer customer, Hotel hotel){
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


            } else if (option == 2) {
                System.out.println("Check Out");

                System.out.println("What room number: ");
                int rmNum = scan.nextInt();

                hotel.checkOut(rmNum, customer);

            } else if (option == 3) {
                System.out.println("Review Rooms");
                System.out.println("View Rooms ");
                System.out.println(hotel.viewOrderedAvailableRooms());

            } else if (option == 4) {
                System.out.println("Reserve Room");


                //Denise! This is your spot!
            } else if (option == 5) {
                return 4;
            }
        }
        return 4;
    }


    public static Hotel createHotel(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");

        hotel.createAccount("Brad","Keith");
        System.out.println(hotel.getCustomer("Brad", "Keith").getId());

        return hotel;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Hotel hotel = createHotel();

        int firstOption =0;

        System.out.println("Welcome to {insert hotel name here}");

        //login or room view loop
        while(firstOption!=4) {
            System.out.println("Would you like to\n" +
                    "1) sign in\n" +
                    "2) sign up\n" +
                    "3) view rooms \n"+
                    "4) quit");

            firstOption = scan.nextInt();

            while (firstOption < 1 || firstOption > 4) {
                System.out.println("1) sign in\n" +
                        "2) sign up\n" +
                        "3) view rooms\n "+
                        "4) quit");

                firstOption = scan.nextInt();
            }

            if (firstOption == 1) {

                System.out.println("Sign In");


                System.out.println("Type Your First Name: ");
                scan.next();

                String firstName = scan.nextLine();
                System.out.println(firstName);

                System.out.println("Type Your User ID: ");
                //scan.next();

                String id = scan.next();
                System.out.println(id);
                if(hotel.getCustomer(id)==null){
                    System.out.println("Incorrect account information");
                }
                else {
                    hotel.logIn(firstName, id);
                    if (hotel.getCustomer(id).getLoggedIn()) {
                        firstOption=onceLoggedIn(hotel.getCustomer(id), hotel);
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
                System.out.println("Your ID is " + id);

                firstOption=onceLoggedIn(hotel.getCustomer(firstName, lastName), hotel);


            } else if (firstOption == 3) {
                System.out.println("View Rooms ");
                System.out.println(hotel.viewOrderedAvailableRooms());
                System.out.println();
            }

            else if (firstOption == 4){
                System.out.println("Thank you");
            }
        }















    }
}
