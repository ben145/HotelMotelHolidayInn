package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.*;

public abstract class Main {

    public static HotelInterface createHotel(){return new Hotel();}

    /**
     *
     * @param h
     * @author - DMF
     */
    public static void employeeLogScreen(HotelInterface h){
        EmployeeUI eUI = new EmployeeUI();
        eUI.uiInteraction(h);
    }

    public static void main(String[] args) {
        CustomerUI ui = new CustomerUI();
        ui.mainScreen();
    }

}
