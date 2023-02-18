package simu.model.util;

import java.util.Map;

import simu.framework.Tapahtuma;
import simu.model.Asiakas;
import simu.model.TapahtumanTyyppi;

public interface IPalvelupiste {
	void lisaaJonoon(Asiakas a);

	Asiakas otaJonosta();

	boolean onJonossa();

	boolean onVarattu();

	void aloitaPalvelu();

	void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet);

	TapahtumanTyyppi getSkeduloitavanTapahtumanTyyppi();

	Tapahtuma getViimeisinTapahtuma();

	int getID();

	String getJonoString();
}
