package simulaattori.framework;

import entity.Tulos;
import simulaattori.model.util.IPalvelupiste;

import java.util.Map;

public interface IMoottori {
	void setSimulointiaika(double aika);
	void setViive(long viive);
	long getViive();
	Tulos getTulos();
	Map<Integer, IPalvelupiste> getPalvelupisteet();
}
