package controller;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM {

	private ISimulaattorinUI ui;
	private IMoottori moottori;
	
	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		// lisää?
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
	public int haeYlaakarienLkm() {
		return 	ui.getYlaakarienLkm();
	}

	@Override
	public int haeElaakarienLkm() {
		return ui.getElaakarienLkm();
	}

	@Override
	public double haeLabJakauma() {
		return ui.getLabraJakauma();
	}

	@Override
	public void haeLoppuaika(double aika) {
		
	}

	@Override
	public void hidasta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nopeuta() {
		// TODO Auto-generated method stub
		
	}
 }
