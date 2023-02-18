package simu.model.util;

import java.util.Map;

import simu.framework.Tapahtuma;
import simu.model.Asiakas;

public interface IPalvelupiste {
    void lisaaJonoon(Asiakas a);

    Asiakas otaJonosta();

    boolean onJonossa();

    boolean onVarattu();

    void aloitaPalvelu();

    void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet);

    Tapahtuma getViimeisinTapahtuma();
    
    int getID();

    String getJonoString();
}
