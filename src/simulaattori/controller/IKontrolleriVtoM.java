package simulaattori.controller;

import simulaattori.model.TapahtumanTyyppi;
import simulaattori.model.util.IPalvelupiste;

import java.util.List;
import java.util.Map;

public interface IKontrolleriVtoM {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot
	int haeYlaakarienLkm();

	int haeElaakarienLkm();

	int haeSairaanhoitajaLkm();

	int haeLabraLkm();

	Map<TapahtumanTyyppi, Integer> haePalvelupisteet();

	void visualisoi();

	void simulointiPaattyi();

	void setPalvelupisteet(Map<TapahtumanTyyppi, List<IPalvelupiste>> palvelupisteet);
}
