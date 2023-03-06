package simulaattori.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.Session;

import entity.Tulos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import simulaattori.MainApp;
import simulaattori.simu.model.TulosDAO;

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
    
    ObservableList<Tulos> listTulos = FXCollections.observableArrayList();
    
    @FXML
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    }
    
    /*
    @FXML
	public void initialize() {
    	col_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()));
        col_aika.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSimTime()));
        col_eLaakarit.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getErikoislaakarit()));
        col_yleisLaakarit.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYleislaakarit()));
        col_labrakaynnit.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getLabraArrivalit()));
        col_saapuneet.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getArrivalCount()));
        col_palveltu.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCompletedCount()));
        col_busyTime.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getBusyTime()));
        col_throughput.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getThroughput()));
        col_utilization.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getUtilization()));
        col_serviceTime.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getServiceTime()));

        //  fetch the Tulos objects
        List<Tulos> tulosList = TulosDAO.getAllTulokset();
        
        // convert the fetched list of Tulos objects to an observable list
        ObservableList<Tulos> tulosObservableList = FXCollections.observableArrayList(tulosList);

        //  set it as the items of the tableview using the setItems() method.
        table_tulokset.setItems(tulosObservableList);
    }
    */
}
