package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReserveRoomTest {
    class Room{

        public boolean available;
        public int roomID;
        public List<String> amenities;

        public void setRoomData(){
            int testAv = (int)Math.random()*1;
            if(testAv == 0){
                available = false;
            }else{
                available = true;
            }

            if(available){
                roomID = 100+(int)Math.random()*11;
                if(roomID%2==0) {
                    amenities.add("mini-bar");
                    amenities.add("room Service");
                    amenities.add("beach view");
                    amenities.add("mini Fridge");
                    amenities.add("king bed");
                }
                if(roomID%3==0){
                    amenities.add("beach view");
                    amenities.add("mini Fridge");
                    amenities.add("double Bed");
                }
                if(roomID%5==0){
                    amenities.add("hot-tub");
                    amenities.add("sleeping bag");
                }
            }
        }
    }
    @Test
    void availableRoomTest() {

        List<Room> testRoomList = new ArrayList<Room>();
        for(int r = 0; r< 3; r++){
            Room test = new Room();
            test.setRoomData();
            System.out.println(test);
        }


       // assertEquals(true, "Room is available");
    }
}
