package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/22/2018.
 */

public class Room implements RoomInterface {

    boolean available;
    int roomNumber;
    double price;
    int bedNum;
    String bedType;
    String amenities; // such as view?
    boolean checkedIn;
    String reservationName;

    public Room(boolean avail, int roomNum, double price, int bedNum, String bedType, String amenities){
        this.available = avail;
        this.roomNumber = roomNum;
        this.price = price;
        this.bedNum = bedNum;
        this.bedType = bedType;
        this.amenities = amenities;
        this.checkedIn = false;
    }

    public Room(){
        //if the room doesn't have data, it shouldn't be allowed to reserve
        this.available = false;
        this.roomNumber = 0;
        this.price = 0;
        this.bedNum = 0;
        this.bedType = "";
        this.amenities = "";
        this.checkedIn = false;
    }


    public void setIfAvailable(boolean avail){
        this.available = avail;
    }

    public void setRoomNumber(int num){
        this.roomNumber = num;

    }

    public void setRoomPrice(double price){
        this.price = price;

    }

    public void setBedCount(int num){
        this.bedNum = num;
    }

    public void setBedType(String type){
        this.bedType = type;
    }

    public void addAmenities(String amenities){

        if(this.amenities.length()> 0){
            this.amenities = this.amenities + ", " + amenities;

        }else{
            this.amenities = amenities;
        }
    }


    public boolean getIfAvailable(){
        return this.available;
    }

    public int getRoomNumber(){
        return this.roomNumber;
    }

    public int getBedCount(){
        return this.bedNum;
    }

    public double getRoomPrice(){
        return this.price;
    }

    public String getBedType (){
        return this.bedType;
    }

    public String getAmenities(){
        return this.amenities;
    }


    public String toString(){
        return "Room: " + this.roomNumber + " Type: "+ this.bedNum + " " + this.bedType+" bed(s) Amenities: " + this.amenities +
                " Price: $"+ this.price + " Available: " + this.available;
    }

    public boolean checkIn(CustomerInterface customer){
        if(customer.getName().equals(this.reservationName)){
            this.checkedIn=true;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkOut(CustomerInterface customer){
        if(customer.getName().equals(this.reservationName)&&this.checkedIn){
            this.checkedIn=false;
            this.available = true;
            this.reservationName=null;
            return true;
        }
        else {
            return false;
        }
    }

    public void setReservationName(String name){
        this.reservationName = name;
    }

    public String getReservationName() {
        return reservationName;
    }

    public boolean getCheckedIn(){
        return this.checkedIn;
    }

    public void makeRequestFromRoom(){
        Requests req = new Requests();
        req.makeRequest(roomNumber);
    }

    public void viewAvailableRequests(){
        Requests req = new Requests();
        req.viewRequests();
    }

}
