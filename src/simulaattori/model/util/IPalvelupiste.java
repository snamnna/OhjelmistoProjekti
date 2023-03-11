package simulaattori.model.util;

import simulaattori.model.Asiakas;
import simulaattori.model.TapahtumanTyyppi;

public interface IPalvelupiste {
    void lisaaJonoon(Asiakas a);

    Asiakas otaJonosta();

    boolean onJonossa();

    boolean onVarattu();

    void aloitaPalvelu();

    TapahtumanTyyppi getTyyppi();

    int getID();

    double getKaikkienPalveluAikojenSumma();

    int getDepartureLkm();

    int getAsiakasMaaraJonossa();

    void addDeparture();

    void addArrival();

}
