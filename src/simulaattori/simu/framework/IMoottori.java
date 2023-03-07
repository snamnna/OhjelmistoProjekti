package simulaattori.simu.framework;

import entity.Tulos;

public interface IMoottori {
	// Kontrolleri käyttää tätä rajapintaa
	void setSimulointiaika(double aika);
	void setViive(long viive);
	long getViive();
	Tulos getTulos();
}
