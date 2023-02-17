package view;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import simu.model.OmaMoottori;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;



public class TESTISimulaationGUI extends Application implements ISimulaattorinUI{

	 @FXML
	    private TextField simulointiAikaTxtField;
	    @FXML
	    private TextField yLaakarienLkmTxtField;
	    @FXML
	    private TextField eLaakarienLkmTxtField;
	    @FXML
	    private MenuButton labraMenu;
	    @FXML
	    private MenuItem labrachoice1;
	    @FXML
	    private MenuItem labrachoice2;
	    @FXML
	    private TextField labraJakaumaVaihteluTxtField;
	    @FXML
	    private Pane visualisointiPane;
	    @FXML
	    private Pane tuloksetPane;
	    
	    
	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;
	private OmaMoottori moottori = new OmaMoottori();
	private IVisualisointi naytto;

	@Override
	public void init() {
		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			init();
			
		      Parent root = FXMLLoader.load(getClass().getResource("gui.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setTitle("Päivystyssimulaattori");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//Käyttöliittymän kontrolloimiseen käytettävät metodit
	//Nää FXML metodit vois melkeen heittää omaan controller luokkaan jolle annetaan oikeudet ajaa tätä mainappii.
	
	@FXML
	private void handleAloitaSimulointi() {
		moottori.aja(); //Tällä aloitetaan simulointi??
	}
	@FXML
	private void handleNopeuta() {
		//tää pitää viel tehdä, esimerkin mukaan?
	}
	@FXML 
	private void handleHidasta() {
		//esimerkin mukaan?
	}
	
	//rajapinnalta saadut metodit
	@Override
	public IVisualisointi getVisualisointi() {
		//ei mitään hajuu tästä :DDD pitää selvittää
		 return naytto;
	}
	
	@Override
	public int getYlaakarienLkm() {
		//tänne textfieldist se määrä mikä saadaan tietty käyttäjält! 
		return 0;
	}

	@Override
	public int getElaakarienLkm() {
		//tänne sama homma, textfieldist
		return 0;
	}

	@Override
	public double getLabraJakauma() {
		//tä saa myös textfieldist mut pitää varmaa muokkaa jotenki ku mahiksena myös valita mikä jakauma ja vaihteluväli
		return 0;
	}

	@Override
	public void setTulokset() {
		//asetetaan tulokset tuloksetPaneen idk miten vielä koska ei oo tuloksii tehty 
	}

	@Override
	public double getSimulointiAika() {
		//Saadaan textfieldist
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		//tehään varmaa joku oma label tms jos näkyy toi loppuaika kokoajan en oo viel ehtiny
	}

	@Override
	public long getViive() {
		//tätä käytetään siihen handleen aika varmaan
		return 0;
	}
	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}

	
}

