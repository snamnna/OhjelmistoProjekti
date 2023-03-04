package entity;

import jakarta.persistence.*;
import simulaattori.MainApp;
import simulaattori.simu.framework.Kello;
import simulaattori.simu.model.Asiakas;
import simulaattori.simu.model.ELaakari;
import simulaattori.simu.model.Labra;
import simulaattori.simu.model.OmaMoottori;
import simulaattori.simu.model.Sairaanhoitaja;
import simulaattori.simu.model.YLaakari;
import simulaattori.view.KayttajatiedotController;


@Entity
@Table(name="tulokset")
public class Tulos {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int yleislääkärit;
	private int erikoislääkärit;
	private int labraArrivalit;
	private int arrivalCount;
	private int completedCount;
	private double simTime;
	private double busyTime;
	private double serviceTime;
	private double throughput;
	private double utilization;
	@Transient
	private double responseTime;
	@Transient
	private double waitingTime;
	@Transient
	private double averageResponseTime;
	@Transient
	private double averageWaitingTime;

	@Transient
	Kello kello = Kello.getInstance();

	public Tulos() {
		completedCount = OmaMoottori.departuret;
		arrivalCount = Asiakas.getViimeisinID();
		simTime = kello.getAika();
		busyTime = ELaakari.getKokPalveluaika() + YLaakari.getKokPalveluaika() + Sairaanhoitaja.getKokPalveluaika() + Labra.getKokPalveluaika();
		throughput = completedCount/simTime;
		utilization = busyTime / simTime;
		serviceTime = busyTime/completedCount;
		labraArrivalit = OmaMoottori.labrat;
		
	}
	public int getId() {
		return id;
	}

	//Arrival count A, asiakkaiden  kokonaismäärä
	public int getArrivalCount() {
		arrivalCount = Asiakas.getViimeisinID();
		return arrivalCount;
	}
	//Completed count, C kokonaismäärä palveltuja asiakkaita (departuret)
	public int getCompletedCount() {
		//voidaan myös tehä setterillä, en ollut varma mikä ois paras systeemi niin tein suoraan tänne
		completedCount = OmaMoottori.departuret;
		return completedCount;
	}

	//Simuloinnin kokonaisaika T
	public double getKokonaisaika() {
		simTime = kello.getAika();
		return simTime;
	}

	//Busy time B, palvelupisteiden aktiiviaika
	public double getBusyTime() {
		busyTime = ELaakari.getKokPalveluaika() + YLaakari.getKokPalveluaika() + Sairaanhoitaja.getKokPalveluaika() + Labra.getKokPalveluaika();
		return busyTime;
	}

	//Utilization U, palvelupisteen käyttöaste
	public double getUtilization() {
		utilization = busyTime / simTime;
		return utilization;
	}

	//Throughput X, suoritusteho X=C/T
	public double getThroughput() {
		throughput = completedCount/simTime;
		return throughput;
	}

	//Service time S, palvelupisteen keskimääräinen palveluaika
	public double getServiceTime() {
		serviceTime = busyTime/completedCount;
		return serviceTime;
	}
	//labrakäyntien määrä
	public int getLabraArrivalit() {
		return OmaMoottori.labrat;
	}
	
	//TODO: Keksi kuinka

	//Response time R, keskimääräinen palvelupisteen läpimenoaika
	public double getAverageResponseTime() {
		averageResponseTime = waitingTime/completedCount;
		return averageResponseTime;
	}

	//Keskimääräinen jononpituus N
	public double getAverageWaitingTime() {
		averageWaitingTime = waitingTime/simTime;
		return averageWaitingTime;
	}

	//Response time Ri palvelupisteen läpimenoaika
	public double getResponseTime() {
		return responseTime;
	}

	//Waiting time W, kokonaisoleskeluaika palvelupisteessä 
	//(kaikkien asiakkaiden läpimenoaikojen summa)
	public double getWaitingTime() {
		return waitingTime;
	}



}
