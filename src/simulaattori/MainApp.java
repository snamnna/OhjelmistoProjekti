package simulaattori;

import javafx.scene.layout.AnchorPane;
import simulaattori.controller.IKontrolleriMtoV;
import simulaattori.controller.Kontrolleri;
import simulaattori.simu.model.TulosDAO;
import simulaattori.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

import entity.Tulos;

public class MainApp extends Application implements ISimulaattorinUI { // Simulaattorin käynnistyspääohjelma
    public static void main(String[] args) {
    	
        launch(MainApp.class);
        
    }
   
    private Stage primaryStage;
    private BorderPane rootLayout;
    private KayttajatiedotController kayttajatiedotController;
    private SimulaattoriController simuController;
    private static TuloksetController tulosController;
    private static TietovarastoController tietovarastoController;
    private IKontrolleriMtoV kontrolleri;

    public void start(Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	this.primaryStage.setTitle("Päivystyssimulaattori");
        kontrolleri = new Kontrolleri(this);
        
        

    	initRootLayout();
        showKayttajatiedot();
        showSimulaattori();
        showTulokset();
        showTietovarasto();
        this.primaryStage.sizeToScene();
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("resources/RootLayout.fxml"));

            rootLayout = (BorderPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the application.controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMain(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showKayttajatiedot() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("resources/Kayttajatiedot.fxml"));
            AnchorPane kayttajatiedot = (AnchorPane) loader.load();
            rootLayout.setLeft(kayttajatiedot);

            // Give the controller access to the main app.
            kayttajatiedotController = loader.getController();
            kayttajatiedotController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSimulaattori() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("resources/Simulaattori.fxml"));
            AnchorPane simulaattori = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(simulaattori);

            // Give the controller access to the main app.
            simuController = loader.getController();
            simuController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTulokset() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("resources/Tulokset.fxml"));
            AnchorPane tulokset = (AnchorPane) loader.load();
            
            rootLayout.setRight(tulokset);
            
            //Give the controller access to the main app
            tulosController = loader.getController();
            tulosController.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showTietovarasto() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("resources/Tietovarasto.fxml"));
        	AnchorPane tietovarasto = (AnchorPane) loader.load();
        	
        	rootLayout.setBottom(tietovarasto);
        	
        	tietovarastoController = loader.getController();
        	tietovarastoController.setMainApp(this);
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void startSimulaattori() {
        simuController.tyhjennaNaytto();
        kontrolleri.kaynnistaSimulointi();
    }

    public void hidastaSimulaattoria() {
        kontrolleri.hidasta();
    }

    public void nopeutaSimulaattoria() {
        kontrolleri.nopeuta();
    }

    @Override
    public double getSimulointiAika() {
        return kayttajatiedotController.getSimulointiAika();
    }

    @Override
    public int getYlaakarienLkm() {
        return kayttajatiedotController.getYLaakariLkm();
    }

    @Override
    public int getElaakarienLkm() {
        return kayttajatiedotController.getELaakariLkm();
    }

    @Override
    public int getLabraLkm() {
        return kayttajatiedotController.getLabraLkm();
    }

    @Override
    public int getSairaanhoitajaLkm() {
        return kayttajatiedotController.getSairaanhoitajaLkm();
    }

    @Override
    public void setLoppuaika(double aika) {
    	
    }
    @Override
    public void setTulokset () {
    	tulosController.setTulokset();
    }

    @Override
    public SimulaattoriController getVisualisointi() {
        return simuController;
    }

    @Override
    public long getViive() {
        return kayttajatiedotController.getViive();
    }


}
