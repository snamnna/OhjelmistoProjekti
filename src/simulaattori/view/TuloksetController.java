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
	public void setTulokset() {
		Tulos tulokset = new Tulos();
		System.out.println("Tulokset asetettais nyt jos toimis, alla kokeillaan toimiiks tulokset oikein konsolis");
		System.out.println("e-lääkärit" + mainApp.getElaakarienLkm());
		System.out.println("y-läälärit" + mainApp.getYlaakarienLkm());
		System.out.println("palveltuja" + tulokset.getCompletedCount());
		System.out.println("kokonaisaika " + tulokset.getKokonaisaika());
		System.out.println("labroja" + tulokset.getLabraArrivalit());
		System.out.println("utilization " + tulokset.getUtilization());
		System.out.println("serviceTime" + tulokset.getServiceTime());
		System.out.println("waiting time " + tulokset.getAverageWaitingTime());
//		eLaakarit.setText(Integer.toString(mainApp.getElaakarienLkm()));
//		ylaakarit.setText(Integer.toString(mainApp.getYlaakarienLkm()));
//		palveltu.setText(Integer.toString(tulokset.getCompletedCount()));
//		kokonaisaika.setText(Double.toString(tulokset.getKokonaisaika()));
//		labroja.setText(Integer.toString(tulokset.getLabraArrivalit()));
//		utilization.setText(Double.toString(tulokset.getUtilization()));
//		serviceTime.setText(Double.toString(tulokset.getServiceTime()));
		
	
	}
	
	

}
