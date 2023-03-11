package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.LinkedList;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public abstract class Palvelupiste implements IPalvelupiste {

	protected LinkedList<Asiakas> jono; // Tietorakennetoteutus
	protected final ContinuousGenerator generator;
	protected final Tapahtumalista tapahtumalista;
	protected final TapahtumanTyyppi tyyppi;
	protected Tapahtuma viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.ELLABARR, 0);
	protected final int ID;
	private static int seuraavaID = 1;
	ContinuousGenerator jakauma;

	protected boolean varattu = false;
	protected static int departures;
	
	private static double kaikkienPalveluAikaSumma;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.tyyppi = tyyppi;
		ID = getSeuraavaID();
		jono = new LinkedList<>();
		departures = 0;
		kaikkienPalveluAikaSumma = 0;
	}

	protected void addPalveluAikaToSumma(double aika) {
		kaikkienPalveluAikaSumma += aika;
	}

	public double getKaikkienPalveluAikojenSumma() {
		return kaikkienPalveluAikaSumma;
	}

	public TapahtumanTyyppi getTyyppi() {
		return tyyppi;
	}

	private int getSeuraavaID() {
		return seuraavaID++;
	}

	public int getID() {
		return ID;
	}

	public void lisaaJonoon(Asiakas a) {
		jono.add(a);
	}

	public Asiakas otaJonosta() {
		varattu = false;
		return jono.poll();
	}

	public boolean onVarattu() {
		return varattu;
	}

	public boolean onJonossa() {
		return !jono.isEmpty();
	}

	public Tapahtuma getViimeisinTapahtuma() {
		return viimeisinLuotuTapahtuma;
	}

	public int getDepartureLkm() {
		return departures;
	}

	public void addDeparture() {
		departures++;
	}

	public abstract void addArrival();

	public abstract int getArrivals();

	public int getAsiakasMaaraJonossa() {
		return jono.size();
	}
}
