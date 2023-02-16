package view;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import ch.makery.adress.MainApp;
import ch.makery.adress.view.PersonOverviewController;
import ch.makery.adress.view.RootLayoutController;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;



public class TESTISimulaationGUI extends Application implements ISimulaattorinUI{

	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleriVtoM kontrolleri;

	private Stage primaryStage;
    private BorderPane rootLayout;
	

	private IVisualisointi naytto;


	@Override
	public void init(){
		
		Trace.setTraceLevel(Level.INFO);
		
		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        
        initRootLayout();
        
        showSimulation();
	}

	public void initRootLayout(){
		try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(TESTISimulaationGUI.class
                    .getResource("resources/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setTesti(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void showSimulation(){
		 try {
	            // Load person overview.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(TESTISimulaationGUI.class.getResource("resources/kayttoliittyma.fxml"));
	            AnchorPane personOverview = (AnchorPane) loader.load();
	            
	            // Set person overview into the center of root layout.
	            rootLayout.setCenter(personOverview);

	            // Give the controller access to the main app.
	            ShowSimulationController controller = loader.getController();
	            controller.setTesti(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public IVisualisointi getVisualisointi() {
		 return naytto;
	}
	
	
	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public int getYlaakarienLkm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElaakarienLkm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLabraJakauma() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTulokset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSimulointiAika() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getViive() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}

