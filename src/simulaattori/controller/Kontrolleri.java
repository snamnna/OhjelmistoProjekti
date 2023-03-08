package simulaattori.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Tulos;
import javafx.application.Platform;
import simulaattori.simu.framework.IMoottori;
import simulaattori.simu.framework.Moottori;
import simulaattori.simu.model.OmaMoottori;
import simulaattori.simu.model.TapahtumanTyyppi;
import simulaattori.simu.model.util.IPalvelupiste;
import simulaattori.view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleriVtoM, IKontrolleriMtoV {

	private ISimulaattorinUI ui;
	private IMoottori moottori;
	private volatile boolean simulointiKaynnissa;

	public Kontrolleri(ISimulaattorinUI ui) {
		this.ui = ui;
		simulointiKaynnissa = false;
	}

	public void visualisoi() {
		Platform.runLater(() -> ui.getVisualisointi().visualisoi());
	}

	@Override
	public void kaynnistaSimulointi() {
		if (!simulointiKaynnissa) {
			moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
			moottori.setSimulointiaika(ui.getSimulointiAika());
			moottori.setViive(ui.getViive());
			((Thread) moottori).start();
			simulointiKaynnissa = true;
		}
	}

	public void simulointiPaattyi() {
		Platform.runLater(() -> {
			ui.setSimuloidaan(false);
			simulointiKaynnissa = false;
		});
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

	@Override
	public void hidasta() {
		moottori.setViive((long) (moottori.getViive() * 1.10));
	}

	@Override
	public void nopeuta() {
		moottori.setViive((long) (moottori.getViive() * 0.9));
	}

	public Moottori getMoottori() {
		return (Moottori) moottori;
	}

	@Override
	public Tulos getTulos() {
		return moottori.getTulos();
	}

	@Override
	public void setPalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet) {
		ui.setPalvelupisteet(palvelupisteet);
	}
}
