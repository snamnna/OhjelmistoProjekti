package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri {   // UUSI

	private ISimulaattorinUI ui;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		// lisää?
	}
	
	public void naytaLoppuaika(double aika) {
		// Platform.runLater(() -> ui.setLoppuaika(aika));
	}
	
	public void visualisoiAsiakas(){
		/* Platform.runLater(new Runnable() {
			public void run() {
				 ui.getVisualisointi.uusiAsiakas();
			}
		}); */
	}
 }
