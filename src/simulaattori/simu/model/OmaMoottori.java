package simulaattori.simu.model;

import eduni.distributions.Negexp;
import entity.Tulos;
import simulaattori.controller.IKontrolleriVtoM;
import simulaattori.simu.framework.*;
import simulaattori.simu.model.util.FPalvelupiste;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OmaMoottori extends Moottori {

	private final Saapumisprosessi saapumisprosessi;
	private Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap;
	private Tulos tulos;
	int count;
	private Reititin reititin;

	public OmaMoottori(IKontrolleriVtoM kontrolleri) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
		tyyppiToPalveluPMap = new HashMap<>();
		tulos = new Tulos();
		reititin = new Reititin();
	}

	@Override
	protected void alustukset() {
		Trace.setTraceLevel(Trace.Level.ERR);
		Kello.getInstance().setAika(0);
		Asiakas.reset();
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

		// viedään alustetut palvelupisteet UI:lle visualisointia varten
		kontrolleri.setPalvelupisteet(tyyppiToPalveluPMap);
		// alustetaan reititin
		reititin.alustaReititin(tyyppiToPalveluPMap);
		// Ensimmäinen saapuminen järjestelmään
		saapumisprosessi.generoiSeuraava();
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		TapahtumanTyyppi tyyppi = t.getTyyppi();
		if (tyyppi == TapahtumanTyyppi.ARR) {
			IPalvelupiste sairaanhoitaja = reititin.haeVapaaPalvelupiste(tyyppi);
			sairaanhoitaja.lisaaJonoon(new Asiakas());
			saapumisprosessi.generoiSeuraava();
		} else {
			IPalvelupiste palvelupiste = t.getPalvelupiste();
			IPalvelupiste seuraavaPalvelupiste = reititin.haeSeuraavaPalvelupiste(palvelupiste, tyyppi);

			Asiakas asiakas = palvelupiste.otaJonosta();
			if (seuraavaPalvelupiste != null) {
				seuraavaPalvelupiste.lisaaJonoon(asiakas);
				seuraavaPalvelupiste.addArrival();
			} else {
				palvelupiste.addDeparture();
				asiakas.setPoistumisaika(Kello.getInstance().getAika());
				asiakas.raportti();
			}
		}
		kontrolleri.visualisoi();
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
		double busyTime = tyyppiToPalveluPMap.get(TapahtumanTyyppi.ARR).get(0).getKaikkienPalveluAikojenSumma() / palvelupisteet.values().size();

		int completedCount = tyyppiToPalveluPMap.get(TapahtumanTyyppi.ARR).get(0).getDepartureLkm();

		double throughput = completedCount / loppuAika;
		double utilization = busyTime / loppuAika;
		double serviceTime = Asiakas.getTotalWaitingTime() / completedCount;
		int labraArrivals = Labra.getLabraArrivalCount();
		double averageResponseTime = 5;
		double averageWaitingTime = Asiakas.getTotalWaitingTime() / completedCount;

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
		tulos.setAverageResponseTime(averageResponseTime);
		tulos.setAverageWaitingTime(averageWaitingTime);
		
		kontrolleri.visualisoi();
		// ilmoitetaan UIlle simulaation päättymisestä
		kontrolleri.simulointiPaattyi();
	}
	
	public Tulos getTulos() {
		return tulos;
	}

	@Override
	public Map<Integer, IPalvelupiste> getPalvelupisteet() {
		return palvelupisteet;
	}
}
