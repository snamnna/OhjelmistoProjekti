package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tulokset")
public class Tulos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "arrivalCount")
	private int arrivalCount;
	@Column(name = "completedCount")
	private int completedCount;
	@Column(name = "simTime")
	private double simTime;
	@Column(name = "busyTime")
	private double busyTime;
	@Column(name = "serviceTime")
	private double serviceTime;
	@Column(name = "throughput")
	private double throughput;
	@Column(name = "utilization")
	private double utilization;
	@Column(name = "averageResponseTime")
	private double averageResponseTime;
	@Column(name = "averageWaitingTime")
	private double averageWaitingTime;
	@Column(name = "yleislääkärit")
	private int yleislääkärit; // pitää viel kattoo miten haetaan et saadaan tietokantaan
	@Column(name = "erikoislääkärit")
	private int erikoislääkärit; // Sama homma
	@Column(name = "sairaanhoitajat")
	private int sairaanhoitajat;
	@Column(name = "labrat")
	private int labrat;
	@Column(name = "labraArrivalit")
	private int labraArrivalit;
	@Column(name = "waitingTime")
	private double waitingTime;
	// Transienttina, jotta hibernate ei huomioisi tyhjää muuttujaa
	@Transient
	private double responseTime;
	
	public int getId() {
		return id;
	}
	
	/**
	 * @param yleislääkärien lkm to set
	 */
	public void setYleislääkärit(int yleislääkärit) {
		this.yleislääkärit = yleislääkärit;
	}

	/**
	 * @param erikoislääkärien lkm to set
	 */
	public void setErikoislääkärit(int erikoislääkärit) {
		this.erikoislääkärit = erikoislääkärit;
	}

	/**
	 * @param sairaanhoitajien lkm to set
	 */
	public void setSairaanhoitajat(int sairaanhoitajat) {
		this.sairaanhoitajat = sairaanhoitajat;
	}

	/**
	 * @param labrojen lkm to set
	 */
	public void setLabrat(int labrat) {
		this.labrat = labrat;
	}

	/**
	 * @param simulaatioon saapuneiden asiakkaiden määrä
	 */
	public void setArrivalCount(int arrivalCount) {
		this.arrivalCount = arrivalCount;
	}

	/**
	 * @param simun läpikulkeneiden asiakkaiden lkm
	 */
	public void setCompletedCount(int completedCount) {
		this.completedCount = completedCount;
	}

	/**
	 * @param simun ajoon kulunut aika
	 */
	public void setSimTime(double simTime) {
		this.simTime = simTime;
	}
	
	/**
	 * @param kaikkien palvelupisteiden yhteenlaskettu palveluaika
	 */
	public void setBusyTime(double busyTime) {
		this.busyTime = busyTime;
	}

	/**
	 * @param labraan saapuneiden asiakkaiden lkm
	 */
	public void setLabraArrivalit(int labraArrivalit) {
		this.labraArrivalit = labraArrivalit;
	}

	/**
	 * @param waitingTime the waitingTime to set
	 */
	public void setWaitingTime(double waitingTime) {
		this.waitingTime = waitingTime;
	}

	/**
	 * @param responseTime the responseTime to set
	 */
	public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}
	
	/**
	 * @param throughput the throughput to set
	 */
	public void setThroughput(double throughput) {
		this.throughput = throughput;
	}

	/**
	 * @param utilization the utilization to set
	 */
	public void setUtilization(double utilization) {
		this.utilization = utilization;
	}

	/**
	 * @param serviceTime the serviceTime to set
	 */
	public void setServiceTime(double serviceTime) {
		this.serviceTime = serviceTime;
	}
	
	/**
	 * @param averageResponseTime the averageResponseTime to set
	 */
	public void setAverageResponseTime(double averageResponseTime) {
		this.averageResponseTime = averageResponseTime;
	}

	/**
	 * @param averageWaitingTime the averageWaitingTime to set
	 */
	public void setAverageWaitingTime(double averageWaitingTime) {
		this.averageWaitingTime = averageWaitingTime;
	}

	// Arrival count A, asiakkaiden kokonaismäärä
	public int getArrivalCount() {
		return arrivalCount;
	}

	// Completed count, C kokonaismäärä palveltuja asiakkaita (departuret)
	public int getCompletedCount() {
		return completedCount;
	}

	// Simuloinnin kokonaisaika T
	public double getKokonaisaika() {
		return simTime;
	}

	// Busy time B, palvelupisteiden aktiiviaika
	public double getBusyTime() {
		return busyTime;
	}

	// Utilization U, palvelupisteen käyttöaste
	public double getUtilization() {
		return utilization;
	}

	// Throughput X, suoritusteho X=C/T
	public double getThroughput() {
		return throughput;
	}

	// Service time S, palvelupisteen keskimääräinen palveluaika
	public double getServiceTime() {
		return serviceTime;
	}

	// labrakäyntien määrä
	public int getLabraArrivalit() {
		return labraArrivalit;
	}

	// Waiting time W, kokonaisoleskeluaika palvelupisteissä
	// (kaikkien asiakkaiden läpimenoaikojen summa)
	public double getWaitingTime() {
		return waitingTime;
	}

	// Response time R, keskimääräinen palvelupisteiden läpimenoaika
	public double getAverageResponseTime() {
		return averageResponseTime;
	}

	// Keskimääräinen jononpituus N
	public double getAverageWaitingTime() {
		return averageWaitingTime;
	}

	// TODO: HALUTAANKO YKSITTÄISEN PALVELUPISTEEN LÄPIMENOAIKAA? NYT ON JO
	// KESKIARVO KAIKILLE -tuisku

	// Response time Ri palvelupisteen läpimenoaika
	public double getResponseTime() {
		return responseTime;
	}

	/**
	 * @return simulaation kesto
	 */
	public double getSimTime() {
		return simTime;
	}

	/**
	 * @return yleislääkärien lukumäärä
	 */
	public int getYleislääkärit() {
		return yleislääkärit;
	}

	/**
	 * @return erikoislääkärien lukumäärä
	 */
	public int getErikoislääkärit() {
		return erikoislääkärit;
	}

	/**
	 * @return sairaanhoitajien lukumäärä
	 */
	public int getSairaanhoitajat() {
		return sairaanhoitajat;
	}

	/**
	 * @return laboratorioiden lukumäärä
	 */
	public int getLabrat() {
		return labrat;
	}
}
