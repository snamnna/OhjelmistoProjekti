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
	@Column(name="arrivalCount")
	private int arrivalCount;
	@Column(name="completedCount")
	private int completedCount;
	@Column(name="simTime")
	private double simTime;
	@Column(name="busyTime")
	private double busyTime;
	@Column(name="serviceTime")
	private double serviceTime;
	@Column(name="throughput")
	private double throughput;
	@Column(name="utilization")
	private double utilization;
	@Column(name="averageResponseTime")
	private double averageResponseTime;
	@Column(name="averageWaitingTime")
	private double averageWaitingTime;
	
	private int yleislääkärit; //pitää viel kattoo miten haetaan et saadaan tietokantaan
	private int erikoislääkärit; //Sama homma
	private int labraArrivalit;
	private double waitingTime;
	//Transienttina, jotta hibernate ei huomioisi tyhjää muuttujaa
	@Transient
	private double responseTime;

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
		waitingTime = Asiakas.getWaitingTime();
		averageResponseTime = waitingTime/completedCount;
		averageWaitingTime = waitingTime/simTime;
		
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
	//Waiting time W, kokonaisoleskeluaika palvelupisteissä 
	//(kaikkien asiakkaiden läpimenoaikojen summa)
	public double getWaitingTime() {
		waitingTime = Asiakas.getWaitingTime();
		return waitingTime;
	}
	//Response time R, keskimääräinen palvelupisteiden läpimenoaika
	public double getAverageResponseTime() {
		averageResponseTime = waitingTime/completedCount;
		return averageResponseTime;
	}
	//Keskimääräinen jononpituus N
	public double getAverageWaitingTime() {
		averageWaitingTime = waitingTime/simTime;
		return averageWaitingTime;
	}
	
	//TODO: HALUTAANKO YKSITTÄISEN PALVELUPISTEEN LÄPIMENOAIKAA? NYT ON JO KESKIARVO KAIKILLE -tuisku

	//Response time Ri palvelupisteen läpimenoaika
	public double getResponseTime() {
		return responseTime;
	}





}
