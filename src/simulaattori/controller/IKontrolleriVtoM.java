package simulaattori.controller;

import java.util.Map;

import entity.Tulos;

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

}
