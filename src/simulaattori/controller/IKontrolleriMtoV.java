package simulaattori.controller;

import entity.Tulos;
import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

/**
 * Rajapinta määrittelee metodit, joita käyttöliittymä käyttää kontrollerin kanssa kommunikoimiseen. Käyttöliittymä
 * kutsuu metodeja käynnistääkseen ja ohjatakseen simulaation kulkua sekä hakeakseen simulaattorin tulokset simulaation
 * päätyttyä.
 */
public interface IKontrolleriMtoV {
    /**
     * Hidastaa simulaattoria.
     */
    void hidasta();

    /**
     * Nopeuttaa simulaattoria.
     */
    void nopeuta();

    /**
     * Käynnistää simulaattorin.
     */
    void kaynnistaSimulointi();

    /**
     * Hakee simulaattorilta simuloinnin tuloksen.
     *
     * @return simulaation lopputulos Tulos-oliona.
     */
    Tulos getTulos();

    /**
     * Hakee simulaattorilta mapin, jossa palvelupisteet ovat tyypeittäin listoissa.
     *
     * @return simulaation lopputulos Tulos-oliona.
     */
    Map<TapahtumanTyyppi, List<IPalvelupiste>> getPalvelupisteet();
}
