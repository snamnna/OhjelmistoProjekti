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
            ui.alustaVisualisointi();
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
    public Map<TapahtumanTyyppi, Integer> haePalvelupisteet() {
        Map<TapahtumanTyyppi, Integer> palvelupisteet = new HashMap<>();
        palvelupisteet.put(TapahtumanTyyppi.ARR, ui.getSairaanhoitajaLkm());
        palvelupisteet.put(TapahtumanTyyppi.YLARR, ui.getYlaakarienLkm());
        palvelupisteet.put(TapahtumanTyyppi.ELARR, ui.getElaakarienLkm());
        palvelupisteet.put(TapahtumanTyyppi.LABRA_ARRIVAL, ui.getLabraLkm());
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
    public Map<TapahtumanTyyppi, List<IPalvelupiste>> getPalvelupisteet() {
        return moottori.getPalvelupisteet();
    }
}
