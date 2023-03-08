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
	
	public void simulointiPaattyi() {
		ui.setSimuloidaan(false);
		Platform.runLater(() -> ui.getTulos());
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
	public void haeLoppuaika(double aika) {
//		 return moottori.getLoppuAika();
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
	public void setKokonaisaika(String aika) {
		Platform.runLater(() -> ui.setKokonaisaika(aika));
	}

	@Override
	public void setPalveltu(String lkm) {
		Platform.runLater(() -> ui.setPalveltu(lkm));
	}

	@Override
	public void setELaakarienLkm(String lkm) {
		Platform.runLater(() -> ui.setELaakarienLkm(lkm));
	}

	@Override
	public void setYLaakarienLkm(String lkm) {
		Platform.runLater(() -> ui.setYLaakarienLkm(lkm));
	}

	@Override
	public void setLabrakaynteja(String lkm) {
		Platform.runLater(() -> ui.setLabrakaynteja(lkm));
	}

	@Override
	public void setKayttoaste(String kayttoaste) {
		Platform.runLater(() -> ui.setKayttoaste(kayttoaste));
	}

	@Override
	public void setKeskimaarainenPalveluaika(String aika) {
		Platform.runLater(() -> ui.setKeskimaarainenPalveluaika(aika));
	}

	@Override
	public void setLapimenoAika(String aika) {
		Platform.runLater(() -> ui.setLapimenoAika(aika));
	}

	@Override
	public void setKeskimaarainenJonotusaika(String aika) {
		Platform.runLater(() -> ui.setKeskimaarainenJonotusaika(aika));
	}

	@Override
	public Tulos getTulos() {
		return moottori.getTulos();
	}
}
