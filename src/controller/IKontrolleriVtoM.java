package controller;

import java.util.Map;

public interface IKontrolleriVtoM {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot ja kutsuu moottorin start metodia
	public void kaynnistaSimulointi();
	
	public int haeYlaakarienLkm();

	public int haeElaakarienLkm();

	public double haeLabJakauma();
	
	public Map<String, Integer> haePalvelupisteet();
	
	public void hidasta();

	public void nopeuta();
}
