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
	
	void setKokonaisaika(String aika);
	
	void setPalveltu(String lkm);
	
	void setELaakarienLkm(String lkm);
	
	void setYLaakarienLkm(String lkm);
	
	void setLabrakaynteja(String lkm);
	
	void setKayttoaste(String kayttoaste);
	
	void setKeskimaarainenPalveluaika(String aika);
	
	void setLapimenoAika(String aika);
	
	void setKeskimaarainenJonotusaika(String aika);
	
	void simulointiPaattyi();

}
