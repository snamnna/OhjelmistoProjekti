package simulaattori;

import java.io.IOException;

import entity.Tulos;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import simulaattori.controller.IKontrolleriMtoV;
import simulaattori.controller.Kontrolleri;
import simulaattori.simu.model.TulosDAO;
import simulaattori.view.ISimulaattorinUI;
import simulaattori.view.KayttajatiedotController;
import simulaattori.view.RootLayoutController;
import simulaattori.view.SimulaattoriController;
import simulaattori.view.TietovarastoController;
import simulaattori.view.TuloksetController;

public class MainApp extends Application implements ISimulaattorinUI { // Simulaattorin käynnistyspääohjelma

	private ObservableList<Tulos> listTulos = FXCollections.observableArrayList();
	private Stage primaryStage;
	private BorderPane rootLayout;
	private KayttajatiedotController kayttajatiedotController;
	private SimulaattoriController simuController;
	private static TuloksetController tulosController;
	private static TietovarastoController tietovarastoController;
	private IKontrolleriMtoV kontrolleri;
	private AnchorPane tietovarasto;
	private BooleanProperty simuloidaan = new SimpleBooleanProperty(false);

	public MainApp() {
		Tulos[] tulokset = TulosDAO.getInstance().getTulokset();
		for (int i = 0; i < tulokset.length; i++) {
			listTulos.add(tulokset[i]);
		}
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
			if(!oldValue) startSimulaattori();
		});
	}

	private void initRootLayout() throws IOException {
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
	}

	public void showKayttajatiedot() throws IOException {
		// Load person overview.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("resources/Kayttajatiedot.fxml"));
		AnchorPane kayttajatiedot = (AnchorPane) loader.load();
		rootLayout.setLeft(kayttajatiedot);

		// Give the controller access to the main app.
		kayttajatiedotController = loader.getController();
		kayttajatiedotController.setMainApp(this);
	}

	public void showSimulaattori() throws IOException {
		// Load person overview.
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("resources/Simulaattori.fxml"));
		AnchorPane simulaattori = (AnchorPane) loader.load();

		// Set person overview into the center of root layout.
		rootLayout.setCenter(simulaattori);

		// Give the controller access to the main app.
		simuController = loader.getController();
		simuController.setMainApp(this);
	}

	public void showTulokset() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("resources/Tulokset.fxml"));
		AnchorPane tulokset = (AnchorPane) loader.load();

		rootLayout.setRight(tulokset);

		// Give the controller access to the main app
		tulosController = loader.getController();
		tulosController.setMainApp(this);
	}

	public void showTietovarasto() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("resources/Tietovarasto.fxml"));
		tietovarasto = (AnchorPane) loader.load();
		tietovarasto.setVisible(false);
		tietovarasto.setManaged(false);
		tietovarastoController = loader.getController();
		tietovarastoController.setMainApp(this);
		rootLayout.setBottom(tietovarasto);
	}

	public BooleanProperty simuloidaanProperty() {
		return simuloidaan;
	}

	public void setSimuloidaan(boolean value) {
		simuloidaan.set(value);
	}

	public void startButtonClicked() {
		setSimuloidaan(true);
	}

	public void startSimulaattori() {
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
	
	public void getTulos() {
		tulosController.setTulos(kontrolleri.getTulos());
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
	public SimulaattoriController getVisualisointi() {
		return simuController;
	}

	@Override
	public long getViive() {
		return kayttajatiedotController.getViive();
	}

	@Override
	public void setKokonaisaika(String aika) {
		tulosController.setKokonaisaika(aika);
	}

	@Override
	public void setPalveltu(String lkm) {
		tulosController.setPalveltu(lkm);
	}

	@Override
	public void setELaakarienLkm(String lkm) {
		tulosController.setELaakarit(lkm);
	}

	@Override
	public void setYLaakarienLkm(String lkm) {
		tulosController.setYlaakarit(lkm);
	}

	@Override
	public void setLabrakaynteja(String lkm) {
		tulosController.setLabroja(lkm);
	}

	@Override
	public void setKayttoaste(String kayttoaste) {
		tulosController.setUtilization(kayttoaste);
	}

	@Override
	public void setKeskimaarainenPalveluaika(String aika) {
		tulosController.setAverageResponseTime(aika);
	}

	@Override
	public void setLapimenoAika(String aika) {
		tulosController.setServiceTime(aika);
	}

	@Override
	public void setKeskimaarainenJonotusaika(String aika) {
		tulosController.setAverageWaitingTime(aika);
	}

	public void closeTietovarasto() {
		tietovarasto.setVisible(false);
		tietovarasto.setManaged(false);
		kayttajatiedotController.enableSimControls();
		primaryStage.sizeToScene();
	}

	public void openTietovarasto() {
		tietovarasto.setVisible(true);
		tietovarasto.setManaged(true);
		kayttajatiedotController.disableSimControls();
		primaryStage.sizeToScene();
	}

	public static void main(String[] args) {
		launch(MainApp.class);
	}
}
