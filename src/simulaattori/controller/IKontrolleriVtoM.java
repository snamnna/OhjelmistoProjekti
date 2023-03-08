package simulaattori.controller;

import java.util.List;
import java.util.Map;

import simulaattori.simu.model.TapahtumanTyyppi;
import simulaattori.simu.model.util.IPalvelupiste;

public interface IKontrolleriVtoM {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot
	int haeYlaakarienLkm();

	int haeElaakarienLkm();

	int haeSairaanhoitajaLkm();

	int haeLabraLkm();

	Map<String, Integer> haePalvelupisteet();

	void visualisoi();

	void simulointiPaattyi();

	void setPalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet);
}
