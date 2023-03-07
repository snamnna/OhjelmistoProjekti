package simulaattori.simu.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import eduni.distributions.Negexp;
import entity.Tulos;
import simulaattori.controller.IKontrolleriVtoM;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Moottori;
import simulaattori.simu.framework.Saapumisprosessi;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Trace;
import simulaattori.simu.model.util.FPalvelupiste;
import simulaattori.simu.model.util.IPalvelupiste;

public class OmaMoottori extends Moottori {

	private final Saapumisprosessi saapumisprosessi;
	private Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap;
	private Tulos tulos;
	
	public OmaMoottori(IKontrolleriVtoM kontrolleri) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
		tyyppiToPalveluPMap = new HashMap<>();
		tulos = new Tulos();
	}

	@Override
	protected void alustukset() {
		Trace.setTraceLevel(Trace.Level.INFO);
		// key on palvelupisteen tyyppi.
		// arvona tietyn tyyppisten palvelupisteiden lukumäärä
		Map<String, Integer> pPisteet = kontrolleri.haePalvelupisteet();

		// palvelupisteiden alustus
		for (String key : pPisteet.keySet()) {
			TapahtumanTyyppi arrival = getTapahtumanTyyppi(key);
			TapahtumanTyyppi departure = getTapahtumanTyyppi(key, false);
			
			// departure viittaa samaan listaan ku sitä vastaava arrival, esim YLDEP ja YLARR
			tyyppiToPalveluPMap.putIfAbsent(arrival, new ArrayList<>());
			tyyppiToPalveluPMap.putIfAbsent(departure, tyyppiToPalveluPMap.get(arrival));

			// alustetaan palvelupisteet
			for (int i = 0; i < pPisteet.get(key); i++) {
				IPalvelupiste palvelupiste = FPalvelupiste.createPalvelupiste(arrival, tapahtumalista);
				palvelupisteet.putIfAbsent(palvelupiste.getID(), palvelupiste);

				// ei tarvitse lisätä myös tyyppiToPalveluPMap, koska se "osoittaa" jo valmiiksi samaan listaan
				tyyppiToPalveluPMap.get(arrival).add(palvelupiste);
			}
		}
		// Ensimmäinen saapuminen järjestelmään
		saapumisprosessi.generoiSeuraava(); 
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		TapahtumanTyyppi tyyppi = t.getTyyppi();

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
		if (tyyppi == TapahtumanTyyppi.ARR) {
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

	@Override
	protected void tulokset() {
		int sairaanhoitajaLkm = tyyppiToPalveluPMap.get(TapahtumanTyyppi.ARR).size();
		int yLaakariLkm = tyyppiToPalveluPMap.get(TapahtumanTyyppi.YLARR).size();
		int eLaakariLkm = tyyppiToPalveluPMap.get(TapahtumanTyyppi.ELARR).size();
		int labraLkm = tyyppiToPalveluPMap.get(TapahtumanTyyppi.LABRA_ARRIVAL).size();
		int arrivalCount = Asiakas.getAsiakasCount();
		double loppuAika = Kello.getInstance().getAika();
		double busyTime = tyyppiToPalveluPMap.get(TapahtumanTyyppi.ARR).get(0).getKaikkienPalveluAikojenSumma();
		
		int completedCount = 0;
		for(IPalvelupiste palvelupiste : palvelupisteet.values()) {
			completedCount += palvelupiste.getDepartureLkm();
		}
		
		double throughput = completedCount / loppuAika;
		double utilization = busyTime / loppuAika;
		double serviceTime = busyTime / completedCount;
		int labraArrivals = Labra.getLabraArrivalCount();
		double waitingTime = Asiakas.getWaitingTime();
		double averageResponseTime = waitingTime / completedCount;
		double averageWaitingTime = waitingTime / loppuAika;
		
		tulos.setSairaanhoitajat(sairaanhoitajaLkm);
		tulos.setYleislääkärit(yLaakariLkm);
		tulos.setErikoislääkärit(eLaakariLkm);
		tulos.setLabrat(labraLkm);
		tulos.setArrivalCount(arrivalCount);
		tulos.setSimTime(loppuAika);
		tulos.setBusyTime(busyTime);
		tulos.setCompletedCount(completedCount);
		tulos.setThroughput(throughput);
		tulos.setUtilization(utilization);
		tulos.setServiceTime(serviceTime);
		tulos.setLabraArrivalit(labraArrivals);
		tulos.setWaitingTime(waitingTime);
		tulos.setAverageResponseTime(averageResponseTime);
		tulos.setAverageWaitingTime(averageWaitingTime);
		
		
		// ilmoitetaan UIlle simulaation päättymisestä
		kontrolleri.simulointiPaattyi();
	}
	
	public Tulos getTulos() {
		return tulos;
	}
}
