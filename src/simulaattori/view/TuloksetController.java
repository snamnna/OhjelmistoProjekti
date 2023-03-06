package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import simulaattori.MainApp;

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

	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void seteLaakarit(String eLaakarit) {
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

}
