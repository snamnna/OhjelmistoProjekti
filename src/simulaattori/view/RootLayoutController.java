package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import simulaattori.MainApp;

/**
 * Root layout controller.
 */
public class RootLayoutController {

	private MainApp mainApp;

	/**
	 * Sets main.
	 *
	 * @param mainApp the main app
	 */
	public void setMain(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Handle exit.
	 */
	@FXML
	public void handleExit() {
		System.exit(0);
	}

	/**
	 * Handle about.
	 */
	@FXML
	public void handleAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Päivystyssimulaattori");
		alert.setHeaderText("About");
		alert.setContentText("Authors: Sanna Lohkovuori, Matias Niemelä,\n Edvard Nivala, Tuisku Närhi");
		alert.showAndWait();
	}


	/**
	 * Handle show tietovarasto.
	 */
// avaa Tietovarastoikkunan
	@FXML
	public void handleShowTietovarasto() {
		mainApp.openTietovarasto();
	}

}
