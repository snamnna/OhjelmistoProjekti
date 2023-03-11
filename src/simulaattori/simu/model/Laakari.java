package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;

import java.util.concurrent.ThreadLocalRandom;

public class Laakari extends Palvelupiste {
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;

	public Laakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
		skeduloitavanTapahtumanTyyppi = getSkeduloitavanTapahtumanTyyppi();
	}

	private TapahtumanTyyppi getSkeduloitavanTapahtumanTyyppi() {
		if (tyyppi == TapahtumanTyyppi.YLARR) {
			return TapahtumanTyyppi.YLDEP;
		} else if (tyyppi == TapahtumanTyyppi.ELARR) {
			return TapahtumanTyyppi.ELDEP;
		} else {
			return null;
		}
	}

	@Override
	public void addArrival() {

	}

	@Override
	public int getArrivals() {
		return 0;
	}

	@Override
	public void aloitaPalvelu() {
		ThreadLocalRandom random = ThreadLocalRandom.current();

		// arvo palveluaika
		varattu = true;
		double palveluaika = generator.sample();
		// Jakauma sille, että meneekö labraan vai kotiin
		// luo tapahtuma, joka lisätään tapahtumalistaan
		Tapahtuma tapahtuma = new Tapahtuma(skeduloitavanTapahtumanTyyppi, Kello.getInstance().getAika() + palveluaika,
				this);
		if (random.nextBoolean() && !jono.peek().getLabrakaynti()) {
			tapahtuma.setTyyppi(TapahtumanTyyppi.LABRA_ARRIVAL);
		}
		tapahtumalista.lisaa(tapahtuma);
		viimeisinLuotuTapahtuma = tapahtuma;
		addPalveluAikaToSumma(palveluaika);
	}
}
