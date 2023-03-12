package simulaattori.framework;

import entity.Tulos;
import simulaattori.model.TapahtumanTyyppi;

import java.util.Map;

/**
 * Rajapinta määrittelee metodit, joita käytetään käytetään simulaattorin moottorin kanssa kommunikointiin.
 */
public interface IMoottori {
    /**
     * Asettaa simulointiajan annettuun arvoon.
     *
     * @param aika simulointiaika
     */
    void setSimulointiaika(double aika);

    /**
     * Palauttaa simulaation viiveen.
     *
     * @return simulaation viive
     */
    long getViive();

    /**
     * Asettaa simulaation viiveen annettuun arvoon.
     *
     * @param viive simulaation viive
     */
    void setViive(long viive);

    /**
     * Palauttaa simulaation tulokset.
     *
     * @return simulaation tulokset
     */
    Tulos getTulos();

    /**
     * asettaa luotavien palvelupisteiden määrät
     *
     * @param palvelupisteMaarat palvelupisteiden määrät, jossa avaimena on palvelupisteen tyyppi ja arvona
     *                           palvelupisteen määrä.
     */
    void setPalvelupisteMaarat(Map<TapahtumanTyyppi, Integer> palvelupisteMaarat);
}
