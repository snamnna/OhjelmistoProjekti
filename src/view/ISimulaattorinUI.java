package view;

public interface ISimulaattorinUI {

	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
		public double getAika();
		public long getViive();
		
		//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
		public void setLoppuaika(double aika);
		
		// Kontrolleri tarvitsee  
		public IVisualisointi getVisualisointi();

}