package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Tapahtuma;
import simulaattori.simu.framework.Tapahtumalista;

import java.util.concurrent.ThreadLocalRandom;

public class Labra extends Palvelupiste {
    private static int labraArrivals;

    public Labra(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        labraArrivals = 0;
    }

    public static int getLabraArrivalCount() {
        return labraArrivals;
    }

    @Override
    public void addArrival() {
        labraArrivals++;
    }

    @Override
    public void aloitaPalvelu() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // arvo palveluaika
        varattu = true;
        double palveluaika = generator.sample();

        // luo tapahtuma, joka lisätään tapahtumalistaan
        Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.LABRA_DEPARTURE,
                Kello.getInstance().getAika() + palveluaika, this);

        // kotiin vai lääkärille
        if (random.nextBoolean()) {
            if (random.nextBoolean())
                tapahtuma.setTyyppi(TapahtumanTyyppi.ELARR);
            else
                tapahtuma.setTyyppi(TapahtumanTyyppi.YLARR);
        }
        tapahtumalista.lisaa(tapahtuma);
        jono.peek().setLabrakaynti(true);
        addPalveluAikaToSumma(palveluaika);
        palveltuCount++;
        palveluAikaSumma += palveluaika;
        keskimaarainenPalveluAika = palveluAikaSumma / palveltuCount;
    }
}

