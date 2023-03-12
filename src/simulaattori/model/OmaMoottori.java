package simulaattori.model;

import eduni.distributions.Negexp;
import entity.Tulos;
import simulaattori.controller.IKontrolleriVtoM;
import simulaattori.framework.*;
import simulaattori.model.util.FPalvelupiste;
import simulaattori.model.util.IPalvelupiste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OmaMoottori extends Moottori {

	private final Saapumisprosessi saapumisprosessi;
	private Map<TapahtumanTyyppi, List<IPalvelupiste>> tyyppiToPalveluPMap;
	private Tulos tulos;
	private Reititin reititin;

	public OmaMoottori(IKontrolleriVtoM kontrolleri) {
		super(kontrolleri);
		saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);
		tyyppiToPalveluPMap = new HashMap<>();
		tulos = new Tulos();
		alustukset();
	}

	@Override
	protected void alustukset() {
		Trace.setTraceLevel(Trace.Level.ERR);
		Kello.getInstance().setAika(0);
		Asiakas.reset();
		// key on palvelupisteen tyyppi.
		// arvona tietyn tyyppisten palvelupisteiden lukumäärä
		Map<TapahtumanTyyppi, Integer> pPisteet = kontrolleri.haePalvelupisteet();

		// palvelupisteiden alustus
		for (TapahtumanTyyppi tapahtumanTyyppi : pPisteet.keySet()) {
			TapahtumanTyyppi arrival = tapahtumanTyyppi;
			TapahtumanTyyppi departure = getTapahtumanTyyppi(tapahtumanTyyppi, false);

			// departure viittaa samaan listaan ku sitä vastaava arrival, esim YLDEP ja YLARR
			tyyppiToPalveluPMap.putIfAbsent(arrival, new ArrayList<>());
			tyyppiToPalveluPMap.putIfAbsent(departure, tyyppiToPalveluPMap.get(arrival));

			// alustetaan palvelupisteet
			for (int i = 0; i < pPisteet.get(tapahtumanTyyppi); i++) {
				IPalvelupiste palvelupiste = FPalvelupiste.createPalvelupiste(arrival, tapahtumalista);
				palvelupisteet.putIfAbsent(palvelupiste.getID(), palvelupiste);

				// ei tarvitse lisätä myös tyyppiToPalveluPMap, koska se "osoittaa" jo valmiiksi samaan listaan
				tyyppiToPalveluPMap.get(arrival).add(palvelupiste);
			}
		}

		// alustetaan reititin
		reititin = new Reititin(tyyppiToPalveluPMap);
		// Ensimmäinen saapuminen järjestelmään
		saapumisprosessi.generoiSeuraava();
	}

	@Override
	protected void suoritaTapahtuma(Tapahtuma t) { // B-vaiheen tapahtumat
		TapahtumanTyyppi tyyppi = t.getTyyppi();
		IPalvelupiste seuraavaPalvelupiste = reititin.haeSeuraavaPalvelupiste(tyyppi);
		if (tyyppi == TapahtumanTyyppi.ARR) {
			seuraavaPalvelupiste.lisaaJonoon(new Asiakas());
			saapumisprosessi.generoiSeuraava();
		} else {
			IPalvelupiste palvelupiste = t.getPalvelupiste();
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

	protected TapahtumanTyyppi getTapahtumanTyyppi(TapahtumanTyyppi tyyppi, Boolean arrival) {
		TapahtumanTyyppi tTyyppi = TapahtumanTyyppi.ARR;
		switch (tyyppi) {
			case ARR -> tTyyppi = TapahtumanTyyppi.ARR;
			case YLARR -> tTyyppi = arrival ? TapahtumanTyyppi.YLARR : TapahtumanTyyppi.YLDEP;
			case ELARR -> tTyyppi = arrival ? TapahtumanTyyppi.ELARR : TapahtumanTyyppi.ELDEP;
			case LABRA_ARRIVAL -> tTyyppi = arrival ? TapahtumanTyyppi.LABRA_ARRIVAL : TapahtumanTyyppi.LABRA_DEPARTURE;
		}
		return tTyyppi;
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
		double averageResponseTime = Asiakas.getTotalWaitingTime() / completedCount;
		double averageWaitingTime = Asiakas.getTotalWaitingTime() / loppuAika;

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
		// ilmoitetaan UIlle simulaation päättymisestä
		kontrolleri.simulointiPaattyi();
	}
	
	public Tulos getTulos() {
		return tulos;
	}

	@Override
	public Map<TapahtumanTyyppi, List<IPalvelupiste>> getPalvelupisteet() {
		return tyyppiToPalveluPMap;
	}
}
