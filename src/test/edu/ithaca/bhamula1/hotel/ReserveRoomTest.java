package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReserveRoomTest {
    class Room{

        private boolean available;
        private int roomID;
        private List<String> amenities = new ArrayList<>();

        private void setRoomData(){
            int testAv = (int)Math.round(Math.random());
            System.out.println(testAv);
            if(testAv == 0){
                available = false;
            }else{
                available = true;
            }

            if(available){
                roomID = (int)(Math.random()*11)+100;
                if(roomID%2==0&&amenities.isEmpty()) {
                    amenities.add("mini-bar");
                    amenities.add("room Service");
                    amenities.add("beach view");
                    amenities.add("mini Fridge");
                    amenities.add("king bed");
                }
                if(roomID%3==0&&amenities.isEmpty()){
                    amenities.add("beach view");
                    amenities.add("mini Fridge");
                    amenities.add("double Bed");
                }
                if(roomID%2==1&&amenities.isEmpty()){
                    amenities.add("hot-tub");
                    amenities.add("sleeping bag");
                }
            }
        }
        @Override
        public String toString(){
            return getClass().getSimpleName()+"Is Avail: "+available+"\n"+"Room Number: "+roomID+"\n"+"Amenities: "+amenities;
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
