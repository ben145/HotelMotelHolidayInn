package edu.ithaca.bhamula1.hotel;

import java.text.SimpleDateFormat;
import java.util.*;

public class Reservation {

    public enum PaymentType{
        CASH, CARD, NODATA
    }

    CustomerInterface customer;
    RoomInterface room;
    //how many nights they are going to stay
    int nightDurration;
    Calendar checkInDate;
    String cardPayment;
    PaymentType paymentType;


    public Reservation(){
        this.customer = new Customer();
        this.room = new Room();
        this.nightDurration = -1;
        this.checkInDate = new GregorianCalendar(1,Calendar.JANUARY,1);
        this.cardPayment = "";
        this.paymentType = PaymentType.NODATA;
    }


    public Reservation (CustomerInterface customer, RoomInterface room, Calendar checkInDate, int nightDurration, String cardPayment){
        this();
        this.customer = customer;
        this.room = room;
        this.nightDurration = nightDurration;
        this.checkInDate = checkInDate;
        if(cardPayment.length()>16 || cardPayment.length()<14){
            System.out.println("hello " + cardPayment.length());
            this.cardPayment = "";
        }else{
            this.cardPayment = cardPayment;
        }

        this.paymentType = PaymentType.NODATA;

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

    public String getCardPayment (){
        return this.cardPayment;
    }

    public void setCardPayment(String card ){
        if(card.length()<= 16 && card.length()>= 14){
            this.cardPayment = card;

        }
    }

    public void setPaymentType(PaymentType paymentType){
        this.paymentType = paymentType;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

        return customer.getName() + " Room " + room.getRoomNumber() + " Check in" + dateFormat.format(checkInDate.getTime()) + " Nights" + nightDurration;
    }

}
