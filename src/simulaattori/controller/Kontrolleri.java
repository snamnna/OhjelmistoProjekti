package simulaattori.controller;

import java.util.HashMap;
import java.util.Map;

import entity.Tulos;
import javafx.application.Platform;
import simulaattori.simu.framework.IMoottori;
import simulaattori.simu.framework.Moottori;
import simulaattori.simu.model.OmaMoottori;
import simulaattori.view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {

	private ISimulaattorinUI ui;
	private IMoottori moottori;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		// Tähän lisää?
	}

	public void visualisoiAsiakas() {
		 Platform.runLater(() -> ui.getVisualisointi().uusiAsiakas());
	}

	@Override
	public void kaynnistaSimulointi() {
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.setSimulointiaika(ui.getSimulointiAika());
		moottori.setViive(ui.getViive());
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
	public int haeSairaanhoitajaLkm() {
		return ui.getSairaanhoitajaLkm();
	}

	@Override
	public int haeLabraLkm() {
		return ui.getLabraLkm();
	}

	@Override
	public Map<String, Integer> haePalvelupisteet() {
		Map<String, Integer> palvelupisteet = new HashMap<>();
		palvelupisteet.put("Sairaanhoitaja", haeSairaanhoitajaLkm());
		palvelupisteet.put("YLaakari", haeYlaakarienLkm());
		palvelupisteet.put("ELaakari", haeElaakarienLkm());
		palvelupisteet.put("Labra", haeLabraLkm());
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
	
	public Moottori getMoottori() {
		return (Moottori) moottori;
	}

	@Override
	public void setTulokset(Tulos tulokset) {
		ui.setTulokset(tulokset);
	}
}
