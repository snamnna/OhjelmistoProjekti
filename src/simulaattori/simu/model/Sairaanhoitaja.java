package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;

import java.util.concurrent.ThreadLocalRandom;

public class Sairaanhoitaja extends Palvelupiste {

    private static int arrivals;

    public Sairaanhoitaja(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        jakauma = new Normal(0.5, 0.5);
        arrivals = 0;
    }

    @Override
    public void addArrival() {
        arrivals++;
    }

    @Override
    public int getArrivals() {
        return arrivals;
    }

    // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
    public void aloitaPalvelu() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        varattu = true;
        double palveluaika = generator.sample();
        // arvo meneekö erikoislääkärille vai yleislääkärille
        Tapahtuma tapahtuma = new Tapahtuma(random.nextBoolean() ? TapahtumanTyyppi.YLARR : TapahtumanTyyppi.ELARR,
                Kello.getInstance().getAika() + palveluaika, this);
        tapahtumalista.lisaa(tapahtuma);
        viimeisinLuotuTapahtuma = tapahtuma;
        addPalveluAikaToSumma(palveluaika);

    }
}
