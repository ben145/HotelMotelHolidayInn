package edu.ithaca.bhamula1.hotel;

import java.util.*;

public class Main {


    public static void onceLoggedIn(Customer customer, Hotel hotel){
        Scanner scan = new Scanner(System.in);

        int option =0;

        System.out.println("Welcome " + customer.getName());

        //maybe if reserved print a check in

        System.out.println("Would you like to \n" +
                "1)check in \n" +
                "2) check out \n" +
                "3) review rooms \n" +
                "4) reserve room \n");

        option = scan.nextInt();

        while(option<1 || option> 4){
            System.out.println("1)check in\n" +
            "2) check out\n" +
            "3) review rooms\n" +
            "4) reserve room\n");

            option = scan.nextInt();
        }


        if(option==1){
            System.out.println("Check In");
            scan.next();

            System.out.println("What room number: ");
            int rmNum = scan.nextInt();

            hotel.checkIn(rmNum, customer);


        }else if(option ==2){
            System.out.println("Check Out");

            System.out.println("What room number: ");
            int rmNum = scan.nextInt();

            hotel.checkOut(rmNum, customer);
            System.out.println("Thank You For Visiting ");

        }else if(option ==3){
            System.out.println("Review Rooms");
            System.out.println("View Rooms ");
            System.out.println(hotel.viewOrderedAvailableRooms());

        }else if(option ==4){
            System.out.println("Reserve Room");



            //Denise! This is your spot!
        }



    }


    public static Hotel createHotel(){
        Hotel hotel = new Hotel();
        hotel.setNumberOfRooms(5);

        hotel.addRoom(2, true, 100,2, "double", "mini bar");
        hotel.addRoom(1, true, 100,2, "double", "mini bar");
        hotel.addRoom(4, true, 100,2, "double", "mini bar");
        hotel.addRoom(3, false, 100,2, "double", "mini bar");


        return hotel;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Hotel hotel = createHotel();

        int firstOption =0;

        System.out.println("Welcome to {insert hotel name here}");

        System.out.println("Would you like to\n" +
                "1) sign in\n" +
                "2) sign up\n" +
                "3) view rooms \n");

        firstOption = scan.nextInt();

        while(firstOption<1 || firstOption> 3){
            System.out.println("1) sign in\n" +
                    "2) sign up\n" +
                    "3) view rooms\n ");

            firstOption = scan.nextInt();
        }

        if(firstOption ==1){

            System.out.println("Sign In");


            System.out.println("Type Your First Name: ");
            scan.next();

            String firstName = scan.nextLine();

            System.out.println("Type Your User ID: ");
            scan.next();

            String id = scan.nextLine();
            hotel.logIn(firstName, id);

            onceLoggedIn(hotel.getCustomer(id), hotel);


        }else if(firstOption == 2){
            System.out.println("Sign Up");

            System.out.println("Type Your First Name: ");
            scan.next();

            String firstName = scan.nextLine();


            System.out.println("Type Your Last Name: ");
            scan.next();

            String lastName = scan.nextLine();


            hotel.createAccount(firstName, lastName);

            String id = hotel.getCustomer(firstName, lastName).getId();
            System.out.println("Your ID is "+id );

            onceLoggedIn(hotel.getCustomer(firstName, lastName), hotel);



        }else if(firstOption ==3){
            System.out.println("View Rooms ");
            System.out.println(hotel.viewOrderedAvailableRooms());

            System.out.println("\nWould you like to reserve one of the rooms above?\nEnter Y or N and hit enter");
            boolean valid = false;
            String respond = scan.next();
            System.out.println("TEst Rm NUm "+respond);


            while(!valid) {
                if (Objects.equals(respond,"Y")||Objects.equals(respond,"y")) {
                    System.out.println("\nPlease enter the room number you wish to reserve: ");
                    int rmNum = scan.nextInt();
                    System.out.println("TEst Rm NUm "+rmNum);
                    valid = true;
                } else if (Objects.equals(respond,"N")||Objects.equals(respond,"n")) {
                    valid = true;
                } else {
                    valid = false;
                }
            }
            //
            hotel.selectRoom();
        }















    }
}
