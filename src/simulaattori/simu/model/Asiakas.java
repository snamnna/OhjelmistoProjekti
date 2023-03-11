package simulaattori.simu.model;

import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Trace;


public class Asiakas {
    private static int i = 1;
    private static double sum = 0;
    private static int asiakasCount = 0;
    private final int id;
    private double saapumisaika;
    private double poistumisaika;
    private boolean labrassaKayty = false;


    public Asiakas() {
        id = i++;
        asiakasCount++;

        saapumisaika = Kello.getInstance().getAika();
        Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
    }

    public static void reset() {
        asiakasCount = 0;
        sum = 0;
    }

    public static int getAsiakasCount() {
        return asiakasCount;
    }

    public static double getTotalWaitingTime() {
        return sum;
    }

    public double getPoistumisaika() {
        return poistumisaika;
    }

    public void setPoistumisaika(double poistumisaika) {
        this.poistumisaika = poistumisaika;
    }

    public double getSaapumisaika() {
        return saapumisaika;
    }

    public void setSaapumisaika(double saapumisaika) {
        this.saapumisaika = saapumisaika;
    }

    public int getId() {
        return id;
    }

    public boolean getLabrakaynti() {
        return labrassaKayty;
    }

    public void setLabrakaynti(boolean kayty) {
        this.labrassaKayty = kayty;
    }

    public void raportti() {
        sum += (poistumisaika - saapumisaika);
        double keskiarvo = sum / Labra.departures;
        //Trace.out(Trace.Level.INFO,"Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
    }
}
