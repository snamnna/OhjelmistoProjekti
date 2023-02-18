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

public class Sairaanhoitaja extends Palvelupiste {
    public Sairaanhoitaja(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        super(generator, tapahtumalista, tyyppi);
        jakauma = new Normal(0.5, 0.5);
    }

    // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
    public void aloitaPalvelu() {
        Random random = new Random();
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
