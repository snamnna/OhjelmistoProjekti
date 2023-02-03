package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.model.util.IPalvelupiste;

import java.util.Random;

import static simu.model.OmaMoottori.SAIRAANHOITAJA;
import static simu.model.OmaMoottori.YLEISLAAKARI;

public class YLaakari extends Palvelupiste{

    public YLaakari(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
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
        Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.YLDEP, Kello.getInstance().getAika() + palveluaika);
        if(random.nextBoolean() && !jono.peek().getLabrakaynti()) {
            tapahtuma.setTyyppi(TapahtumanTyyppi.LABRA_ARRIVAL);
        }
        tapahtumalista.lisaa(tapahtuma);
        viimeisinLuotuTapahtuma = tapahtuma;
    }

    @Override
    public void siirraAsiakas(Tapahtuma tapahtuma, IPalvelupiste[] palvelupisteet) {
        switch (tapahtuma.getTyyppi()){
            case YLARR: {
                lisaaJonoon(palvelupisteet[SAIRAANHOITAJA].otaJonosta());
                break;
            }
            case YLDEP: {
                Asiakas asiakas = palvelupisteet[YLEISLAAKARI].otaJonosta();
                asiakas.setPoistumisaika(Kello.getInstance().getAika());
                asiakas.raportti();
                break;
            }
        }
    }

    public String getJonoString() {
        return jono.toString() + "\n YLaakari";
    }
}
