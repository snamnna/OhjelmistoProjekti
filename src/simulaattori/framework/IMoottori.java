package simulaattori.framework;

import entity.Tulos;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.List;
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
     * Palauttaa simulaation palvelupisteet.
     *
     * @return simulaatiossa käytössä olevat palvelupisteet
     */
    Map<TapahtumanTyyppi, List<IPalvelupiste>> getPalvelupisteet();
}
