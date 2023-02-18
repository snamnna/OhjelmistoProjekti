package simu.model;

import java.util.LinkedList;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.model.util.IPalvelupiste;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public abstract class Palvelupiste implements IPalvelupiste {

	protected LinkedList<Asiakas> jono; // Tietorakennetoteutus

	protected final ContinuousGenerator generator;
	protected final Tapahtumalista tapahtumalista;
	protected final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	private final static int INITIAL_TAPAHTUMA_ID = -1;

	protected Tapahtuma viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.ELLABARR, 0, INITIAL_TAPAHTUMA_ID);

	protected final int ID;

	private static int seuraavaID = 1;

	ContinuousGenerator jakauma;

	// JonoStartegia strategia; //optio: asiakkaiden järjestys

	protected boolean varattu = false;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		ID = getSeuraavaID();
		jono = new LinkedList<>();
	}

	private int getSeuraavaID() {
		return seuraavaID++;
	}

	public int getID() {
		return ID;
	}

	public void lisaaJonoon(Asiakas a) { // Jonon 1. asiakas aina palvelussa
		jono.add(a);
	}

	public Asiakas otaJonosta() { // Poistetaan palvelussa ollut
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

}
