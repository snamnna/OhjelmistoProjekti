package controller;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {

	private ISimulaattorinUI ui;
	private IMoottori moottori;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		// Tähän lisää?
	}

	public void visualisoiAsiakas() {
		/*
		 * Platform.runLater(new Runnable() { public void run() {
		 * ui.getVisualisointi.uusiAsiakas(); } });
		 */
	}

	@Override
	public void kaynnistaSimulointi() {
		// TODO Auto-generated method stub
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getSimulointiAika());
		moottori.setViive(ui.getViive()); // Matias muokkaa moottoria?
		ui.getVisualisointi().tyhjennaNaytto();
		((Thread) moottori).start();
	}
	
	public void kaynnistaSimulointiTest() {
		moottori = new OmaMoottori(this, true);
		moottori.setSimulointiaika(1000);
		moottori.setViive(50);
		
//		ui.getVisualisointi().tyhjennaNaytto();
		
		((Thread) moottori).start();
	}

	@Override
	public int haeYlaakarienLkm() {
		return ui.getYlaakarienLkm();
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
	public Map<String, Integer> haePalvelupisteet() {
		Map<String, Integer> palvelupisteet = new HashMap<>();
		palvelupisteet.put("Sairaanhoitaja", 1); // Kovakoodattu 1 koska sairaanhoitajia aina vain 1
		palvelupisteet.put("YLaakari", haeYlaakarienLkm());
		palvelupisteet.put("ELaakari", haeElaakarienLkm());
		palvelupisteet.put("Labra", 1); // Kovakoodattu 1 koska labroja aina vain 1
		return palvelupisteet;
	}
	
	public Map<String, Integer> haePalvelupisteetTest() {
		Map<String, Integer> palvelupisteet = new HashMap<>();
		palvelupisteet.put("Sairaanhoitaja", 1); // Kovakoodattu 1 koska sairaanhoitajia aina vain 1
		palvelupisteet.put("YLaakari", 1);
		palvelupisteet.put("ELaakari", 1);
		palvelupisteet.put("Labra", 1); // Kovakoodattu 1 koska labroja aina vain 1
		return palvelupisteet;
	}

	@Override
	public void haeLoppuaika(double aika) {
//		 return moottori.getLoppuAika();
	}

	@Override
	public void hidasta() {
		 moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public void nopeuta() {
		 moottori.setViive((long)(moottori.getViive()*0.9));
	}
}
