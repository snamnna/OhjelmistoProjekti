package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;

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
}

