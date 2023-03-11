package simulaattori.simu.model.util;

import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.model.Asiakas;
import simulaattori.simu.model.TapahtumanTyyppi;

public interface IPalvelupiste {
	void lisaaJonoon(Asiakas a);

	Asiakas otaJonosta();

	boolean onJonossa();

	boolean onVarattu();

	void aloitaPalvelu();

	TapahtumanTyyppi getTyyppi();

	Tapahtuma getViimeisinTapahtuma();

	int getID();

	double getKaikkienPalveluAikojenSumma();

	int getDepartureLkm();

	int getAsiakasMaaraJonossa();

	void addDeparture();

	void addArrival();

	int getArrivals();
}
