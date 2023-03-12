/**
 * Reititin-luokka vastaa asiakkaiden ohjaamisesta vapaaseen tai pienimmän jonon omaavaan palvelupisteeseen niiden tapahtumatyypin perusteella.
 */
package simulaattori.model;

import simulaattori.model.util.IPalvelupiste;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static simulaattori.model.TapahtumanTyyppi.*;

public class Reititin {
    /**
     * ARRIVALS sisältää saapumistapahtumatyyppien listan, jotka ovat myös palvelupisteiden tyyppejä.
     */
    private final static TapahtumanTyyppi[] ARRIVALS = {ARR, YLARR, ELARR, LABRA_ARRIVAL};
    private Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteMap;

    /**
     * Alustaa reitittimen tapahtumatyyppien ja palvelupisteiden välisellä kuvauksella.
     *
     * @param tyyppiToPalveluPMap tapahtumatyyppien ja palvelupisteiden kuvaus
     */
    public void alustaReititin(Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap) {
        this.palvelupisteMap = tyyppiToPalveluPMap;
    }

    /**
     * Palauttaa seuraavan vapaan tai pienimmän jonon omaavan palvelupisteen annetulle tapahtumatyypille.
     *
     * @param tyyppi tapahtumatyyppi, jonka seuraavaa vapaata tai pienimmän jonon omaavaa palvelupistettä haetaan
     * @return seuraava vapaa palvelupiste tai palvelupiste, jolla on pienin asiakasjono
     * @throws NullPointerException jos tyyppi on null
     */
    private IPalvelupiste haeVapaaPalvelupiste(TapahtumanTyyppi tyyppi) {
        Objects.requireNonNull(tyyppi, "tyyppi ei saa olla null");
        if (!Arrays.asList(ARRIVALS).contains(tyyppi)) {
            throw new IllegalArgumentException("tyyppi ei ole kelvollinen palvelupisteen tyyppi");
        }
        List<IPalvelupiste> palvelupisteet = palvelupisteMap.get(tyyppi);
        IPalvelupiste palvelupiste = palvelupisteet.get(0);
        for (IPalvelupiste p : palvelupisteet) {
            if (!p.onVarattu() || p.getAsiakasMaaraJonossa() < palvelupiste.getAsiakasMaaraJonossa()) {
                palvelupiste = p;
            }
        }
        return palvelupiste;
    }

    /**
     * Palauttaa seuraavan vapaan tai pienimmän jonon omaavan palvelupisteen annetulle tapahtumatyypille.
     *
     * @param tyyppi tapahtumatyyppi, jonka seuraavaa vapaata tai pienimmän jonon omaavaa palvelupistettä haetaan
     * @return seuraava vapaa palvelupiste tai palvelupiste, jolla on pienin asiakasjono, tai null, jos sellaista ei ole saatavilla
     * @throws NullPointerException jos palvelupisteMap on null
     */
    public IPalvelupiste haeSeuraavaPalvelupiste(TapahtumanTyyppi tyyppi) {
        Objects.requireNonNull(palvelupisteMap, "palvelupisteMap ei saa olla null");
        if (Arrays.asList(ARRIVALS).contains(tyyppi)) {
            return haeVapaaPalvelupiste(tyyppi);
        }
        return null;
    }
}
