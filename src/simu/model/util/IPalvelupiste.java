package simu.model.util;

import simu.framework.Tapahtuma;
import simu.model.Asiakas;

public interface IPalvelupiste {
    void lisaaJonoon(Asiakas a);

    Asiakas otaJonosta();

    boolean onJonossa();

    boolean onVarattu();

    void aloitaPalvelu();

    void siirraAsiakas(Tapahtuma tapahtuma, IPalvelupiste[] palvelupisteet);

    Tapahtuma getViimeisinTapahtuma();

    String getJonoString();
}
