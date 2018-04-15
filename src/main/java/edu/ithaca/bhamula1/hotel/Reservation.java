package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {

    Customer customer;
    Room room;
    //how many nights they are going to stay
    int nightDurration;
    Calendar checkInDate;

    public Reservation(){
        this.customer = new Customer();
        this.room = new Room();
        this.nightDurration = -1;
        this.checkInDate = new GregorianCalendar(1,Calendar.JANUARY,1);
    }


    public Reservation (Customer customer, Room room, Calendar checkInDate, int nightDurration){
        this.customer = customer;
        this.room = room;
        this.nightDurration = nightDurration;
        this.checkInDate = checkInDate;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    public Room getRoom(){
        return this.room;
    }

    public Calendar getCheckInDate(){
        return this.checkInDate;
    }

    public int getNightDurration(){
        return this.nightDurration;
    }


}
