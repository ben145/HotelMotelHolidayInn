package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    Inventory test = new Inventory("A Trainwreck", 1);

    @Test
    void getItemTest(){
        assertEquals("A Trainwreck",test.getItem(), "FAIL - item name not stored");
    }

    @Test
    void getQuantityTest(){
        assertEquals(1,test.getQuantity(), "FAIL - item quantity name not stored");
    }

    @Test
    void setItemTest() {
        test.setItem("A Working Class");
        assertEquals("A Working Class",test.getItem(),"FAIL - item name not changed");
    }

    @Test
    void setQuantityTest(){
        test.setQuantity(17);
        assertEquals(17,test.getQuantity(),"FAIL - item quantity not changed");
    }

}
