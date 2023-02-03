package simu.model;

import eduni.distributions.Negexp;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.model.util.FPalvelupiste;
import simu.model.util.IPalvelupiste;

import java.util.HashMap;
import java.util.Map;

public class OmaMoottori extends Moottori {

    private final Saapumisprosessi saapumisprosessi;

    Map<TapahtumanTyyppi, IPalvelupiste> tapahtumaMap = new HashMap<>();

    public final static int SAIRAANHOITAJA = 0;
    public final static int YLEISLAAKARI = 1;
    public final static int ERIKOISLAAKARI = 2;
    public final static int LABRA = 3;

    public OmaMoottori() {
        palvelupisteet = new IPalvelupiste[4];

        palvelupisteet[SAIRAANHOITAJA] = FPalvelupiste.createPalvelupiste(TapahtumanTyyppi.ARR, tapahtumalista);
        palvelupisteet[YLEISLAAKARI] = FPalvelupiste.createPalvelupiste(TapahtumanTyyppi.YLARR, tapahtumalista);
        palvelupisteet[ERIKOISLAAKARI] = FPalvelupiste.createPalvelupiste(TapahtumanTyyppi.ELARR, tapahtumalista);
        palvelupisteet[LABRA] = FPalvelupiste.createPalvelupiste(TapahtumanTyyppi.LABRA_ARRIVAL, tapahtumalista);
        saapumisprosessi = new Saapumisprosessi(new Negexp(15, 5), tapahtumalista, TapahtumanTyyppi.ARR);

        tapahtumaMap.put(TapahtumanTyyppi.ARR, palvelupisteet[SAIRAANHOITAJA]);
        tapahtumaMap.put(TapahtumanTyyppi.YLARR, palvelupisteet[YLEISLAAKARI]);
        tapahtumaMap.put(TapahtumanTyyppi.ELARR, palvelupisteet[ERIKOISLAAKARI]);
        tapahtumaMap.put(TapahtumanTyyppi.LABRA_ARRIVAL, palvelupisteet[LABRA]);
        tapahtumaMap.put(TapahtumanTyyppi.LABRA_DEPARTURE, palvelupisteet[LABRA]);
        tapahtumaMap.put(TapahtumanTyyppi.YLDEP, palvelupisteet[YLEISLAAKARI]);
        tapahtumaMap.put(TapahtumanTyyppi.ELDEP, palvelupisteet[ERIKOISLAAKARI]);
    }


    @Override
    protected void alustukset() {
        saapumisprosessi.generoiSeuraava(); // Ensimmäinen saapuminen järjestelmään
    }

    @Override
    protected void suoritaTapahtuma(Tapahtuma t) {  // B-vaiheen tapahtumat
        for(IPalvelupiste p : palvelupisteet) System.out.println(p.getJonoString());
        TapahtumanTyyppi tyyppi = t.getTyyppi();
        tapahtumaMap.get(tyyppi).siirraAsiakas(t, palvelupisteet);
        if(tyyppi == TapahtumanTyyppi.ARR) saapumisprosessi.generoiSeuraava();
    }


    @Override
    protected void tulokset() {
        System.out.println("Simulointi päättyi kello " + Kello.getInstance().getAika());
        System.out.println("Tulokset ... puuttuvat vielä");
    }
}
