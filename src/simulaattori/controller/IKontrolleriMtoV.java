package simulaattori.controller;

import entity.Tulos;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

/**
 * Rajapinta määrittelee metodit, joita simulaattori käyttää käyttöliittymän kanssa kommunikoimiseen.
 */
public interface IKontrolleriMtoV {
    /**
     * Visualisoi palvelupisteet
     */
    void visualisoi();

    /**
     * Vie tulokset käyttöliittymälle.
     *
     * @param tulos
     */
    void vieTulokset(Tulos tulos);

    /**
     * Vie visualisoitavat palvelupisteet käyttöliittymälle
     *
     * @param palvelupisteet
     */
    void viePalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet);
}
