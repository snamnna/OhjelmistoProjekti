package simu.framework;

public interface IMoottori {
	// Kontrolleri käyttää tätä rajapintaa
	public void setSimulointiaika(double aika);
	protected void setLabJakauma(); //asetettava jakauma
	public void setYlaakarienLkm(); //asetettavat palvelupisteet
	public void setElaakarienLkm();
	//tarvitaanko muita?
	public void setViive(long viive);
}
