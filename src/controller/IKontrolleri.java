package controller;

public interface IKontrolleri {
	// Rajapinta, joka tarjotaan moottorille:
	// hakee annetut arvot ja kutsuu moottorin start metodia
	public void kaynnistaSimulointi();

	// Rajapinta, joka tarjotaan käyttöliittymälle:
	public void haeLoppuaika(double aika);

	public void visualisoiAsiakas();

	public void hidasta();

	public void nopeuta();

	public int haeYlaakarienLkm();

	public int haeElaakarienLkm();

	public double haeLabJakauma();

}
