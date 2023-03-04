package view;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Application;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import simulaattori.MainApp;
import simulaattori.view.KayttajatiedotController;

public class KayttajatiedotControllerTest{
	
	//Tässä luokassa controlleri vielä tyhjä
	
	private static KayttajatiedotController controller;
	
	//Yritetään parsea metodin palauttama arvo intiksi, jos onnistuu, testi menee läpi sillä on annettu numero
	//jos testi ei mene läpi, tulee numberformatexception eli on annettu kirjan
	@Test
	void testgetELaakariLkm() {
	    System.out.println("Erikoislääkärien lukumäärä");
	    try {
	        Integer.parseInt(controller.getELaakariLkm().toString());
	        assertTrue(true, "Lääkärien lukumäärä on numero");
	    } catch (NumberFormatException e) {
	        fail("Lääkärien lukumäärä ei ole numero");
	    }
	}
	
	@Test
	void testgetYLaakariLkm() {
	    System.out.println("Yleislääkärien lukumäärä");
	    try {
	        Integer.parseInt(controller.getYLaakariLkm().toString());
	        assertTrue(true, "Lääkärien lukumäärä on numero");
	    } catch (NumberFormatException e) {
	        fail("Lääkärien lukumäärä ei ole numero");
	    }
	}
}
