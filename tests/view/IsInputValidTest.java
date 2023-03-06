package view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import simulaattori.view.KayttajatiedotController;

class IsInputValidTest {
	
	//HUOM, KayttajatiedotControllerissa määritelty tarkasteltavat arvot nulleiksi
	
	static KayttajatiedotController controller;
	private TextField aikaTextField = null;
	private TextField YLaakariLkmTextField = null;
	private TextField ELaakariLkmTextField = null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception{
	   controller = new KayttajatiedotController();
	}
	
	@BeforeEach
	void setUp() throws Exception {
	    aikaTextField = controller.simulointiAikaTextField;
	    YLaakariLkmTextField = controller.yLaakariLkmTextField;
	    ELaakariLkmTextField = controller.eLaakariLkmTextField;
	}

	@Test
	void testIsInputValid() {
		//Kaikki nämä nulleja, setText ei suostu asettamaan teksitä muuttujaan
		YLaakariLkmTextField.setText("2");
	    ELaakariLkmTextField.setText("3");
	    aikaTextField.setText("10");
	    assertTrue(controller.isInputValid(), "Numerot hyväksyttäviä");
	}
}