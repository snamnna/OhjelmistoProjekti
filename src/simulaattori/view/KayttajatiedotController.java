package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import simulaattori.MainApp;
import simulaattori.model.TapahtumanTyyppi;

import java.util.HashMap;
import java.util.Map;


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

    private MainApp mainApp;

    @FXML
    public void handleKaynnista() {
        if (isInputValid()) {
            mainApp.startSimulaattori();
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

    /**
     * Tarkistaa, että simuloinnin syötteet ovat kelvollisia.
     *
     * @return palauttaa true, jos syötteet ovat kelvollisia, muutoin false.
     */
    public boolean isInputValid() {
        String errorMessage = "";

        if (simulointiAikaTextField.getText() == null || simulointiAikaTextField.getText().length() == 0) {
            errorMessage += "Simulointiaika: Syötä numero!\n";
        } else {
            try {
                Integer.parseInt(simulointiAikaTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Simulointiaika: Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (sairaanhoitajaLkmTextField.getText() == null || sairaanhoitajaLkmTextField.getText().length() == 0) {
            errorMessage += "Sairaanhoitaja: Syötä numero!\n";
        } else {
            try {
                Integer.parseInt(sairaanhoitajaLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Sairaanhoitajat: Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (yLaakariLkmTextField.getText() == null || yLaakariLkmTextField.getText().length() == 0) {
            errorMessage += "Yleislääkärit: Syötä numero!\n";
        } else {
            try {
                Integer.parseInt(yLaakariLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Yleislääkärit: Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (eLaakariLkmTextField.getText() == null || eLaakariLkmTextField.getText().length() == 0) {
            errorMessage += "Erikoislääkärit: Syötä numero!\n";
        } else {
            try {
                Integer.parseInt(eLaakariLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Erikoislääkärit: Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (labraLkmTextField.getText() == null || labraLkmTextField.getText().length() == 0) {
            errorMessage += "Laboratoriot: Syötä numero!\n";
        } else {
            try {
                Integer.parseInt(labraLkmTextField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Laboratoriot: Vääränlainen syöttö. Syötä kokonaisluku!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
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
        sairaanhoitajaLkmTextField.setDisable(disabled);
        labraLkmTextField.setDisable(disabled);
        simulointiViiveTextField.setDisable(disabled);
        simulointiAikaTextField.setDisable(disabled);
    }

    public Map<TapahtumanTyyppi, Integer> getPalvelupisteMaarat() {
        Map<TapahtumanTyyppi, Integer> palvelupisteet = new HashMap<>();
        palvelupisteet.put(TapahtumanTyyppi.ARR, Integer.parseInt(sairaanhoitajaLkmTextField.getText()));
        palvelupisteet.put(TapahtumanTyyppi.YLARR, Integer.parseInt(yLaakariLkmTextField.getText()));
        palvelupisteet.put(TapahtumanTyyppi.ELARR, Integer.parseInt(eLaakariLkmTextField.getText()));
        palvelupisteet.put(TapahtumanTyyppi.LABRA_ARRIVAL, Integer.parseInt(labraLkmTextField.getText()));
        return palvelupisteet;
    }
}
