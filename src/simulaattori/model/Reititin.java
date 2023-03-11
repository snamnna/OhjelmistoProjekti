package simulaattori.model;

import simulaattori.model.util.IPalvelupiste;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static simulaattori.model.TapahtumanTyyppi.*;

public class Reititin {
    private final static TapahtumanTyyppi[] ARRIVALS = {ARR, YLARR, ELARR, LABRA_ARRIVAL};
    private Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteMap;

    public void alustaReititin(Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap) {
        this.palvelupisteMap = tyyppiToPalveluPMap;
    }

    public IPalvelupiste haeVapaaPalvelupiste(TapahtumanTyyppi tyyppi) {
        List<IPalvelupiste> palvelupisteet = palvelupisteMap.get(tyyppi);
        IPalvelupiste palvelupiste = palvelupisteet.get(0);
        for (IPalvelupiste p : palvelupisteet) {
            if (!p.onVarattu() || p.getAsiakasMaaraJonossa() < palvelupiste.getAsiakasMaaraJonossa()) {
                palvelupiste = p;
            }
        }
        return palvelupiste;
    }

    public IPalvelupiste haeSeuraavaPalvelupiste(TapahtumanTyyppi tyyppi) {
        if (Arrays.asList(ARRIVALS).contains(tyyppi)) {
            return haeVapaaPalvelupiste(tyyppi);
        }
        return null;
    }
}
