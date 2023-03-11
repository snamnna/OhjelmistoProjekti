package simulaattori.simu.model.util;

import simulaattori.simu.model.Asiakas;
import simulaattori.simu.model.TapahtumanTyyppi;

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
