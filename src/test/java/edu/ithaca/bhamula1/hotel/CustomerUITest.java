package edu.ithaca.bhamula1.hotel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerUITest {

    @Test
    void checkChoiceInputTest(){
        CustomerUI myUI = new CustomerUI();
        assertEquals(myUI.checkChoiceInput("Seven",1,5),0);
        assertEquals(myUI.checkChoiceInput("-1",1,5),0);
        assertEquals(myUI.checkChoiceInput("6",1,5),0);
        assertEquals(myUI.checkChoiceInput("6",1,6),6);
        assertEquals(myUI.checkChoiceInput("1",1,6),1);
        assertEquals(myUI.checkChoiceInput("3",1,6),3);
    }

}