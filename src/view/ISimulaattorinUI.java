package view;

public interface ISimulaattorinUI {

	//
	public int getYlaakarienLkm();
	public int getElaakarienLkm();
	public double getLabraJakauma();
	public double getSimulointiAika(); //simuloinnin kesto kontrollerille

	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	public void setTulokset(); //asetetaan tulokset näkyville, tähän pitää selvittää mitä tyyppiä jne


	// Kontrolleri tarvitsee  
	public IVisualisointi getVisualisointi();
	
	//Testikommentti
	}