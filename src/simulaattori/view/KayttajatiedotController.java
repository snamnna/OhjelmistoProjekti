package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import simulaattori.MainApp;


public class KayttajatiedotController {

    @FXML
	public TextField eLaakariLkmTextField;

    @FXML
	public TextField simulointiAikaTextField;

    @FXML
    private TextField simulointiViiveTextField;

    @FXML
	public TextField yLaakariLkmTextField;

    @FXML
    private CheckBox enableAdvancedCheckBox;

    @FXML
    private TextField labraLkmTextField;

    @FXML
    private TextField sairaanhoitajaLkmTextField;
    
    @FXML
    private Button kaynnistaButton;
    
    @FXML
    private ButtonBar simControls;

    private MainApp mainApp;

    @FXML
    public void handleKaynnista() {
        if(isInputValid()) {
        	mainApp.startButtonClicked();
        }
    }

    @FXML
    public void handleHidasta() {
        mainApp.hidastaSimulaattoria();
    }

    @FXML
    public void handleNopeuta() {
        mainApp.nopeutaSimulaattoria();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Double getSimulointiAika() {
        return Double.parseDouble(simulointiAikaTextField.getText());
    }

    public Integer getYLaakariLkm() {
        return Integer.parseInt(yLaakariLkmTextField.getText());
    }

    public Integer getELaakariLkm() {
        return Integer.parseInt(eLaakariLkmTextField.getText());
    }

    public long getSimulointiViive() {
        return Long.parseLong(simulointiViiveTextField.getText());
    }

    public Integer getSairaanhoitajaLkm() {
        return Integer.parseInt(sairaanhoitajaLkmTextField.getText());
    }

    public Integer getLabraLkm() {
        return Integer.parseInt(sairaanhoitajaLkmTextField.getText());
    }

    public boolean isInputValid() {
        String errorMessage = "";

        if (simulointiAikaTextField.getText() == null || simulointiAikaTextField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n";
        }
        else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(simulointiAikaTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (yLaakariLkmTextField.getText() == null || yLaakariLkmTextField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n";
        } else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(yLaakariLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (eLaakariLkmTextField.getText() == null || eLaakariLkmTextField.getText().length() == 0) {
            errorMessage += "Syötä numero!\n";
        } else {
            // koita parse numero intiksi.
            try {
                Integer.parseInt(eLaakariLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public long getViive() {
        return Long.parseLong(simulointiViiveTextField.getText());
    }

	public void disableTextFieldsAndStartButton(boolean disabled) {
		kaynnistaButton.setDisable(disabled);
		yLaakariLkmTextField.setDisable(disabled);
		eLaakariLkmTextField.setDisable(disabled);
		simulointiViiveTextField.setDisable(disabled);
		simulointiAikaTextField.setDisable(disabled);
	}
}
