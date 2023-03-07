package simulaattori.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import simulaattori.MainApp;

public class SimulaattoriController {

    private MainApp mainApp;
    private GraphicsContext gc;
    private int sairaanhoitajaLkm;
    private int yLaakariLkm;
    private int eLaakariLkm;
    private int labraLkm;

    double i = 0;
    double j = 10;

    @FXML
    private AnchorPane parentPane;
    @FXML
    private Canvas canvas;

    @FXML
    private void initialize() {
        gc = canvas.getGraphicsContext2D();
        AnchorPane.setTopAnchor(canvas, 0.0);
        AnchorPane.setRightAnchor(canvas, 0.0);
        AnchorPane.setBottomAnchor(canvas, 0.0);
        AnchorPane.setLeftAnchor(canvas, 0.0);
        canvas.widthProperty().bind(parentPane.widthProperty());
        canvas.heightProperty().bind(parentPane.heightProperty());
    }

    public void tyhjennaNaytto() {
        gc.setFill(Color.rgb(18,18,18));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void uusiAsiakas() {
        gc.setFill(Color.RED);
        gc.fillOval(i,j,10,10);
        i += 10;
        if (i >= canvas.getWidth()) {
            i = 0;
            j += 10;
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

	public void alustukset() {
		tyhjennaNaytto();
	}
}
