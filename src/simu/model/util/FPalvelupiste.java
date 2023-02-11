package simu.model.util;

import eduni.distributions.Normal;
import simu.framework.Tapahtumalista;
import simu.model.*;

public class FPalvelupiste {
    public static IPalvelupiste createPalvelupiste(TapahtumanTyyppi tyyppi, Tapahtumalista tapahtumalista) {
        IPalvelupiste uusiPalvelupiste;
        switch (tyyppi) {
            case ARR: uusiPalvelupiste = new Sairaanhoitaja(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.ARR);
                break;
            case YLARR: uusiPalvelupiste = new YLaakari(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.YLARR);
                break;
            case ELARR: uusiPalvelupiste = new ELaakari(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.ELARR);
                break;
            case LABRA_ARRIVAL: uusiPalvelupiste = new Labra(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.LABRA_ARRIVAL);
                break;
            default: throw new IllegalArgumentException("Unexpected value: " + tyyppi);
        };
        return uusiPalvelupiste;
    }
}
