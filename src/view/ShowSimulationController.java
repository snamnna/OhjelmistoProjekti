package view;

import javafx.fxml.FXML;
import simu.model.OmaMoottori;

public class ShowSimulationController {
	
	private TESTISimulaationGUI mainapp;
	
	private OmaMoottori moottori = new OmaMoottori();
	
	//Referoi itsensä, muokataan oikeaksi myöhemmin
	public void setTesti(TESTISimulaationGUI testiSimulaationGUI) {
		this.mainapp = testiSimulaationGUI;
	}

	@FXML
	private void handleAloitaSimulointi() {
		moottori.aja(); //Tällä aloitetaan simulointi
	}
}
