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

public class ELaakari extends Palvelupiste {

	public ELaakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
	}

	@Override
	public void aloitaPalvelu() {
		Random random = new Random();
		// arvo palveluaika
		Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
		varattu = true;
		double palveluaika = generator.sample();

		// Jakauma sille, että meneekö labraan vai kotiin
		// luo tapahtuma, joka lisätään tapahtumalistaan
		Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.ELDEP, Kello.getInstance().getAika() + palveluaika,
				this.ID);
		if (random.nextBoolean() && !jono.peek().getLabrakaynti()) {
			tapahtuma.setTyyppi(TapahtumanTyyppi.LABRA_ARRIVAL);
		}
		tapahtumalista.lisaa(tapahtuma);
		viimeisinLuotuTapahtuma = tapahtuma;
	}

	@Override
	public void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet) {
		Asiakas asiakas = palvelupisteet.get(tapahtuma.getPalvelupisteID()).otaJonosta();
		switch (tapahtuma.getTyyppi()) {
		case ELARR -> lisaaJonoon(asiakas);
		case ELDEP -> {
			asiakas.setPoistumisaika(Kello.getInstance().getAika());
			asiakas.raportti();
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + tapahtuma.getTyyppi());
		}
	}

	public String getJonoString() {
		return jono.toString() + "\n elaakari";
	}
}
