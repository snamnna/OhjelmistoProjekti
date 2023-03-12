package simulaattori.view;

import entity.Tulos;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

/**
 * Rajapinta määrittelee metodit, joita kontrolleri käyttää käyttöliittymän kanssa kommunikointiin.
 */
public interface ISimulaattorinUI {
    /**
     * Palauttaa käyttäjän syöttämän simulointiajan.
     *
     * @return simulointiaika.
     */
    double getSimulointiAika();

    /**
     * Palauttaa käyttäjän syöttämän viiveen.
     *
     * @return viive.
     */
    long getViive();

    /**
     * Asettaa, simuloidaanko vai ei.
     *
     * @param value true, jos simulaatio on käynnissä, false muuten.
     */
    void setSimuloidaan(boolean value);


    /**
     * Vie tuloksen TuloksetControllerille, joka näyttää tuloksen käyttäjälle.
     *
     * @param tulos
     */
    void setTulos(Tulos tulos);

    /**
     * vie SimulaattoriControllerille visualisoitavat palvelupisteet.
     *
     * @param palvelupisteet
     */
    void viePalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet);

    /**
     * hakee käyttäjän syöttämät palvelupisteiden määrät.
     *
     * @return palvelupisteiden määrät, jossa avaimena on palvelupisteen tyyppi ja arvona palvelupisteen määrä.
     */
    Map<TapahtumanTyyppi, Integer> getPalvelupisteMaarat();

    /**
     * Visualisoi palvelupisteiden jonot käyttöliittymään.
     */
    void visualisoi();
}
