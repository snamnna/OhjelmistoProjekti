package simu.framework;

public interface IMoottori {
	// Kontrolleri käyttää tätä rajapintaa
	public void setSimulointiaika(double aika);
	public double setLabJakauma(); //asetettava jakauma
	public void setYlaakarienLkm(); //asetettavat palvelupisteet
	public void setElaakarienLkm();
	//tarvitaanko muita?
}
