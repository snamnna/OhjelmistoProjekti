package view;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import ch.makery.address.util.DateUtil;
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
import javafx.scene.control.Alert.AlertType;
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
	    @FXML
	    private Label simuloinninKokonaisaikaLabel;
	    @FXML
	    private Label asiakkaitaPalveltuLabel;
	    @FXML
	    private Label yLaakareitaLabel; // = yLaakarienLkm ?
	    @FXML
	    private Label eLaakareitaLabel; // = eLaakarienLkm ?
	    @FXML
	    private Label labraKayntejaLabel;
	    @FXML
	    private Label kayttoAsteLabel;
	    @FXML
	    private Label suoritustehoLabel;
	    
	    private Button kaynnistaButton;
		private Button hidastaButton;
		private Button nopeutaButton;
		
		private boolean hidastaClicked = false;
		private boolean nopeutaClicked = false;
	    
	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriMtoV kontrolleri;
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
			
		      // näin vai metodien kautta?
		    //hidastaButton.setOnAction(e -> kontrolleri.hidasta());
		    //nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());
		      
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
		// tsekkaa, jos input on ok.
		if (isInputValid()) {
			kontrolleri.kaynnistaSimulointi();
		}
	}
	@FXML
	private void handleNopeuta() {
		//tää pitää viel tehdä, esimerkin mukaan?
		nopeutaClicked = true;
		if (nopeutaClicked) {
			kontrolleri.nopeuta();
		}
	}
	@FXML 
	private void handleHidasta() {
		//esimerkin mukaan?
		hidastaClicked = true;
		if (hidastaClicked) {
			kontrolleri.hidasta();
		}
	}
	
	/**
     * File -> New : luodaan uusi simulaatio
     */
    @FXML
    private void handleNew() {
        // settii
    }
    
    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Päivystyssimulaattori");
    	alert.setHeaderText("About");
    	alert.setContentText("Authors: Sanna Lohkovuori, Matias Niemelä, Edvard Nivala, Tuisku Närhi");

    	alert.showAndWait();
    }
    
    // Save, Save As, Open... etc. metodit jos otetaan käyttöön?
    
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
		return Double.parseDouble(simulointiAikaTxtField.getText());
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
	
	/**
     * Tsekataan textfieldien input.
     * 
     * @return true jos ok
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (simulointiAikaTxtField.getText() == null || simulointiAikaTxtField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n"; 
        }
        else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(simulointiAikaTxtField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n"; 
            }
        }
        if (yLaakarienLkmTxtField.getText() == null || yLaakarienLkmTxtField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n"; 
        } else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(yLaakarienLkmTxtField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n"; 
            }
        }
        if (eLaakarienLkmTxtField.getText() == null || eLaakarienLkmTxtField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n"; 
        } else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(eLaakarienLkmTxtField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n"; 
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
    // JavaFX-sovelluksen (käyttöliittymän) käynnistäminen
    public static void main(String[] args) {
		launch(args);
	}
}
