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

public class Sairaanhoitaja extends Palvelupiste {
    public Sairaanhoitaja(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        jakauma = new Normal(0.5, 0.5);
    }

    // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
    public void aloitaPalvelu() {
    	ThreadLocalRandom random = ThreadLocalRandom.current();
        Trace.out(Trace.Level.INFO, "Aloitetaan uusi palvelu asiakkaalle " + jono.peek().getId());
        varattu = true;
        double palveluaika = generator.sample();
        // arvo meneekö erikoislääkärille vai yleislääkärille
        Tapahtuma tapahtuma = new Tapahtuma(random.nextBoolean() ? TapahtumanTyyppi.YLARR : TapahtumanTyyppi.ELARR,
                Kello.getInstance().getAika() + palveluaika, this.ID);
        tapahtumalista.lisaa(tapahtuma);
        viimeisinLuotuTapahtuma = tapahtuma;
    }

    public void siirraAsiakas(Tapahtuma tapahtuma, Map<Integer, IPalvelupiste> palvelupisteet) {
        lisaaJonoon(new Asiakas());
    }

    public String getJonoString() {
        return jono.toString() + "\n Sairaanhoitaja";
    }
}
