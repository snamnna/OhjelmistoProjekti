package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri {

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

	@Override
	public void kaynnistaSimulointi() {
		// TODO Auto-generated method stub
	}

	@Override
	public int naytaYlaakarienLkm() {
		return 	ui.getYlaakarienLkm();
	}

	@Override
	public int naytaElaakarienLkm() {
		return ui.getElaakarienLkm();
	}

	@Override
	public double naytaLabJakauma() {
		return ui.getLabraJakauma();
	}
 }
