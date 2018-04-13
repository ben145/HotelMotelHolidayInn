package edu.ithaca.bhamula1.hotel;

public class Reservation {


    Customer customer;
    Room room;

    public Reservation(){
        this.customer = new Customer();
        this.room = new Room();


    }

    public Reservation (Customer customer, Room room){
        this.customer = customer;
        this.room = room;
    }




}
