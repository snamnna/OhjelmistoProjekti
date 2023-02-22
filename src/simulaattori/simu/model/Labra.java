package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;
import simulaattori.simu.framework.Trace;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Labra extends Palvelupiste {
	public Labra(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
	}

	@Override
	public void aloitaPalvelu() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		Asiakas asiakas = jono.peek();
		// arvo palveluaika
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + asiakas.getId());
		varattu = true;
		double palveluaika = generator.sample();

		// luo tapahtuma, joka lisätään tapahtumalistaan
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.LABRA_DEPARTURE,
				Kello.getInstance().getAika() + palveluaika, this.ID);

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
	}

	@Override
	public void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet) {
		switch (tapahtuma.getTyyppi()) {
		// tarkistetaan kumman palvelupisteen viimeisin tapahtuma on
		case LABRA_ARRIVAL -> {
			if (palvelupisteet.get(tapahtuma.getPalvelupisteID()).getViimeisinTapahtuma().equals(tapahtuma)) {
				Asiakas asiakas = palvelupisteet.get(tapahtuma.getPalvelupisteID()).otaJonosta();
				lisaaJonoon(asiakas);
			}
		}
		case LABRA_DEPARTURE -> {
			Asiakas asiakas = otaJonosta();
			asiakas.setPoistumisaika(Kello.getInstance().getAika());
			asiakas.raportti();
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + tapahtuma.getTyyppi());
		}
	}

	public String getJonoString() {
		return jono.toString() + "\n labra";
	}
}

