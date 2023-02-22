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

    double i = 0;
    double j = 10;

    @FXML
    private AnchorPane parentPane;
    @FXML
    private Canvas visualisointiCanvas;

    @FXML
    private void initialize() {
        gc = visualisointiCanvas.getGraphicsContext2D();
        AnchorPane.setTopAnchor(visualisointiCanvas, 0.0);
        AnchorPane.setRightAnchor(visualisointiCanvas, 0.0);
        AnchorPane.setBottomAnchor(visualisointiCanvas, 0.0);
        AnchorPane.setLeftAnchor(visualisointiCanvas, 0.0);
        visualisointiCanvas.widthProperty().bind(parentPane.widthProperty());
        visualisointiCanvas.heightProperty().bind(parentPane.heightProperty());
    }

    public void tyhjennaNaytto() {
        gc.setFill(Color.rgb(18,18,18));
        gc.fillRect(0, 0, visualisointiCanvas.getWidth(), visualisointiCanvas.getHeight());
    }

    public void uusiAsiakas() {
        gc.setFill(Color.RED);
        gc.fillOval(i,j,10,10);
        i = (i + 10) % visualisointiCanvas.getWidth();
        if (i==0) j+=10;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
