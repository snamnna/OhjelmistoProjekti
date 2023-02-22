package simulaattori.testi;
import simulaattori.controller.Kontrolleri;
import simulaattori.simu.framework.*;
import simulaattori.simu.framework.Trace.Level;

public class Simulaattori { //Tekstipohjainen

	public static void main(String[] args) {
		Trace.setTraceLevel(Level.INFO);
		Kontrolleri k = new Kontrolleri(null);
		k.kaynnistaSimulointiTest();
	}
}
