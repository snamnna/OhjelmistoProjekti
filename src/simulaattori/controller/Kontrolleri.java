package simulaattori.controller;

import entity.Tulos;
import javafx.application.Platform;
import simulaattori.framework.IMoottori;
import simulaattori.model.OmaMoottori;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;
import simulaattori.view.ISimulaattorinUI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<TapahtumanTyyppi, Integer> haePalvelupisteet() {
        Map<TapahtumanTyyppi, Integer> palvelupisteet = new HashMap<>();
        palvelupisteet.put(TapahtumanTyyppi.ARR, haeSairaanhoitajaLkm());
        palvelupisteet.put(TapahtumanTyyppi.YLARR, haeYlaakarienLkm());
        palvelupisteet.put(TapahtumanTyyppi.ELARR, haeElaakarienLkm());
        palvelupisteet.put(TapahtumanTyyppi.LABRA_ARRIVAL, haeLabraLkm());
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

    @Override
    public Tulos getTulos() {
        return moottori.getTulos();
    }

    @Override
    public void setPalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet) {
        ui.setPalvelupisteet(palvelupisteet);
    }
}
