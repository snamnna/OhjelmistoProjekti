package controller;

import java.util.Map;

public interface IKontrolleriVtoM {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot
	public int haeYlaakarienLkm();

	public int haeElaakarienLkm();

	public double haeLabJakauma();

	public Map<String, Integer> haePalvelupisteet();

	public void visualisoiAsiakas();
	
	public Map<String, Integer> haePalvelupisteetTest();

}
