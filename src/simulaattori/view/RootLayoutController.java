package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import simulaattori.MainApp;

public class RootLayoutController {

	private MainApp mainApp;

	public void setMain(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	public void handleExit() {
		System.exit(0);
	}

	@FXML
	public void handleAbout() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Päivystyssimulaattori");
		alert.setHeaderText("About");
		alert.setContentText("Authors: Sanna Lohkovuori, Matias Niemelä, Edvard Nivala, Tuisku Närhi");
		alert.showAndWait();
	}
	
	
	// avaa Tietovarastoikkunan
	@FXML
	public void handleShowTietovarasto() {
		mainApp.openTietovarasto();
	}
	
}
