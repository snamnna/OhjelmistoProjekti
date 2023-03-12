package simulaattori.model;

import entity.Tulos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class TulosDAO {

	private static TulosDAO instance;
	private static SessionFactory sf;

	/**
	 * TulosDAO-luokan yksityinen konstruktori, joka luo uuden Hibernate-istunnon ja käyttää luettua
	 * Hibernate-konfiguraatiotiedostoa tietokantayhteyden muodostamiseen.
	 */
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
	
	/**
	 * Luo uuden tulos-olion tietokantaan käyttämällä avointa hibernate-istuntoa.
	 * @param tulos Tulos-olio, joka tallennetaan tietokantaan.
	 * @return true, jos tallennus onnistuu, muutoin false.
	 */
	public boolean createTulos(Tulos tulos) {
	    Session istunto = null;
	    try {
	        istunto = sf.openSession();
	        istunto.beginTransaction();
	        istunto.merge(tulos);
	        istunto.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        if (istunto != null) {
	            istunto.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (istunto != null) {
	            istunto.close();
	        }
	    }
	}

	/**
	 * Hakee kaikki tallennetut tulokset tietokannasta.
	 * @return Tulos-olioita sisältävä taulukko, joka sisältää kaikki tietokantaan tallennetut tulokset.
	 */
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
	
	/**
	 * Poistaa tietokannasta halutun tulos-olion.
	 * @param id Poistettavan tulos-olion ID.
	 * @return true, jos poisto onnistuu, muutoin false.
	 */
	public boolean deleteTulos(int id) {
	    Session istunto = null;
	    try {
	        istunto = sf.openSession();
	        istunto.beginTransaction();
	        Tulos tulos = (Tulos) istunto.get(Tulos.class, id);
	        if (tulos != null) {
	            istunto.remove(tulos);
	            istunto.getTransaction().commit();
	            return true;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (istunto != null) {
	            istunto.close();
	        }
	    }
	    return false;
	}
}
