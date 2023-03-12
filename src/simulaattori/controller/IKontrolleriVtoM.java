package simulaattori.controller;

import simulaattori.model.TapahtumanTyyppi;

import java.util.Map;

/**
 * Rajapinta määrittelee metodit, joita simulaattori käyttää kontrollerin kanssa kommunikoimiseen. Simulaattori kutsuu
 * metodeja hakeakseen käyttäjän syöttämät palvelupisteiden määrät, visualisoinnin päivitykseen ja ilmoittaakseen
 * simuloinnin päättymisestä.
 */
public interface IKontrolleriVtoM {

    /**
     * Hakee käyttäjän syöttämät palvelupisteiden määrät.
     *
     * @return tietyn tyyppisen palvelupisteiden lukumäärä kuvattuna sen saapumistyyppiin.
     */
    Map<TapahtumanTyyppi, Integer> haePalvelupisteet();

    /**
     * Piirtää palvelupisteiden jonot käyttöliittymään.
     */
    void visualisoi();

    /**
     * Ilmoittaa käyttöliittymälle, kun simulointi päättyy. Käyttöliittymä käyttää tätä metodia, jotta se voi
     * esimerkiksi päivittää käyttäjälle näkyvän datan.
     */
    void simulointiPaattyi();
}
