package simulaattori.simu.model.util;

import java.util.Map;

import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.model.Asiakas;
import simulaattori.simu.model.TapahtumanTyyppi;

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
	
	double getKaikkienPalveluAikojenSumma();
	
	int getDepartureLkm();
}
