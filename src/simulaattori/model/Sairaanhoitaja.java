package simulaattori.model;

import eduni.distributions.ContinuousGenerator;
import simulaattori.framework.Kello;
import simulaattori.framework.Tapahtuma;
import simulaattori.framework.Tapahtumalista;

import java.util.concurrent.ThreadLocalRandom;

public class Sairaanhoitaja extends Palvelupiste {

    private static int arrivals;

    public Sairaanhoitaja(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        arrivals = 0;
    }

    @Override
    public void addArrival() {
        arrivals++;
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
        addPalveluAikaToSumma(palveluaika);
        palveltuCount++;
        palveluAikaSumma += palveluaika;
        keskimaarainenPalveluAika = palveluAikaSumma / palveltuCount;

    }
}
