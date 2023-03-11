package simulaattori.simu.model;

import simulaattori.simu.framework.Kello;
import simulaattori.simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private final int id;
	private static double waitingTime;
	private static int i = 1;
	private static double sum = 0;
	private boolean labrassaKayty = false;
	//tallennetaan viimeisin annettu id tuloksia varten
	private static int viimeisinID;

	private static int asiakasCount = 0;

	private static int completed;
	private static double totalWaitingTime;


	public Asiakas() {
		id = i++;
		//viimeisin id sama arvo, kuin id, eli i
		viimeisinID = i;
		asiakasCount++;

		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}
	
	public static int getAsiakasCount() {
		return asiakasCount;
	}

	public static void reset() {
		asiakasCount = 0;
		sum = 0;
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
	public void setLabrakaynti(boolean kayty) {
		this.labrassaKayty = kayty;
	}
	public boolean getLabrakaynti() {
		return labrassaKayty;
	}
	
	public static double getTotalWaitingTime() {
		return sum;
	}

	public static int getViimeisinID() {
		return viimeisinID;
	}

	public static double getWaitingTime() {
		return waitingTime;
	}

	public void raportti() {
		sum += (poistumisaika - saapumisaika);
//		double keskiarvo = sum / asiakasCount;
//		waitingTime = keskiarvo;
		//Trace.out(Trace.Level.INFO,"Asiakkaiden läpimenoaikojen keskiarvo tähän asti "+ keskiarvo);
	}
}
