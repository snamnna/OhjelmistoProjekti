package simulaattori.model.util;

import simulaattori.model.Asiakas;
import simulaattori.model.TapahtumanTyyppi;

/**
 * Rajapinta, jota moottori käyttää palvelupisteiden kanssa kommunikoimiseen.
 */
public interface IPalvelupiste {
    /**
     * Lisaa jonoon asiakas.
     *
     * @param asiakas
     */
    void lisaaJonoon(Asiakas asiakas);

    /**
     * Ottaa asiakkaan jonosta ja asettaa palvelupisteen vapaaksi.
     *
     * @return asiakas
     */
    Asiakas otaJonosta();

    /**
     * Onko palvelupisteen jonossa asiakkaita.
     *
     * @return boolean
     */
    boolean onJonossa();

    /**
     * Onko palvelupiste varattu.
     *
     * @return boolean
     */
    boolean onVarattu();

    /**
     * Arpoo palveluajan ja luo tapahtuman palvelun loppumisesta.
     */
    void aloitaPalvelu();

    /**
     * Palvelupisteen tyyppi
     *
     * @return TapahtumanTyyppi
     */
    TapahtumanTyyppi getTyyppi();

    /**
     * Gets id.
     *
     * @return id
     */
    int getID();

    /**
     * Hakee kaikkien palveluaikojen summan.
     *
     * @return summa
     */
    double getKaikkienPalveluAikojenSumma();

    /**
     * Hakee departuren lkm.
     *
     * @return departures
     */
    int getDepartureLkm();

    /**
     * Asiakkaiden määrä jonossa
     *
     * @return maara
     */
    int getAsiakasMaaraJonossa();

    /**
     * lisää departure.
     */
    void addDeparture();

    /**
     * lisää arrival.
     */
    void addArrival();

}
