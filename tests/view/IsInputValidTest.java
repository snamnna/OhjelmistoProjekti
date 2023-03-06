package view;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.TextField;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import simulaattori.view.KayttajatiedotController;
import javax.swing.JTextField;

class IsInputValidTest {
	
    static KayttajatiedotController controller;
    
    static TextField aikaTextField = new TextField();
    static TextField yLaakariLkmTextField = new TextField();
    static TextField eLaakariLkmTextField = new TextField();
    
    @BeforeAll
    static void setUpBeforeClass(){
    	System.out.println("Testit alkaa");
    	System.out.println("AikaTextFieldin arvo: " + aikaTextField);
        controller = new KayttajatiedotController();
    }

    @Test
    void testIsInputValid() {
    	
        //Kaikki nämä nulleja, setText ei suostu asettamaan teksitä muuttujaan
    	if (IsInputValidTest.aikaTextField != null) {
        	IsInputValidTest.aikaTextField.setText("10");
        	assertEquals("10", aikaTextField.getText());
        } else {
            fail("aikaTextField is not initialized");
        }
        if (IsInputValidTest.yLaakariLkmTextField != null) {
        	IsInputValidTest.yLaakariLkmTextField.setText("2");
        	assertEquals("2", yLaakariLkmTextField.getText());
        	
        } else {
            fail("yLaakariLkmTextField");
        }
        if (IsInputValidTest.eLaakariLkmTextField != null) {
        	IsInputValidTest.eLaakariLkmTextField.setText("3");
        	assertEquals("3", eLaakariLkmTextField.getText());
        } else {
            fail("eLaakariLkmTextField is not initialized");
        }
        assertEquals("10", aikaTextField.getText());
        assertEquals("2", yLaakariLkmTextField.getText());
        assertEquals("3", eLaakariLkmTextField.getText());
    }
}