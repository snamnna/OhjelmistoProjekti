package simulaattori.view;

import entity.Tulos;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import simulaattori.MainApp;

public class TietovarastoController {

	@FXML
	private TableView<Tulos> table_tulokset;
	@FXML
	private TableColumn<Tulos, Integer> col_id;
	@FXML
	private TableColumn<Tulos, Double> col_aika;
	@FXML
	private TableColumn<Tulos, Integer> col_eLaakarit;
	@FXML
	private TableColumn<Tulos, Integer> col_yleisLaakarit;
	@FXML
	private TableColumn<Tulos, Integer> col_labrakaynnit;
	@FXML
	private TableColumn<Tulos, Integer> col_saapuneet;
	@FXML
	private TableColumn<Tulos, Integer> col_palveltu;
	@FXML
	private TableColumn<Tulos, Double> col_busyTime;
	@FXML
	private TableColumn<Tulos, Double> col_throughput;
	@FXML
	private TableColumn<Tulos, Double> col_utilization;
	@FXML
	private TableColumn<Tulos, Double> col_serviceTime;

	private MainApp mainApp;
	private Boolean open = true;

	@FXML
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		table_tulokset.setItems(mainApp.getTulokset());
	}

	@FXML
	public void initialize() {
		col_id.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
			Integer id = tulos.getId();
			return new ReadOnlyObjectWrapper<Integer>(id);
		});

		col_aika.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
			Double aika = tulos.getKokonaisaikaProperty();
			return new ReadOnlyObjectWrapper<Double>(aika);
		});

		col_eLaakarit.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
//        	Integer lkm = tulos.getELaakarienLkm();
//        	return new ReadOnlyObjectWrapper<Integer>(lkm);
			return null;
		});

		col_yleisLaakarit.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
//        	Integer lkm = tulos.getELaakarienLkm();
//        	return new ReadOnlyObjectWrapper<Integer>(lkm);
			return null;
		});

		col_labrakaynnit.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
//        	Integer lkm = tulos.getELaakarienLkm();
//        	return new ReadOnlyObjectWrapper<Integer>(lkm);
			return null;
		});

		col_saapuneet.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
			Integer lkm = tulos.getArrivalCountProperty();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_palveltu.setCellValueFactory(cellData -> {
			Integer lkm = cellData.getValue().getCompletedCountProperty();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_busyTime.setCellValueFactory(cellData -> {
			Double busyTime = cellData.getValue().getBusyTimeProperty();
			return new ReadOnlyObjectWrapper<Double>(busyTime);
		});

		col_throughput.setCellValueFactory(cellData -> {
			Double throughput = cellData.getValue().getThroughputProperty();
			return new ReadOnlyObjectWrapper<Double>(throughput);
		});

		col_utilization.setCellValueFactory(cellData -> {
			Double utilization = cellData.getValue().getUtilizationProperty();
			return new ReadOnlyObjectWrapper<Double>(utilization);
		});

		col_serviceTime.setCellValueFactory(cellData -> {
			Double serviceTime = cellData.getValue().getServiceTimeProperty();
			return new ReadOnlyObjectWrapper<Double>(serviceTime);
		});
	}
	
	public Boolean isOpen() {
		return open;
	}
	
	@FXML
	private void handleClose() {
		mainApp.closeTietovarasto();
	}
}