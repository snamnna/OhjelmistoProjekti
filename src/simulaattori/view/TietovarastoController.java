package simulaattori.view;

import java.util.List;
import entity.Tulos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    ObservableList<Tulos> listTulos = FXCollections.observableArrayList();;
    
    @FXML
    private void initialize() {
    	col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_aika.setCellValueFactory(new PropertyValueFactory<>("simTime"));

        //  fetch the Tulos objects
        List<Tulos> tulosList = TulosDAO.getAllTulokset();
        
        // convert the fetched list of Tulos objects to an observable list
        ObservableList<Tulos> tulosObservableList = FXCollections.observableArrayList(tulosList);

        //  set it as the items of the tableview using the setItems() method.
        table_tulokset.setItems(tulosObservableList);
    }
}
