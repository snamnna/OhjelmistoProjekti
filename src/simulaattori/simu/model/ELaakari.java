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

public class ELaakari extends Palvelupiste {
	
	private static double kokPalveluaika;

	public ELaakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
		super(generator, tapahtumalista, tyyppi);
		jakauma = new Normal(0.5, 0.5);
	}

	@Override
	public void aloitaPalvelu() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
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
		kokPalveluaika =+ palveluaika;
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
	
	public static double getKokPalveluaika() {
		return kokPalveluaika;
	}
}
