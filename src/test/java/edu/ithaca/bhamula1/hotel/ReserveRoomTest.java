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
        public Room (boolean a, int rID){
            available = a;
            roomID = rID;
            amenities.add("hot-tub");
            amenities.add("sleeping bag");
        }

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
    void emptyRoomLIstTest() {

        List<Room> testRoomList = new ArrayList<Room>();

//        for(int r = 0; r< 3; r++){
//            Room test = new Room();
//            test.setRoomData();
//            System.out.println(test);
//        }

        boolean testList = testRoomList.isEmpty();

       assertEquals(true, testList,"There are no rooms in room list");
    }

    @Test
    void availableRoomsTest(){
        List<Room> testRoomList = new ArrayList<Room>();
        Room rm1 = new Room(true, 110 );
        Room rm2 = new Room(false, 120 );


        testRoomList.add(rm1);
        testRoomList.add(rm2);
        String testAvail = ""+testRoomList.get(0);
        String testRmResult="";


        for (int s = 0; s < testRoomList.size(); s++) {
            if (testRoomList.get(s).available) {
                testRmResult += testRoomList.get(s);
           //     System.out.println(testRoomList.get(s));
            }
        }
        System.out.println(testRmResult);
        assertEquals(""+testRoomList.get(0), testAvail,"Returned room is not available"+Thread.currentThread().getStackTrace()[0].getLineNumber());


    }


}
