package simulaattori.controller;

import entity.Tulos;

public interface IKontrolleriMtoV {

	// Rajapinta, joka tarjotaan käyttöliittymälle:
	public void haeLoppuaika(double aika);

	public void hidasta();

	public void nopeuta();

	public void kaynnistaSimulointi();

	public Tulos getTulos();
}
