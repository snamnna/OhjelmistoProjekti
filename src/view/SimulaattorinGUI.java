package view;


import java.text.DecimalFormat;
import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;


public class SimulaattorinGUI extends Application implements ISimulaattorinUI{
	
	private IKontrolleri kontrolleri;
	
	@Override
	public void init() {
		kontrolleri = new Kontrolleri(this);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			init();
			primaryStage.setTitle("Päivystyssimulaattori");
			
			HBox hBox = new HBox();
			
			Scene scene = new Scene(hBox, 1024, 768);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public int getYlaakarienLkm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getElaakarienLkm() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLabraJakauma() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSimulointiAika() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLoppuaika(double aika) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTulokset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IVisualisointi getVisualisointi() {
		// TODO Auto-generated method stub
		return null;
	}
}
