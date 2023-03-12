package simulaattori.view;

import entity.Tulos;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import simulaattori.MainApp;

/**
 * Tietovarastonäkymän kontrolleri.
 */
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

	/**
	 * Sets main app.
	 *
	 * @param mainApp the main app
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		table_tulokset.setItems(mainApp.getTulokset());
	}

	/**
	 * Remove tulos.
	 *
	 * @param tulos the tulos
	 */
	public void removeTulos(Tulos tulos) {
		table_tulokset.getItems().remove(tulos);
	}

	/**
	 * Lisaa tulos.
	 *
	 * @param tulos the tulos
	 */
	public void lisaaTulos(Tulos tulos) {
		table_tulokset.getItems().add(tulos);
	}

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		col_id.setCellValueFactory(cellData -> {
			Integer id = cellData.getValue().getId();
			return new ReadOnlyObjectWrapper<Integer>(id);
		});

		col_aika.setCellValueFactory(cellData -> {
			Double aika = cellData.getValue().getKokonaisaika();
			return new ReadOnlyObjectWrapper<Double>(aika);
		});

		col_eLaakarit.setCellValueFactory(cellData -> {
			Integer lkm = cellData.getValue().getErikoislääkärit();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_yleisLaakarit.setCellValueFactory(cellData -> {
			Integer lkm = cellData.getValue().getYleislääkärit();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_labrakaynnit.setCellValueFactory(cellData -> {
			Integer lkm = cellData.getValue().getLabraArrivalit();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_saapuneet.setCellValueFactory(cellData -> {
			Tulos tulos = cellData.getValue();
			Integer lkm = tulos.getArrivalCount();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_palveltu.setCellValueFactory(cellData -> {
			Integer lkm = cellData.getValue().getCompletedCount();
			return new ReadOnlyObjectWrapper<Integer>(lkm);
		});

		col_busyTime.setCellValueFactory(cellData -> {
			Double busyTime = cellData.getValue().getBusyTime();
			return new ReadOnlyObjectWrapper<Double>(busyTime);
		});

		col_throughput.setCellValueFactory(cellData -> {
			Double throughput = cellData.getValue().getThroughput();
			return new ReadOnlyObjectWrapper<Double>(throughput);
		});

		col_utilization.setCellValueFactory(cellData -> {
			Double utilization = cellData.getValue().getUtilization();
			return new ReadOnlyObjectWrapper<Double>(utilization);
		});

		col_serviceTime.setCellValueFactory(cellData -> {
			Double serviceTime = cellData.getValue().getServiceTime();
			return new ReadOnlyObjectWrapper<Double>(serviceTime);
		});

		table_tulokset.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> mainApp.setTulos(newValue));
	}

	@FXML
	private void handleClose() {
		mainApp.closeTietovarasto();
	}

	/**
	 * Disable tulokset table.
	 *
	 * @param disable
	 */
	public void disableTuloksetTable(boolean disable) {
		table_tulokset.setDisable(disable);
	}
}