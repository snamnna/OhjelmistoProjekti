package simulaattori.view;

public interface ISimulaattorinUI {

	int getYlaakarienLkm();

	int getElaakarienLkm();

	int getLabraLkm();

	int getSairaanhoitajaLkm();

	double getSimulointiAika(); // simuloinnin kesto kontrollerille

	// Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
	void setLoppuaika(double aika);

	// Kontrolleri tarvitsee
	SimulaattoriController getVisualisointi();

	long getViive();

	void setKokonaisaika(String aika);

	void setPalveltu(String lkm);

	void setELaakarienLkm(String lkm);

	void setYLaakarienLkm(String lkm);

	void setLabrakaynteja(String lkm);

	void setKayttoaste(String kayttoaste);

	void setKeskimaarainenPalveluaika(String aika);

	void setLapimenoAika(String aika);

	void setKeskimaarainenJonotusaika(String aika);
}
