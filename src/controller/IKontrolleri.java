package controller;

public interface IKontrolleri {
	
	// Rajapinta, joka tarjotaan  käyttöliittymälle:
	public void naytaLoppuaika(double aika);
	public void visualisoiAsiakas();
	
	// Rajapinta, joka tarjotaan moottorille:
	public void kaynnistaSimulointi();
	public int naytaYlaakarienLkm();
	public int naytaElaakarienLkm();
	public double naytaLabJakauma();

	//muita? (onko ees oikeita :D)
}
