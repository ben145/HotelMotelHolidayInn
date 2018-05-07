package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static edu.ithaca.bhamula1.hotel.Hotel.activeRequests;
import static edu.ithaca.bhamula1.hotel.Hotel.viewInventory;

/**
 * Employee UI
 * @author DMF
 */

public class EmployeeUI implements EmployeeUI_Interface{

    private Employee employee;
    private HotelInterface hotel;
    private boolean employeeScreenActive;

    private Requests requests;
    private boolean invalid = true;

    // Employee User Interface constructor
    public EmployeeUI() {
        requests = new Requests();
        employeeScreenActive = true;
    }

    public String getEmplTitle(){return employee.getE_Title();}

    public void setEmployee(EmployeeIMPL e){this.employee = e;}

    public void uiInteraction(HotelInterface h) {
        this.hotel = h;
        System.out.println("------<>----Employee login page----<>------");
        employeeLogScreenUI();
        while (employeeScreenActive) {
            if (!invalid) {
                System.out.println("\n@>--->---- Employee Menu Page ----<---<@");

                Scanner scan = new Scanner(System.in);
                System.out.print("\nWhat would you like to do, "+employee.getE_FirstName()+"?\n\n\t> Please select a menu number 1-7 below <\n\n\t1) View Open Customer Requests\n" +
                        "\t2) Select Customer Request\n" +
                        "\t3) Complete Customer Request\n" +
                        "\t4) Add New Request to Hotel Repertoire\n" +
                        "\t5) Remove Request Hotel Repertoire\n" +
                        "\t6) View Hotel Inventory\n" +
                        "\t7) LogOut\n"+"\nEnter Number Here -> ");
                int option = scan.nextInt();
                if (option == 1) {
                    System.out.println("\nPlease see open Customer Requests below:");
                    viewOpenRequests();
                    System.out.println("\n");
                }
                else if (option == 2) {
                    if (!employee.getE_Available()) {
                        System.out.println("You must complete the current request assigned to you before you can help another customer\n");
                        System.out.println("Please continue to work on the following request:\n");
                        checkEmpReq();
                    } else {
                        System.out.println("\nPlease enter customer request number to work on below:\n");
                        viewOpenRequests();
                        takeRequests(scan.nextInt());
                    }

                }
                else if (option == 3) {
                    if (checkEmpReq()) {
                        System.out.println("\nPlease enter customer request number you wish to complete below:\n");
                        completeRequest(scan.nextInt());
                    }
                    else
                        System.out.println("\nYou currently are not assigned to any customer requests\n");
                }
                else if (option == 4)
                    requests.addRequest();

                else if (option == 5)
                    requests.removeRequest();

                else if (option == 6)
                    Hotel.viewInventory();

                else if (option == 7)
                    closeUI();

            }
            else
                employeeScreenActive=false;
        }
    }

    @Override
    public void checkEmployeeLogIn(String el, String epwd){
        int index = 0;
        List<EmployeeIMPL> empls = hotel.getEList();
        Iterator iterator = hotel.getEList().iterator();
        while (iterator.hasNext() && index != hotel.getEList().size()) {
            empls.get(index).checkE_LoginID(el);
            if (empls.get(index).checkE_LoginID(el)
                    && empls.get(index).checkE_PWD(epwd)) {
                invalid = false;
                // store employee values in UI
                setEmployee(empls.get(index));
                if (!employee.getE_LoggedIn()) {
                    employee.setE_LoggedIn(true);
                    hotel.saveEmplList();
                }
            }
            index++;
        }
    }

    @Override
    public void employeeLogScreenUI() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter Employee LogIn ID: ");
        String eID = scan.nextLine();
        System.out.print("\nEnter your password: ");
        String ePwd = scan.nextLine();

        if( eID.isEmpty() || ePwd.isEmpty()) {
            System.out.println("\nInput cannot be blank, please try again.\n");
            employeeLogScreenUI();
        }
        else {
            checkEmployeeLogIn(eID,ePwd);
            if (!invalid) {
                System.out.println("\n---> Employee Has Succesfully Logged In");
                System.out.println("\n\n\tWelcome "+employee.getE_FirstName()+" "+
                        employee.getE_LastName()+" - "+employee.getE_Title()+"\n\n\t\t\t\t<o> <o>\n\t\t\t\t   o\n\t\t\t\t \\___/\n\n ----<---<@ We value your service @>--->--");
            }
            else
                System.out.println("\n** You have entered an invalid employee login ID/Password combination. **\n" +
                        "\n\t\t*** Access Denied To Employee System ***\n\n\n ---<--<@   Main Menu   @>-->---\n");
        }
    }

    @Override
    public void viewOpenRequests() {
        if (Hotel.activeRequests.size() > 0) {
            System.out.println("\n-- Current number of Active Guest Requests -->  "+ Hotel.activeRequests.size()+"\n");
            for (int a = 0; a < activeRequests.size(); a++)
                System.out.format("%-15s %15s %n","Active Request: " + (a+1) , "~ Room Number: " + activeRequests.get(a).roomNumber + "\t request:  " + activeRequests.get(a).request);
            System.out.println("\n---- End Active Guest Requests ----");
        }
        else
            System.out.println("\n<-- There are no Active Guest Requests at this time -->\n");
    }

    @Override
    public void takeRequests(int req) {
        for (int ar = 0; ar < activeRequests.size(); ar++) {
            if (ar+1 == req) {
                activeRequests.get(ar).employeeId = employee.getE_LogID();
                employee.setE_Available(false);
            }
        }
    }

    @Override
    public boolean checkEmpReq() {
        for (int ar = 0; ar < activeRequests.size(); ar++) {
            if (employee.getE_LogID().equals(activeRequests.get(ar).employeeId)) {
                System.out.format("%-15s %15s %n","Your Assigned Request: " + (ar+1) , "~ Room Number: " + activeRequests.get(ar).roomNumber + "\t request:  " + activeRequests.get(ar).request);
                return true;
            }
        }
        return false;
    }

    @Override
    public void completeRequest(int req) {
        for (int ar = 0; ar < activeRequests.size(); ar++) {
            if (ar+1 == req && activeRequests.get(ar).employeeId.equals(employee.getE_LogID())) {
                System.out.format("%-15s %15s %n","Request: " + (ar+1) , "~ Room Number: " + activeRequests.get(ar).roomNumber + "\t request:  " + activeRequests.get(ar).request);
                employee.setE_Available(true);
                activeRequests.remove(ar);
                System.out.println("Has been fulfilled");
            }
            else
                System.out.println("You have entered an invalid request number, try this again, please.\n");
        }
    }

    @Override
    public void closeUI() {
        employee.setE_LoggedIn(false);
        hotel.saveEmplList();
        employeeScreenActive = false;
        System.out.println("\n"+employee.getE_FirstName()+", you are now logged out of {insert hotel name here} employee system\n\n");
        System.out.println("------<>----End Employee Page----<>------\n\n\n ---<--<@   Main Menu   @>-->---\n");
    }

}
