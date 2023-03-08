package simulaattori.simu.framework;

import java.util.Map;

import entity.Tulos;
import simulaattori.simu.model.util.IPalvelupiste;

public interface IMoottori {
	// Kontrolleri käyttää tätä rajapintaa
	void setSimulointiaika(double aika);
	void setViive(long viive);
	long getViive();
	Tulos getTulos();
	Map<Integer, IPalvelupiste> getPalvelupisteet();
}
