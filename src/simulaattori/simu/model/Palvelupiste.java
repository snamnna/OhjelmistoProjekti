package simulaattori.simu.model;

import eduni.distributions.ContinuousGenerator;
import simulaattori.simu.framework.Tapahtumalista;
import simulaattori.simu.model.util.IPalvelupiste;

import java.util.LinkedList;

public abstract class Palvelupiste implements IPalvelupiste {

    protected static int departures;
    private static int seuraavaID = 1;
    private static double kaikkienPalveluAikaSumma;
    protected final ContinuousGenerator generator;
    protected final Tapahtumalista tapahtumalista;
    protected final TapahtumanTyyppi tyyppi;
    protected final int ID;
    protected LinkedList<Asiakas> jono; // Tietorakennetoteutus
    protected boolean varattu = false;
    protected double keskimaarainenPalveluAika;
    protected int palveltuCount;
    protected double palveluAikaSumma;

    public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi) {
        this.tapahtumalista = tapahtumalista;
        this.generator = generator;
        this.tyyppi = tyyppi;
        ID = getSeuraavaID();
        jono = new LinkedList<>();
        departures = 0;
        kaikkienPalveluAikaSumma = 0;
    }

    protected void addPalveluAikaToSumma(double aika) {
        kaikkienPalveluAikaSumma += aika;
    }

    public double getKaikkienPalveluAikojenSumma() {
        return kaikkienPalveluAikaSumma;
    }

    public TapahtumanTyyppi getTyyppi() {
        return tyyppi;
    }

    private int getSeuraavaID() {
        return seuraavaID++;
    }

    public int getID() {
        return ID;
    }

    public void lisaaJonoon(Asiakas a) {
        jono.add(a);
    }

    public Asiakas otaJonosta() {
        varattu = false;
        return jono.poll();
    }

    public boolean onVarattu() {
        return varattu;
    }

    public boolean onJonossa() {
        return !jono.isEmpty();
    }

    public int getDepartureLkm() {
        return departures;
    }

    public void addDeparture() {
        departures++;
    }

    public abstract void addArrival();

    public int getAsiakasMaaraJonossa() {
        return jono.size();
    }

}
