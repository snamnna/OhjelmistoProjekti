package simulaattori.model;

import eduni.distributions.ContinuousGenerator;
import simulaattori.framework.Kello;
import simulaattori.framework.Tapahtuma;
import simulaattori.framework.Tapahtumalista;

import java.util.concurrent.ThreadLocalRandom;

public class Laakari extends Palvelupiste {

    public Laakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
    }

    @Override
    public void addArrival() {

    }

    @Override
    public void aloitaPalvelu() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        varattu = true;
        double palveluaika = generator.sample();
        Tapahtuma tapahtuma = new Tapahtuma(tyyppi == TapahtumanTyyppi.YLARR ? TapahtumanTyyppi.YLDEP : TapahtumanTyyppi.ELDEP, Kello.getInstance().getAika() + palveluaika, this);
        if (random.nextBoolean() && !jono.peek().getLabrakaynti()) {
            tapahtuma.setTyyppi(TapahtumanTyyppi.LABRA_ARRIVAL);
        }
        tapahtumalista.lisaa(tapahtuma);
        addPalveluAikaToSumma(palveluaika);
        palveltuCount++;
        palveluAikaSumma += palveluaika;
        keskimaarainenPalveluAika = palveluAikaSumma / palveltuCount;
    }
}
