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

    @Test
    void checkYorNTest(){
        CustomerUI myUI = new CustomerUI();
        myUI.checkYorN("yes");
        assertEquals('y',myUI.checkYorN("y"));
        assertEquals('y',myUI.checkYorN("Yes"));
        assertEquals('y',myUI.checkYorN("Yesterday"));
        assertEquals('y',myUI.checkYorN("y"));
        assertEquals('y',myUI.checkYorN("yes"));
        assertEquals('y',myUI.checkYorN("yep"));
        assertEquals('y',myUI.checkYorN("yepppers"));
        assertEquals('n',myUI.checkYorN("N"));
        assertEquals('n',myUI.checkYorN("No"));
        assertEquals('n',myUI.checkYorN("Nope"));
        assertEquals('n',myUI.checkYorN("n"));
        assertEquals('n',myUI.checkYorN("no"));
        assertEquals('n',myUI.checkYorN("nope"));
        assertEquals('n',myUI.checkYorN("nah"));
        assertEquals(1,myUI.checkYorN("hi"));
        assertEquals(1,myUI.checkYorN("01836"));
        assertEquals(1,myUI.checkYorN("GB52537"));
    }

}