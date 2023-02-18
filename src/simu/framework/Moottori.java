package simu.framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.IKontrolleriVtoM;
import simu.model.util.IPalvelupiste;

public abstract class Moottori extends Thread implements IMoottori {

	private double simulointiaika = 0;
	private long viive = 0;

	private Kello kello;

	protected Tapahtumalista tapahtumalista;
//	protected IPalvelupiste[] palvelupisteet;
	protected Map<String, Map<Integer, IPalvelupiste>> palvelupisteet;
	protected IKontrolleriVtoM kontrolleri;

	public Moottori(IKontrolleriVtoM kontrolleri) {
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
		for (IPalvelupiste p : palvelupisteet) {
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