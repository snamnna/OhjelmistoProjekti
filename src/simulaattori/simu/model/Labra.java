package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Labra extends Palvelupiste {
	private static int labraArrivals;

	public static int getLabraArrivalCount() {
		return labraArrivals;
	}

	public Labra(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
		labraArrivals = 0;
	}

	@Override
	public void addArrival() {
		labraArrivals++;
	}

	@Override
	public int getArrivals() {
		return labraArrivals;
	}

	@Override
	public void aloitaPalvelu() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Asiakas asiakas = jono.peek();
		// arvo palveluaika
		varattu = true;
		double palveluaika = generator.sample();

		// luo tapahtuma, joka lisätään tapahtumalistaan
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.LABRA_DEPARTURE,
				Kello.getInstance().getAika() + palveluaika, this);

		// arvotaan vielä, meneekö asiakas kotiin vai yleislääkärille
		if (random.nextBoolean()) {
			if (random.nextBoolean())
				tapahtuma.setTyyppi(TapahtumanTyyppi.ELARR);
			else
				tapahtuma.setTyyppi(TapahtumanTyyppi.YLARR);
		}
		viimeisinLuotuTapahtuma = tapahtuma;
		tapahtumalista.lisaa(tapahtuma);
		jono.peek().setLabrakaynti(true);
		addPalveluAikaToSumma(palveluaika);
	}

	@Override
	public void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet) {
//		switch (tapahtuma.getTyyppi()) {
//		// tarkistetaan kumman palvelupisteen viimeisin tapahtuma on
//		case LABRA_ARRIVAL -> {
//			if (palvelupisteet.get(tapahtuma.getPalvelupisteID()).getViimeisinTapahtuma().equals(tapahtuma)) {
//				Asiakas asiakas = palvelupisteet.get(tapahtuma.getPalvelupisteID()).otaJonosta();
//				lisaaJonoon(asiakas);
//				labraArrivals++;
//			}
//		}
//		case LABRA_DEPARTURE -> {
//			Asiakas asiakas = otaJonosta();
//			asiakas.setPoistumisaika(Kello.getInstance().getAika());
//			asiakas.raportti();
//			departures++;
//		}
//		default -> throw new IllegalArgumentException("Unexpected value: " + tapahtuma.getTyyppi());
//		}
	}

	public String getJonoString() {
		return jono.toString() + "\n labra";
	}
}

