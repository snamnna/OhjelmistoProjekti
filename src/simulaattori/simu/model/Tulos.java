package simulaattori.simu.model;

import jakarta.persistence.*;


@Entity
@Table(name="tulokset")
public class Tulos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	private int arrivalCount;
	private int completedCount;
	private double simTime;
	private double busyTime;
	private double responseTime;
	private double waitingTime;
	private double serviceTime;
	private double throughput;
	private double utilization;
	private double averageResponseTime;
	private double averageWaitingTime;

	private OmaMoottori moottori;

	public Tulos() {}
	
	public Tulos(OmaMoottori moottori) {
		this.moottori = moottori;
	}


	//Arrival count A, asiakkaiden  kokonaismäärä
	public int getArrivalCount() {
		arrivalCount = Asiakas.getViimeisinID();
		return arrivalCount;
	}
	//Completed count, C kokonaismäärä palveltuja asiakkaita (departuret)
	public int getCompletedCount() {
		//voidaan myös tehä setterillä, en ollut varma mikä ois paras systeemi niin tein suoraan tänne
		completedCount = moottori.getDeparturet();
		return completedCount;
	}
	
	//HUOM: tästä alaspäin en oikein tiennyt miten tehdä näitä kun koodi aika erilaista kuin esimerkeissä
	//enkä miettimisenkään jälkeen oikein keksinyt mitään. Sorii -Tuisku
	
	//Simuloinnin kokonaisaika T
	public double getKokonaisaika() {
		//haetaanko mainappista?
		return simTime;
	}
	
	//Busy time B, palvelupistekohtainen aktiiviaika
	public double getBusyTime() {
		return busyTime;
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
	
	//Utilization U, palvelupisteen käyttöaste
	public double getUtilization() {
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
}
