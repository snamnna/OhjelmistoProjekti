package simulaattori.simu.model;

import simulaattori.simu.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

public class Reititin {

    private Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteMap;
    private List<Asiakas> asiakkaat;

    public void alustaReititin(Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap) {
        this.palvelupisteMap = tyyppiToPalveluPMap;
    }

    public List<IPalvelupiste> haePalvelupisteet(TapahtumanTyyppi t) {
        return palvelupisteMap.get(t);
    }

    public IPalvelupiste haeVapaaPalvelupiste(TapahtumanTyyppi tyyppi) {
        List<IPalvelupiste> palvelupisteet = palvelupisteMap.get(tyyppi);
        // hae palvelupiste joka ei ole varattu tai jonka jonossa on vähiten asiakkaita
        IPalvelupiste palvelupiste = palvelupisteet.get(0);
        for (IPalvelupiste p : palvelupisteet) {
            if (!p.onVarattu() || p.getAsiakasMaaraJonossa() < palvelupiste.getAsiakasMaaraJonossa()) {
                palvelupiste = p;
            }
        }
        return palvelupiste;
    }

    public IPalvelupiste haeSeuraavaPalvelupiste(IPalvelupiste palvelupiste, TapahtumanTyyppi tyyppi) {
        if (tyyppi == TapahtumanTyyppi.ARR || tyyppi == TapahtumanTyyppi.YLARR || tyyppi == TapahtumanTyyppi.ELARR || tyyppi == TapahtumanTyyppi.LABRA_ARRIVAL) {
            return haeVapaaPalvelupiste(tyyppi);
        }
//        if (palvelupiste instanceof Sairaanhoitaja) {
//            return haeVapaaPalvelupiste(haePalvelupisteet(tyyppi));
//        } else if (palvelupiste instanceof YLaakari) {
//            return haeVapaaPalvelupiste(haePalvelupisteet(TapahtumanTyyppi.YLARR));
//        } else if (palvelupiste instanceof ELaakari) {
//            return haeVapaaPalvelupiste(haePalvelupisteet(TapahtumanTyyppi.ELARR));
//        } else if (palvelupiste instanceof Labra) {
//            return haeVapaaPalvelupiste(haePalvelupisteet(TapahtumanTyyppi.LABRA_ARRIVAL));
//        }
        return null;
    }
}
