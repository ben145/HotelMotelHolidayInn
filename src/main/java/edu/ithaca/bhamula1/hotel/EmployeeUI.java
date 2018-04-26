package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static edu.ithaca.bhamula1.hotel.Hotel.activeRequests;
import static edu.ithaca.bhamula1.hotel.Hotel.viewInventory;

/**
 * Employee UI
 * @athor DMF
 */

public class EmployeeUI implements EmployeeUI_Interface{

    private Employee employee;
    private HotelInterface hotel;
    private boolean employeeScreenActive;

// added this to use requests - think the requests should be in Hotel as a list with room number associated?
    private Requests requests;
    boolean invalid = true;

    public EmployeeUI(){
        requests = new Requests();
        employeeScreenActive = true;
    }

    /**
     * sets Employee to this instance of login screen
     * @param e
     */
    void setEmployee(EmployeeIMPL e){
        this.employee = e;
    }

    /**
     * returns employee title - used for test
     * @return
     */
    public String getEmplTitle(){
        return employee.getE_Title();
    }

    /**
     * All UI interaction below - Employee can make choice if logged in
     * Takes in Hotel object oto get employee list and other data associated with the hotel
     * @param h
     */
    public void uiInteraction(HotelInterface h){
        this.hotel = h;
        System.out.println("------<>----Employee login page----<>------");
        employeeLogScreenUI();
        while(employeeScreenActive){
            if(!invalid){
                System.out.println("\n@>--->---- Employee Menu Page ----<---<@");

                Scanner scan = new Scanner(System.in);
                System.out.print("\nWhat would you like to do, "+employee.getE_FirstName()+"?\n\n\t> Please select a menu number 1-4 below <\n\n\t1) View Open Customer Requests\n" +
                        "\t2) Select Customer Request\n" +
                        "\t3) Complete Customer Request\n" +
                        "\t4) LogOut\n"+"\nEnter Number Here -> ");
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
                System.out.println("\n---> Employee Has Succesfully Logged In");
                System.out.println("\n\n\tWelcome "+employee.getE_FirstName()+" "+
                        employee.getE_LastName()+" - "+employee.getE_Title()+"\n\n\t\t\t\t<o> <o>\n\t\t\t\t   o\n\t\t\t\t \\___/\n\n ----<---<@ We value your service @>--->--");
            }else{
                System.out.println("\n** You have entered an invalid employee login ID/Password combination. **\n" +
                        "\n\t\t*** Access Denied To Employee System ***\n\n\n ---<--<@   Main Menu   @>-->---\n");
            }
        }
    }

    /**
     * Validates E Login and PWD based on user input
     * compared to employee list
     * if login is accurate, save employee state
     * @param el
     * @param epwd
     * @author - DMF
     */
    @Override
    public void checkEmployeeLogIn(String el, String epwd){

        int index = 0;
        List<EmployeeIMPL>empls = hotel.getEList();
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
                    hotel.saveEmplList();
                }
            }
            index++;
        }
    }

    /**
     * Function displays active requests made for specific room numbers
     * uses the static list of activeRequests in Hotel
     * If no requests, displays a message that there are no active requests
     * @author DMF
     */
    @Override
    public void viewOpenRequests() {

        if(Hotel.activeRequests.size()>0) {

            System.out.println("\n-- Current number of Active Guest Requests -->  "+ Hotel.activeRequests.size()+"\n");
            for (int a = 0; a < activeRequests.size(); a++) {
                System.out.format("%-15s %15s %n","Active Request: " + (a+1) , "~ Room Number: " + activeRequests.get(a).roomNumber + "\t request:  " + activeRequests.get(a).request);
            }
            System.out.println("\n---- End Active Guest Requests ----");
        }else{
            System.out.println("\n<-- There are no Active Guest Requests at this time -->\n");
        }

    }

    @Override
    public void takeRequests() {

    }

    @Override
    public void completeRequest() {

    }

    public void viewHotelInventory(){
        viewInventory();
    }

    /**
     * Employee logout
     * closes/ends employeeUI and returns to main screen menu
     * saves state of employee
     * @author DMF
     */
    @Override
    public void closeUI() {
        employee.setE_LoggedIn(false);
        hotel.saveEmplList();
        employeeScreenActive = false;
        System.out.println("\n"+employee.getE_FirstName()+", you are now logged out of {insert hotel name here} employee system\n\n");
        System.out.println("------<>----End Employee Page----<>------\n\n\n ---<--<@   Main Menu   @>-->---\n");

    }
}
