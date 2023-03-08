package simulaattori.view;

import entity.Tulos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import simulaattori.MainApp;
import simulaattori.simu.model.TulosDAO;

public class TuloksetController {

	@FXML
	private Label eLaakarit;
	@FXML
	private Label ylaakarit;
	@FXML
	private Label palveltu;
	@FXML
	private Label kokonaisaika;
	@FXML
	private Label labroja;
	@FXML
	private Label utilization;
	@FXML
	private Label serviceTime;
	@FXML
	private Label averageResponseTime;
	@FXML
	private Label averageWaitingTime;
	@FXML
	private Button tallennaButton;
	@FXML 
	private Button poistaButton;

	private MainApp mainApp;
	
	private ObjectProperty<Tulos> tulos = new SimpleObjectProperty<>();

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tulos.addListener((observable, oldValue, newValue) -> {
			setELaakarit(Integer.toString(newValue.getErikoislääkärit()));
			setYlaakarit(Integer.toString(newValue.getYleislääkärit()));
			setLabroja(Integer.toString(newValue.getLabrat()));
			setPalveltu(Integer.toString(newValue.getCompletedCount()));
			setKokonaisaika(String.format("%.2f", newValue.getSimTime()));
		    setUtilization(String.format("%.2f", newValue.getUtilization()));
		    setServiceTime(String.format("%.2f", newValue.getServiceTime()));
		    setAverageResponseTime(String.format("%.2f", newValue.getAverageResponseTime()));
		    setAverageWaitingTime(String.format("%.2f", newValue.getAverageWaitingTime()));
		});
	}
	
	@FXML
	public void tallennaTulos() {
		System.out.println("Tallennetaan tulos tietokantaan");
		mainApp.tallennaTulos(tulos.get());
	}
	
	@FXML
	public void poistaTulos() {
		System.out.println("Poistetaan tulos");
	}

	public void setELaakarit(String eLaakarit) {
		this.eLaakarit.setText(eLaakarit);
	}

	public void setYlaakarit(String ylaakarit) {
		this.ylaakarit.setText(ylaakarit);
	}

	public void setPalveltu(String palveltu) {
		this.palveltu.setText(palveltu);
	}

	public void setKokonaisaika(String kokonaisaika) {
		this.kokonaisaika.setText(kokonaisaika);
	}

	public void setLabroja(String labroja) {
		this.labroja.setText(labroja);
	}

	public void setUtilization(String utilization) {
		this.utilization.setText(utilization);
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime.setText(serviceTime);
	}

	public void setAverageResponseTime(String averageResponseTime) {
		this.averageResponseTime.setText(averageResponseTime);
	}

	public void setAverageWaitingTime(String averageWaitingTime) {
		this.averageWaitingTime.setText(averageWaitingTime);
	}

	public void setTulos(Tulos tulos) {
		this.tulos.set(tulos);
//		ObservableValue<Tulos> observableTulos = new SimpleObjectProperty<>(tulos);
//		this.tulos = observableTulos;
	}

}
