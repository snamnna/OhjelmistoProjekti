package simulaattori.simu.model;

import jakarta.persistence.*;

public class TulosDAO {

	//Haetaan tulos tietokannasta
	public Tulos haeTulos(int id) {
		EntityManager em = datasource.MAriaDBConnector.getInstance();
		em.getTransaction().begin();
		Tulos tulos = em.find(Tulos.class, id);
		em.getTransaction().commit();
		return tulos;
	}
	
	//Viedään tulos tietokantaan
	public void vieTulos(Tulos tulos) {
		EntityManager em = datasource.MAriaDBConnector.getInstance();
		em.getTransaction().begin();
		em.persist(tulos);
		em.getTransaction().commit();
	}
	
}
