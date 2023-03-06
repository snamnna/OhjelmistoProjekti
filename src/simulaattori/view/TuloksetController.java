package simulaattori.view;

import entity.Tulos;
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
	
	@FXML
	public void setTulokset(Tulos tulokset) {

		eLaakarit.setText(Integer.toString(mainApp.getElaakarienLkm()));
		ylaakarit.setText(Integer.toString(mainApp.getYlaakarienLkm()));
		palveltu.setText(Integer.toString(tulokset.getCompletedCount()));
		kokonaisaika.setText(Double.toString(tulokset.getKokonaisaika()));
		labroja.setText(Integer.toString(tulokset.getLabraArrivalit()));
		utilization.setText(Double.toString(tulokset.getUtilization()));
		serviceTime.setText(Double.toString(tulokset.getServiceTime()));
		
	
	}
	
	

}
