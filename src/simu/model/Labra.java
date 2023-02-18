package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.model.util.IPalvelupiste;

import java.util.Map;
import java.util.Random;

import static simu.model.OmaMoottori.*;

public class Labra extends Palvelupiste {
	public Labra(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
	}

	@Override
	public void aloitaPalvelu() {
		Random random = new Random();
		Asiakas asiakas = jono.peek();
		// arvo palveluaika
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + asiakas.getId());
		varattu = true;
		double palveluaika = generator.sample();

		// luo tapahtuma, joka lisätään tapahtumalistaan
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.LABRA_DEPARTURE,
				Kello.getInstance().getAika() + palveluaika, this.ID);
		tapahtumalista.lisaa(tapahtuma);

		// arvotaan vielä, meneekö asiakas kotiin vai yleislääkärille
		if (random.nextBoolean()) {
			if (random.nextBoolean())
				viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.ELARR,
						Kello.getInstance().getAika() + palveluaika, this.ID);
			else
				viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.YLARR,
						Kello.getInstance().getAika() + palveluaika, this.ID);
		} else
			viimeisinLuotuTapahtuma = tapahtuma;

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
		case LABRA_DEPARTURE -> handleDeparture(tapahtuma, palvelupisteet);
		default -> throw new IllegalArgumentException("Unexpected value: " + tapahtuma.getTyyppi());
		}
	}

	private void handleDeparture(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet) {
		// meneekö kotiin, erikoislääkärille vai yleislääkärille
		switch (viimeisinLuotuTapahtuma.getTyyppi()) {
		case YLARR -> {
			for (int i = 1; i < palvelupisteet.size() + 1; i++) {
				if (palvelupisteet.get(i).getSkeduloitavanTapahtumanTyyppi() == TapahtumanTyyppi.YLARR) {
					palvelupisteet.get(i).lisaaJonoon(otaJonosta());
					break;
				}
			}
		}

		case ELARR -> {
			for (int i = 1; i < palvelupisteet.size() + 1; i++) {
				if (palvelupisteet.get(i).getSkeduloitavanTapahtumanTyyppi() == TapahtumanTyyppi.ELARR) {
					palvelupisteet.get(i).lisaaJonoon(otaJonosta());
					break;
				}
			}
		}

		case LABRA_DEPARTURE -> {
			Asiakas asiakas = otaJonosta();
			asiakas.setPoistumisaika(Kello.getInstance().getAika());
			asiakas.raportti();
			break;
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + viimeisinLuotuTapahtuma.getTyyppi());
		}
	}

	public String getJonoString() {
		return jono.toString() + "\n labra";
	}
}
