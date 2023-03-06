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
	
	void setTulokset();

	Map<String, Integer> haePalvelupisteet();

	void visualisoiAsiakas();
	
	Map<String, Integer> haePalvelupisteetTest();

}
