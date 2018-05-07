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
    private int nightDuration;
    private Calendar checkInDate;
    private String cardPayment;
    private PaymentType paymentType;
    private Map<String, Double> paymentTracker;

    public final static double PRICE_OFF = 0.1;

    public Reservation(){
        this.customer = new Customer();
        this.room = new Room();
        this.nightDuration = -1;
        this.checkInDate = new GregorianCalendar(1,Calendar.JANUARY,1);
        this.cardPayment = "";
        this.paymentType = PaymentType.NODATA;
        this.paymentTracker = new HashMap<String, Double>();
    }


    public Reservation (CustomerInterface customer, RoomInterface room, Calendar checkInDate, int nightDuration, String cardPayment){
        this();
        this.customer = customer;
        this.room = room;
        this.nightDuration = nightDuration;
        this.checkInDate = checkInDate;
        if(cardPayment.length()>16 || cardPayment.length()<14){
            this.cardPayment = "";
        }else{
            this.cardPayment = cardPayment;
        }
        this.paymentType = PaymentType.NODATA;

        boolean returning = false;
        if(customer.getReturningCustomer()){
            returning = true;
        }

        if(returning){
            paymentTracker.put("Room Price (for "+ nightDuration + " nights)",  (room.getRoomPrice()- (room.getRoomPrice() * PRICE_OFF)) * nightDuration);

        }else{
            paymentTracker.put("Room Price (for "+ nightDuration + " nights)", (room.getRoomPrice()*nightDuration));

        }


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

    public int getNightDuration(){
        return this.nightDuration;
    }

    public String getCardPayment (){
        return this.cardPayment;
    }

    public void setCardPayment(String card ){
        if(card.length()<= 16 && card.length()>= 14){
            this.cardPayment = card;

        }
    }

    public void makeRequestFromReservation(){
        Requests req = new Requests();
        String newReq = req.makeRequest(room.getRoomNumber());
        if(newReq != ""){
            String[] reqInstance= newReq.split(",");
            paymentTracker.put(reqInstance[0], Double.parseDouble(reqInstance[1]));
        }
    }

    public void viewAvailableRequests(){
        Requests req = new Requests();
        req.viewRequests();
    }

    public void setPaymentType(PaymentType paymentType){
        this.paymentType = paymentType;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyy");

        return customer.getName() + " Room " + room.getRoomNumber() + " Check in: " + dateFormat.format(checkInDate.getTime()) + " Nights: " + nightDuration;
    }


    public Map<String, Double> getPaymentTracker(){
        return this.paymentTracker;
    }
}
