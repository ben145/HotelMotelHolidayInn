package edu.ithaca.bhamula1.hotel;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Employee UI
 * @author DMF
 */

public class EmployeeUI implements EmployeeUI_Interface{

    private Employee employee;
    private HotelInterface hotel;
    private boolean employeeScreenActive;

    // added this to use orderables - think the orderables should be in Hotel as a list with room number associated?
    private Requests requests;
    boolean invalid = true;

    public EmployeeUI(){
        requests = new Requests();
        employeeScreenActive = true;
    }

    /**
     * sets Employee to this instance of login screen
     * @param e employee associated with this login screen
     */
    void setEmployee(EmployeeIMPL e){
        this.employee = e;
    }

    /**
     * returns employee title - used for test
     * @return employee's title
     */
    public String getEmplTitle(){
        return employee.getE_Title();
    }

    /**
     * All UI interaction below - Employee can make choice if logged in
     * Takes in Hotel object to get employee list and other data associated with the hotel
     * @param h
     */
    public void uiInteraction(HotelInterface h){
        this.hotel = h;
        System.out.println("------<>----Employee Page----<>------");
        employeeLogScreenUI();
        while(employeeScreenActive){
            if(!invalid){
                Scanner scan = new Scanner(System.in);
                System.out.print("\nWhat would you like to do?\n1) View Open Customer Requests\n" +
                        "2) Select Customer Request\n" +
                        "3) Complete Customer Request\n" +
                        "4) LogOut\n"+"\nEnter Number Here -> ");
                int option = scan.nextInt();
                if(option == 1){
                    System.out.println("\nPlease see open Customer Requests below:");
                    viewOpenRequests();
                    System.out.println("\n");
                }else if(option == 2){
                    System.out.println("\nPlease enter customer request number to complete below:\n");
                    viewOpenRequests();

                }else if(option == 3){
                    System.out.println("\nMark customer request Complete in option 3");
                }else if(option==4){
                    closeUI();
                }


            }else{
                employeeScreenActive=false;
            }

        }
    }

    /**
     * Before employee can chose options, valid input
     * must be entered - this method checks for valid input
     * and calls checkEmployeeLogIn to verify login data
     */
    @Override
    public void employeeLogScreenUI() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter Employee LogIn ID:");
        String eID = scan.nextLine();
        System.out.print("\nEnter your password:");
        String ePwd = scan.nextLine();

        if(eID.isEmpty()||ePwd.isEmpty()){
            System.out.println("\nInput cannot be blank, please try again\n");
            employeeLogScreenUI();
        }else{
            checkEmployeeLogIn(eID,ePwd);
            if(!invalid){
                System.out.println("\nWelcome "+employee.getE_FirstName()+" "+
                        employee.getE_LastName()+" - "+employee.getE_Title());
            }else{
                System.out.println("\nYou have entered an invalid employee login ID/Password combination.\n" +
                        "Access Denied\n");
            }
        }
    }

    /**
     * Validates E Login and PWD based on user input
     * compared to employee list
     * @param el
     * @param epwd employee password
     * @author - DMF
     */
    @Override
    public void checkEmployeeLogIn(String el, String epwd){

        int index = 0;
        List<EmployeeIMPL> empls = hotel.getEList();
        Iterator iterator = hotel.getEList().iterator();
        while(iterator.hasNext() && index!=hotel.getEList().size()){
            //System.out.println(index + "  " +employees.size());
            //System.out.println(employees.get(index).toString());
            empls.get(index).checkE_LoginID(el);
            if(empls.get(index).checkE_LoginID(el)
                    && empls.get(index).checkE_PWD(epwd)){
                invalid = false;
                // store employee values in UI
                setEmployee(empls.get(index));
                if(!employee.getE_LoggedIn()){
                    employee.setE_LoggedIn(true);
                }
            }
            index++;
        }
    }

    @Override
    public void viewOpenRequests() {
        requests.viewRequests();

    }

    @Override
    public void takeRequests() {
        viewOpenRequests();
        for (int i = 0; i < requests.orderables.size(); i++) {
            if (requests.)
        }
    }

    @Override
    public void completeRequest() {

    }

    /**
     * Employee logout
     * closes/ends employeeUI and returns to main screen menu
     */
    @Override
    public void closeUI() {
        employee.setE_LoggedIn(false);
        employeeScreenActive = false;
        System.out.println("\n"+employee.getE_FirstName()+", you are now logged out of {insert hotel name here} employee system\n\n");
        System.out.println("------<>----End Employee Page----<>------\n\n");

    }
}
