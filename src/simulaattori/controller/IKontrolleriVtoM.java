package simulaattori.controller;

import java.util.Map;

import simulaattori.simu.model.util.IPalvelupiste;

public interface IKontrolleriVtoM {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot
	int haeYlaakarienLkm();

	int haeElaakarienLkm();

	int haeSairaanhoitajaLkm();

	int haeLabraLkm();

	Map<String, Integer> haePalvelupisteet();

	void visualisoiAsiakas();

	void simulointiPaattyi();

	void setPalvelupisteet(Map<Integer, IPalvelupiste> palvelupisteet);

}
