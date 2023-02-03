package simu.model.util;

import eduni.distributions.Normal;
import simu.framework.Tapahtumalista;
import simu.model.*;

public class FPalvelupiste {
    public static IPalvelupiste createPalvelupiste(TapahtumanTyyppi tyyppi, Tapahtumalista tapahtumalista) {
        return switch (tyyppi) {
            case ARR: yield new Sairaanhoitaja(new Normal(10, 6), tapahtumalista, TapahtumanTyyppi.ARR);
            case YLARR: yield new YLaakari(new Normal(10, 10), tapahtumalista, TapahtumanTyyppi.YLARR);
            case ELARR: yield new ELaakari(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.ELARR);
            case LABRA_ARRIVAL: yield new Labra(new Normal(5, 3), tapahtumalista, TapahtumanTyyppi.LABRA_ARRIVAL);
            default: throw new IllegalArgumentException("Unexpected value: " + tyyppi);
        };
    }
}
