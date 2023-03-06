package simulaattori.view;

import entity.Tulos;

public interface ISimulaattorinUI {

	int getYlaakarienLkm();

	int getElaakarienLkm();

	int getLabraLkm();

	int getSairaanhoitajaLkm();

	double getSimulointiAika(); // simuloinnin kesto kontrollerille

	// Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
	void setLoppuaika(double aika);

	void setTulokset(); // asetetaan tulokset näkyville, tähän pitää selvittää mitä tyyppiä jne

	// Kontrolleri tarvitsee
	SimulaattoriController getVisualisointi();
	
	long getViive();
}
