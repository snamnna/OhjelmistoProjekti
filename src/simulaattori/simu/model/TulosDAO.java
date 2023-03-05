package simulaattori.simu.model;

import java.util.List;

import datasource.MAriaDBConnector;
import entity.Tulos;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
		System.out.println("Tulokset viety");
	}
	
	/*
	 * Using Criteria API to construct a query to fetch all Tulos objects from the database table.
	 * The query is executed by calling the getResultList() method on the created query object.
	 * The fetched Tulos objects are returned as a list.
	 */
	
	public static List<Tulos> getAllTulokset() {
        List<Tulos> tulosList = null;
        try {
            EntityManager entityManager = datasource.MAriaDBConnector.getInstance();
            entityManager.getTransaction().begin();

            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Tulos> criteriaQuery = criteriaBuilder.createQuery(Tulos.class);
            Root<Tulos> root = criteriaQuery.from(Tulos.class);
            criteriaQuery.select(root);

            tulosList = entityManager.createQuery(criteriaQuery).getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tulosList;
    }
}
