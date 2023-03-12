package simulaattori.framework;

import simulaattori.controller.IKontrolleriMtoV;
import simulaattori.model.util.IPalvelupiste;

import java.util.HashMap;
import java.util.Map;

public abstract class Moottori extends Thread implements IMoottori {

	private double simulointiaika = 0;
	private long viive = 0;

	private Kello kello;

	protected Tapahtumalista tapahtumalista;
	protected Map<Integer, IPalvelupiste> palvelupisteet;
	protected IKontrolleriMtoV kontrolleri;

	public Moottori(IKontrolleriMtoV kontrolleri) {
		this.kontrolleri = kontrolleri;
		kello = Kello.getInstance();
		tapahtumalista = new Tapahtumalista();
		palvelupisteet = new HashMap<>();
	}

	public void setSimulointiaika(double aika) {
		simulointiaika = aika;
	}

	public void run() {
		alustukset();
		while (simuloidaan()) {
			viive();
			kello.setAika(nykyaika());
			suoritaBTapahtumat();
			yritaCTapahtumat();
		}
		tulokset();
	}

	private void suoritaBTapahtumat() {
		while (tapahtumalista.getSeuraavanAika() == kello.getAika()) {
			suoritaTapahtuma(tapahtumalista.poista());
		}
	}

	private void yritaCTapahtumat() {
		for (Integer key : palvelupisteet.keySet()) {
			IPalvelupiste p = palvelupisteet.get(key);
			if (!p.onVarattu() && p.onJonossa()) {
				p.aloitaPalvelu();
			}
		}
	}

	private double nykyaika() {
		return tapahtumalista.getSeuraavanAika();
	}

	private boolean simuloidaan() {
		return kello.getAika() < simulointiaika;
	}

	private void viive() {
		Trace.out(Trace.Level.INFO, "Viive: " + viive);
		try {
			sleep(viive);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public long getViive() {
		return viive;
	}

	public void setViive(long viive) {
		this.viive = viive;
	}

	protected abstract void alustukset();

	protected abstract void suoritaTapahtuma(Tapahtuma t);

	protected abstract void tulokset();
}