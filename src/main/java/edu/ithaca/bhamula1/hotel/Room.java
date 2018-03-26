package edu.ithaca.bhamula1.hotel;

/**
 * Created by Ben on 3/22/2018.
 */
public class Room {
    private boolean isReserved;
    private boolean checkedIn;
    private int number;

    public Room(int num){
        number = num;
        isReserved = false;
        checkedIn = false;
    }

    public boolean getIsReserved(){
        return this.isReserved;
    }
}
