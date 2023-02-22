package simulaattori.simu.framework;

public interface IMoottori {
	// Kontrolleri käyttää tätä rajapintaa
	public void setSimulointiaika(double aika);
	public void setLabJakauma(); //asetettava jakauma
	public void setYlaakarienLkm(); //asetettavat palvelupisteet
	public void setElaakarienLkm();
	public void setViive(long viive);
	public long getViive();
}
