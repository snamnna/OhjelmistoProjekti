package simulaattori.simu.model.util;

import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.model.Asiakas;
import simulaattori.simu.model.TapahtumanTyyppi;

import java.util.Map;

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

	double getKaikkienPalveluAikojenSumma();

	int getDepartureLkm();

	int getAsiakasMaaraJonossa();

	void addDeparture();

	void addArrival();

	int getArrivals();
}
