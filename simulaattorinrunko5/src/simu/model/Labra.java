package simu.model;

import eduni.distributions.ContinuousGenerator;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;
import simu.framework.Trace;
import simu.model.util.IPalvelupiste;

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
        Tapahtuma tapahtuma = new Tapahtuma(TapahtumanTyyppi.LABRA_DEPARTURE, Kello.getInstance().getAika() + palveluaika);
        tapahtumalista.lisaa(tapahtuma);

        // arvotaan vielä, meneekö asiakas kotiin vai yleislääkärille
        if (random.nextBoolean()) {
            if (random.nextBoolean())
                viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.ELARR, Kello.getInstance().getAika() + palveluaika);
            else
                viimeisinLuotuTapahtuma = new Tapahtuma(TapahtumanTyyppi.YLARR, Kello.getInstance().getAika() + palveluaika);
        } else viimeisinLuotuTapahtuma = tapahtuma;

        jono.peek().setLabrakaynti(true);
    }

    @Override
    public void siirraAsiakas(Tapahtuma tapahtuma, IPalvelupiste[] palvelupisteet) {
        switch (tapahtuma.getTyyppi()) {
            // tarkistetaan kumman palvelupisteen viimeisin tapahtuma on
            case LABRA_ARRIVAL: {
                if (palvelupisteet[YLEISLAAKARI].getViimeisinTapahtuma().equals(tapahtuma)) {
                    lisaaJonoon(palvelupisteet[YLEISLAAKARI].otaJonosta());
                } else if (palvelupisteet[ERIKOISLAAKARI].getViimeisinTapahtuma().equals(tapahtuma)) {
                    lisaaJonoon(palvelupisteet[ERIKOISLAAKARI].otaJonosta());
                } else {
                    throw new IllegalStateException("Tapahtuma ei ole yleis- tai erikoislääkärin viimeisin tapahtuma");
                }
                break;
            }
            case LABRA_DEPARTURE: {
                // meneekö kotiin, erikoislääkärille vai yleislääkärille
                switch (viimeisinLuotuTapahtuma.getTyyppi()) {
                    case YLARR:
                        palvelupisteet[YLEISLAAKARI].lisaaJonoon(otaJonosta());
                        break;
                    case ELARR:
                        palvelupisteet[ERIKOISLAAKARI].lisaaJonoon(otaJonosta());
                        break;
                    case LABRA_DEPARTURE: {
                        Asiakas asiakas = otaJonosta();
                        asiakas.setPoistumisaika(Kello.getInstance().getAika());
                        asiakas.raportti();
                        break;
                    }
                }
                break;
            }
        }
    }

    public String getJonoString() {
        return jono.toString() + "\n labra";
    }
}
