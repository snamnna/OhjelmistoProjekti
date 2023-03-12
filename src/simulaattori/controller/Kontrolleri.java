/**
 * Kontrolleri-luokka hoitaa käyttöliittymän ja mallin välisen kommunikoinnin.
 */
package simulaattori.controller;

import entity.Tulos;
import javafx.application.Platform;
import simulaattori.framework.IMoottori;
import simulaattori.model.OmaMoottori;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;
import simulaattori.view.ISimulaattorinUI;

import java.util.List;
import java.util.Map;


public class Kontrolleri implements IKontrolleriMtoV, IKontrolleriVtoM {

    private ISimulaattorinUI ui;
    private IMoottori moottori;
    private volatile boolean simulointiKaynnissa;

    /**
     * Konstruktori Kontrolleri-luokalle.
     *
     * @param ui Käyttöliittymä
     */
    public Kontrolleri(ISimulaattorinUI ui) {
        this.ui = ui;
        simulointiKaynnissa = false;
    }

    @Override
    public void visualisoi() {
        Platform.runLater(() -> ui.visualisoi());
    }

    @Override
    public void kaynnistaSimulointi() {
        if (!simulointiKaynnissa) {
            ui.setSimuloidaan(true);
            moottori = new OmaMoottori(this);
            moottori.setSimulointiaika(ui.getSimulointiAika());
            moottori.setViive(ui.getViive());
            moottori.setPalvelupisteMaarat(ui.getPalvelupisteMaarat());
            ((Thread) moottori).start();
            simulointiKaynnissa = true;
        }
    }

    @Override
    public void vieTulokset(Tulos tulos) {
        Platform.runLater(() -> {
            ui.setTulos(tulos);
            ui.setSimuloidaan(false);
            simulointiKaynnissa = false;
        });
    }

    @Override
    public void viePalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet) {
        ui.viePalvelupisteet(palvelupisteet);
    }

    @Override
    public void hidasta() {
        moottori.setViive((long) (moottori.getViive() * 1.10));
    }

    @Override
    public void nopeuta() {
        moottori.setViive((long) (moottori.getViive() * 0.9));
    }
}
