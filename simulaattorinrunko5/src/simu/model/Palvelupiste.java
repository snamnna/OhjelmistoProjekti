package simu.model;

import java.util.LinkedList;
import java.util.Random;

import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;
	private boolean labraKayty = false;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista) {
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.YLARR;
		labraKayty = true;
	}

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi alkperTyyppi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		//jos alkpertyyppi on arrival eli sairaanhoitajalla
		if(alkperTyyppi == TapahtumanTyyppi.ARR) {
			//ARVOTAAN JOKO ERIKOISLÄÄKÄRI = 1 TAI YLEISLÄÄKÄRI = 2
			Random rand = new Random();
			int randomInt = rand.nextInt(2)+1;
			if(randomInt == 1) {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.ELARR;
			}
			else {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.YLARR;
			}
		//tyyppi on erikoislääkäri
		}else if (alkperTyyppi == TapahtumanTyyppi.ELARR ) {
			//ARVOTAAN JOKO Labra = 1 TAI poistuminen = 2
			Random rand = new Random();
			int randomInt = rand.nextInt(2)+1;
			
			if(randomInt == 1) {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.ELLABARR;
			}
			else {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.ELDEP;
			}
		}
		//tyyppi on yleislääkäri
		else if (alkperTyyppi == TapahtumanTyyppi.YLARR ) {
			//ARVOTAAN JOKO Labra = 1 TAI poistuminen = 2
			Random rand = new Random();
			int randomInt = rand.nextInt(2)+1;
			if(randomInt == 1) {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.YLLABARR;
			}
			else {
				skeduloitavanTapahtumanTyyppi = TapahtumanTyyppi.YLDEP;
			}
		}

	}

	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		varattu = false;
		return jono.poll();
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		
		varattu = true;
		double palveluaika = generator.sample();
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}


	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}

}