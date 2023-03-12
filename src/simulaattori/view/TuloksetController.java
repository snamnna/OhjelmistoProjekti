package simulaattori.view;

import entity.Tulos;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import simulaattori.MainApp;

/**
 * Tulokste näkymän kontrolleri.
 */
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
	private Label labrassaKaynteja;
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

	/**
	 * Sets main app.
	 *
	 * @param mainApp the main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		tulos.addListener((observable, oldValue, newValue) -> {
			setELaakarit(Integer.toString(newValue.getErikoislääkärit()));
			setYlaakarit(Integer.toString(newValue.getYleislääkärit()));
			setLabrassaKaynteja(Integer.toString(newValue.getLabraArrivalit()));
			setPalveltu(Integer.toString(newValue.getCompletedCount()));
			setKokonaisaika(String.format("%.2f", newValue.getSimTime()));
			setUtilization(String.format("%.2f", newValue.getUtilization()));
			setServiceTime(String.format("%.2f", newValue.getServiceTime()));
			setAverageResponseTime(String.format("%.2f", newValue.getAverageResponseTime()));
			setAverageWaitingTime(String.format("%.2f", newValue.getAverageWaitingTime()));
		});
	}

	/**
	 * Tallenna tulos.
	 */
	@FXML
	public void tallennaTulos() {
		mainApp.tallennaTulos(tulos.get());
	}

	/**
	 * Poista tulos.
	 */
	@FXML
	public void poistaTulos() {
		mainApp.poistaTulos(tulos.get());
	}

	/**
	 * Sets e laakarit.
	 *
	 * @param eLaakarit the e laakarit
	 */
	public void setELaakarit(String eLaakarit) {
		this.eLaakarit.setText(eLaakarit);
	}

	/**
	 * Sets ylaakarit.
	 *
	 * @param ylaakarit the ylaakarit
	 */
	public void setYlaakarit(String ylaakarit) {
		this.ylaakarit.setText(ylaakarit);
	}

	/**
	 * Sets palveltu.
	 *
	 * @param palveltu the palveltu
	 */
	public void setPalveltu(String palveltu) {
		this.palveltu.setText(palveltu);
	}

	/**
	 * Sets kokonaisaika.
	 *
	 * @param kokonaisaika the kokonaisaika
	 */
	public void setKokonaisaika(String kokonaisaika) {
		this.kokonaisaika.setText(kokonaisaika);
	}

	/**
	 * Sets labrassa kaynteja.
	 *
	 * @param labroja the labroja
	 */
	public void setLabrassaKaynteja(String labroja) {
		this.labrassaKaynteja.setText(labroja);
	}

	/**
	 * Sets utilization.
	 *
	 * @param utilization the utilization
	 */
	public void setUtilization(String utilization) {
		this.utilization.setText(utilization);
	}

	/**
	 * Sets service time.
	 *
	 * @param serviceTime the service time
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime.setText(serviceTime);
	}

	/**
	 * Sets average response time.
	 *
	 * @param averageResponseTime the average response time
	 */
	public void setAverageResponseTime(String averageResponseTime) {
		this.averageResponseTime.setText(averageResponseTime);
	}

	/**
	 * Sets average waiting time.
	 *
	 * @param averageWaitingTime the average waiting time
	 */
	public void setAverageWaitingTime(String averageWaitingTime) {
		this.averageWaitingTime.setText(averageWaitingTime);
	}

	/**
	 * Sets tulos.
	 *
	 * @param tulos the tulos
	 */
	public void setTulos(Tulos tulos) {
		this.tulos.set(tulos);
	}
}
