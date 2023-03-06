package simulaattori.simu.model;

import eduni.distributions.Negexp;
import entity.Tulos;
import simulaattori.simu.framework.*;
import simulaattori.simu.model.util.FPalvelupiste;
import simulaattori.simu.model.util.IPalvelupiste;
import simulaattori.view.TuloksetController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import simulaattori.MainApp;
import simulaattori.controller.IKontrolleriVtoM;

public class OmaMoottori extends Moottori {

	private final Saapumisprosessi saapumisprosessi;
	private Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap;
	private Boolean test = false;
	
	//luodaan tulos-olio ja departuret muuttuja tuloksien antamista varten
	private Tulos tulokset = new Tulos();
	public static int departuret;
	public static int labrat;

	public OmaMoottori(IKontrolleriVtoM kontrolleri) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
		tyyppiToPalveluPMap = new HashMap<>();
	}

	public OmaMoottori(IKontrolleriVtoM kontrolleri, Boolean testi) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
		tyyppiToPalveluPMap = new HashMap<>();
		this.test = testi;
	}

	@Override
	protected void alustukset() {
		Trace.setTraceLevel(Trace.Level.INFO);
		// key on palvelupisteen tyyppi.
		// arvona tietyn tyyppisten palvelupisteiden lukumäärä
		Map<String, Integer> pPisteet = test ? kontrolleri.haePalvelupisteetTest() : kontrolleri.haePalvelupisteet();

		// palvelupisteiden alustus
		for (String key : pPisteet.keySet()) {
			TapahtumanTyyppi tArrival = getTapahtumanTyyppi(key);
			TapahtumanTyyppi tDeparture = getTapahtumanTyyppi(key, false);
			tyyppiToPalveluPMap.putIfAbsent(tArrival, new ArrayList<>());
			// departure viittaa samaan listaan ku sitä vastaava arrival, esim YLDEP ja
			// YLARR
			tyyppiToPalveluPMap.putIfAbsent(tDeparture, tyyppiToPalveluPMap.get(tArrival));

			// alustetaan tyypin palvelupisteet
			for (int i = 0; i < pPisteet.get(key); i++) {
				IPalvelupiste palvelupiste = FPalvelupiste.createPalvelupiste(tArrival, tapahtumalista);
				palvelupisteet.putIfAbsent(palvelupiste.getID(), palvelupiste);

				// ei tarvitse lisätä myös tyyppiToPalveluPMap, koska se "osoittaa" jo valmiiksi
				// samaan listaan
				tyyppiToPalveluPMap.get(tArrival).add(palvelupiste);
			}
		}

		saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		TapahtumanTyyppi tyyppi = t.getTyyppi();
		
		//otetaan lasku departure tyyppisistä tapahtumista tuloksia varten
		if(tyyppi == TapahtumanTyyppi.ELDEP || tyyppi == TapahtumanTyyppi.LABRA_DEPARTURE || tyyppi == TapahtumanTyyppi.YLDEP) {
			departuret++;
		}
		//Lasketaan myös labrakäynnit tuloksia varten
		if(tyyppi == TapahtumanTyyppi.LABRA_ARRIVAL) {
			labrat++;
		}
		for (IPalvelupiste p : palvelupisteet.values()) {
			System.out.println(p.getJonoString());
		}
		List<IPalvelupiste> pisteet = tyyppiToPalveluPMap.get(tyyppi);

		// tällä hetkellä hakee randomisti vaan jonku tapahtumatyyppiä vastaavan
		// palvelupisteen
		// pitää lisätä switch() jos halutaan erilaisia kriteerejä miten se palvelupiste
		// valitaan eri tyypeille
		// jos halutaan vaan että hakee palvelupisteen jolla on vähiten jonoa tai on
		// vapaa niin implementoidaan se tähän tilalle
		int rndIndex = ThreadLocalRandom.current().nextInt(0, pisteet.size());
		pisteet.get(rndIndex).siirraAsiakas(t, palvelupisteet);

		// jos asiakas saapuu ekaa kertaa simulaattoriin eli sen tapahtumatyyppi on ARR
		// generoidaan seuraava saapuminen ja kutsutaan kontrollerin metodia, joka
		// hoitaa visualisoinnin
		if (onArrival(tyyppi)) {
			saapumisprosessi.generoiSeuraava();
			kontrolleri.visualisoiAsiakas();
		}
	}

	protected TapahtumanTyyppi getTapahtumanTyyppi(String tyyppi, Boolean arrival) {
		TapahtumanTyyppi tTyyppi = TapahtumanTyyppi.ARR;
		switch (tyyppi) {
		case "Sairaanhoitaja" -> tTyyppi = TapahtumanTyyppi.ARR;
		case "YLaakari" -> tTyyppi = arrival ? TapahtumanTyyppi.YLARR : TapahtumanTyyppi.YLDEP;
		case "ELaakari" -> tTyyppi = arrival ? TapahtumanTyyppi.ELARR : TapahtumanTyyppi.ELDEP;
		case "Labra" -> tTyyppi = arrival ? TapahtumanTyyppi.LABRA_ARRIVAL : TapahtumanTyyppi.LABRA_DEPARTURE;
		}
		return tTyyppi;
	}

	protected TapahtumanTyyppi getTapahtumanTyyppi(String tyyppi) {
		return getTapahtumanTyyppi(tyyppi, true);
	}

	private Boolean onArrival(TapahtumanTyyppi tyyppi) {
		return tyyppi == TapahtumanTyyppi.ARR;
	}

	@Override
	protected void tulokset() {
		//Nää printataan? en saa muokattuu kuitenkaan ja vaik kommentois pois näiden printtaukset näkyy, en tajuu :DD -Tuiksu
//		System.out.println("Simuloinnin kokonaisaika " + tulokset.getKokonaisaika());
//		System.out.println("Saapuneet asiakkaat: " + tulokset.getArrivalCount());
//		System.out.println("Palveltujen asiakkaiden määrä: " + tulokset.getCompletedCount());
//		System.out.println("Kokonais busy time " + tulokset.getBusyTime());
		Tulos tulokset = new Tulos();
		kontrolleri.setTulokset();
	}
	//deprature tapahtumien määrä tulos-luokalle.
	public int getDeparturet() {
		return departuret;
	}

	public void setLabJakauma() {
		// TODO Auto-generated method stub

	}

	public void setElaakarienLkm() {
		// TODO Auto-generated method stub

	}

	public void setYlaakarienLkm() {
		// TODO Auto-generated method stub

	}
}
