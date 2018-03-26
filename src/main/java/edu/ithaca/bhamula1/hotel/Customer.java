package edu.ithaca.bhamula1.hotel;

public class Customer extends User {
    private int roomNumber;
    private boolean checkedIn;

    public Customer(String firstname,String lastname){
        super(firstname,lastname);
        checkedIn = false;
    }

    //just for use in testing checkIn and checkOut
    public void fakeReserveRoom(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public boolean checkIn(int roomNumber){
        if(roomNumber == this.roomNumber){
            this.checkedIn = true;
            System.out.println("Welcome. Enjoy your stay in room number "+roomNumber+".");
            return true;
        }
        else{
            System.out.println("You have not reserved the selected room. Please reserve a room or try again with a different room number.");
            return false;
        }
    }

    public boolean checkOut(int roomNumber){
        //probably unnecessary but just in case
        if(!checkedIn){
            System.out.println("You are not currently checked in, you must be checked in to check out.");
            return false;
        }
        else if(roomNumber!=this.roomNumber){
            System.out.println("You are not checked into room "+roomNumber+" please make sure to use the number of the room you are checked in to.");
            return false;
        }
        else{
            checkedIn = false;
            //setting room number to a value to be recognized as not a valid room number
            roomNumber = -1;
            System.out.println("Thank you. We hope you enjoyed your stay.");
            return true;
        }
    }

    public boolean getCheckedIn(){
        return this.checkedIn;
    }

}
