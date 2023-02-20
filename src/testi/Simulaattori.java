package testi;
import controller.Kontrolleri;
import simu.framework.*;
import simu.framework.Trace.Level;
import simu.model.OmaMoottori;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {
		Trace.setTraceLevel(Level.INFO);
		Kontrolleri k = new Kontrolleri(null);
		k.kaynnistaSimulointiTest();
	}
}
