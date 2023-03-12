package simulaattori;

import entity.Tulos;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import simulaattori.controller.IKontrolleriVtoM;
import simulaattori.controller.Kontrolleri;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.TulosDAO;
import simulaattori.model.util.IPalvelupiste;
import simulaattori.view.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainApp extends Application implements ISimulaattorinUI { // Simulaattorin käynnistyspääohjelma

    private TuloksetController tulosController;
    private TietovarastoController tietovarastoController;
    private RootLayoutController rootLayoutController;
    private ObservableList<Tulos> listTulos = FXCollections.observableArrayList();
    private TulosDAO tulosDAO;
    private Stage primaryStage;
    private BorderPane rootLayout;
    private KayttajatiedotController kayttajatiedotController;
    private SimulaattoriController simuController;
    private IKontrolleriVtoM kontrolleri;
    private AnchorPane tietovarasto;
    private BooleanProperty simuloidaan = new SimpleBooleanProperty(false);

    public MainApp() {
        tulosDAO = TulosDAO.getInstance();
        Tulos[] tulokset = tulosDAO.getTulokset();
        for (int i = 0; i < tulokset.length; i++) {
            listTulos.add(tulokset[i]);
        }
    }

    public static void main(String[] args) {
        launch(MainApp.class);
    }

    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Päivystyssimulaattori");
        kontrolleri = new Kontrolleri(this);

        initRootLayout();
        showKayttajatiedot();
        showSimulaattori();
        showTulokset();
        showTietovarasto();

        primaryStage.sizeToScene();
        primaryStage.show();

        simuloidaan.addListener((observable, oldValue, newValue) -> {
            kayttajatiedotController.disableTextFieldsAndStartButton(newValue);
            if (!oldValue) {
                tietovarastoController.disableTuloksetTable(true);
            }

            if (oldValue && !newValue) {
                tietovarastoController.disableTuloksetTable(false);
            }
        });
    }

    private void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("resources/RootLayout.fxml"));
        rootLayout = loader.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);

        // Give the controller access to the main app.
        rootLayoutController = loader.getController();
        rootLayoutController.setMain(this);
    }

    public void showKayttajatiedot() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("resources/Kayttajatiedot.fxml"));
        AnchorPane kayttajatiedot = loader.load();
        rootLayout.setLeft(kayttajatiedot);

        // Give the controller access to the main app.
        kayttajatiedotController = loader.getController();
        kayttajatiedotController.setMainApp(this);
    }

    public void showSimulaattori() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("resources/Simulaattori.fxml"));
        AnchorPane simulaattori = loader.load();
        rootLayout.setCenter(simulaattori);

        // Give the controller access to the main app.
        simuController = loader.getController();
        simuController.setMainApp(this);
    }

    public void showTulokset() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("resources/Tulokset.fxml"));
        AnchorPane tulokset = loader.load();
        rootLayout.setRight(tulokset);

        // Give the controller access to the main app
        tulosController = loader.getController();
        tulosController.setMainApp(this);
    }

    public void showTietovarasto() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("resources/Tietovarasto.fxml"));
        tietovarasto = loader.load();
        tietovarasto.setVisible(false);
        tietovarasto.setManaged(false);
        tietovarastoController = loader.getController();
        tietovarastoController.setMainApp(this);
        rootLayout.setBottom(tietovarasto);
    }

    public void setSimuloidaan(boolean value) {
        simuloidaan.set(value);
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

    public ObservableList<Tulos> getTulokset() {
        return listTulos;
    }

    public void setTulos(Tulos tulos) {
        tulosController.setTulos(tulos);
    }

    public void closeTietovarasto() {
        tietovarasto.setVisible(false);
        tietovarasto.setManaged(false);
        primaryStage.sizeToScene();
    }

    public void openTietovarasto() {
        tietovarasto.setVisible(true);
        tietovarasto.setManaged(true);
        primaryStage.sizeToScene();
    }

    public void tallennaTulos(Tulos tulos) {
        tulosDAO.createTulos(tulos);
        tietovarastoController.lisaaTulos(tulos);
    }

    public void poistaTulos(Tulos tulos) {
        tulosDAO.deleteTulos(tulos.getId());
        tietovarastoController.removeTulos(tulos);
    }

    public void viePalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet) {
        simuController.setPalvelupisteet(palvelupisteet);
    }

    @Override
    public Map<TapahtumanTyyppi, Integer> getPalvelupisteMaarat() {
        return kayttajatiedotController.getPalvelupisteMaarat();
    }

    @Override
    public void visualisoi() {
        simuController.visualisoi();
    }

    @Override
    public long getViive() {
        return kayttajatiedotController.getViive();
    }

    @Override
    public double getSimulointiAika() {
        return kayttajatiedotController.getSimulointiAika();
    }
}
