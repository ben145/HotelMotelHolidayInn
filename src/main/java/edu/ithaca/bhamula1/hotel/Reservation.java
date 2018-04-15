package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {

    CustomerInterface customer;
    RoomInterface room;
    //how many nights they are going to stay
    int nightDurration;
    Calendar checkInDate;

    public Reservation(){
        this.customer = new Customer();
        this.room = new Room();
        this.nightDurration = -1;
        this.checkInDate = new GregorianCalendar(1,Calendar.JANUARY,1);
    }


    public Reservation (CustomerInterface customer, RoomInterface room, Calendar checkInDate, int nightDurration){
        this.customer = customer;
        this.room = room;
        this.nightDurration = nightDurration;
        this.checkInDate = checkInDate;
    }

    public CustomerInterface getCustomer(){
        return this.customer;
    }

    public RoomInterface getRoom(){
        return this.room;
    }

    public Calendar getCheckInDate(){
        return this.checkInDate;
    }

    public int getNightDurration(){
        return this.nightDurration;
    }


}
