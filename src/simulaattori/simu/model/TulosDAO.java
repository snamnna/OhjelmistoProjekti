package simulaattori.simu.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Tulos;
import jakarta.persistence.EntityManager;

public class TulosDAO {

	private static TulosDAO instance;
	private static SessionFactory sf;

	private TulosDAO() {
		try {
			sf = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			System.err.println("");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static TulosDAO getInstance() {
		if (instance == null) {
			synchronized (TulosDAO.class) {
				instance = new TulosDAO();
			}
		}
		return instance;
	}

	// Haetaan tulos tietokannasta
	public Tulos haeTulos(int id) {
		EntityManager em = datasource.MAriaDBConnector.getInstance();
		em.getTransaction().begin();
		Tulos tulos = em.find(Tulos.class, id);
		em.getTransaction().commit();
		return tulos;
	}

	// Viedään tulos tietokantaan
	public void vieTulos(Tulos tulos) {
		EntityManager em = datasource.MAriaDBConnector.getInstance();
		em.getTransaction().begin();
		em.persist(tulos);
		em.getTransaction().commit();
		System.out.println("Tulokset viety");
	}
	
	public boolean createTulos(Tulos tulos) {
		Session istunto = sf.getCurrentSession();
		try {
			istunto.beginTransaction();
			istunto.merge(tulos);
			istunto.getTransaction().commit();
			return true;
		} catch (Exception e) {
			istunto.getTransaction().rollback();
			e.printStackTrace();
			return false;
		}
	 }

	public Tulos[] getTulokset() {
		List<Tulos> tulosList = null;
		try (Session istunto = sf.openSession();) {
			istunto.beginTransaction();
			tulosList = istunto.createQuery("FROM Tulos", Tulos.class).list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tulosList.toArray(new Tulos[tulosList.size()]);
	}
	
	public boolean deleteTulos(int id) {
		try (Session istunto = sf.openSession()) {
			istunto.beginTransaction();
			Tulos tulos = (Tulos) istunto.get(Tulos.class, id);
			if (tulos != null) {
				istunto.remove(tulos);
				istunto.getTransaction().commit();
				System.out.println("Tulos-olio poistettu.");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Tuloksen poisto epäonnistui.");
		}
		return false;
	}
}
