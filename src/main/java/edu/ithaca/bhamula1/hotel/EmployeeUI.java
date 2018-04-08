package edu.ithaca.bhamula1.hotel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmployeeUI implements EmployeeUI_Interface{

    private Employee employee;
    private HotelInterface hotel;
    private boolean employeeScreenActive;

// added this to use requests - think the reqeusts should be in Hotel as a list with room number associated?
    private Requests requests;

    public EmployeeUI(){
        requests = new Requests();
        employeeScreenActive = true;
    }

    void setEmployee(EmployeeIMPL e){
        this.employee = e;
    }


    public void uiInteraction(HotelInterface h){
        this.hotel = h;
        System.out.println("Employee Screens");
        employeeLogScreenUI();
        if(employee.getE_LoggedIn()){
            System.out.println("Please see open Customer Requests below:\n");
            viewOpenRequests();
        }
    }

    @Override
    public void employeeLogScreenUI() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter Employee LogIn ID:\n");
        String eID = scan.nextLine();
        System.out.println("Enter your password:\n");
        String ePwd = scan.nextLine();

        if(eID.isEmpty()||ePwd.isEmpty()){
            System.out.println("Input cannot be blank, please try again\n");
            employeeLogScreenUI();
        }else{
            checkEmployeeLogIn(eID,ePwd);
            if(employee.getE_LoggedIn()){
                System.out.println("Welcome "+employee.getE_FirstName());
            }else{
                System.out.println("You have entered an invalid employee login ID/Password combination.\n" +
                        "Access Denied\n");
            }
        }
    }

    /**
     * Validates E Login and PWD
     * @param el
     * @param epwd
     * @return
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

    }

    @Override
    public void completeRequest() {

    }

    @Override
    public void closeUI() {

    }
}
