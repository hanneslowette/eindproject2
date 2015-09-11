package zomaar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.betavzw.entities.Werknemer;

public class HibernateMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("unitName");
		
		EntityManager em = emf.createEntityManager();

//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
		
		Werknemer koen = new Werknemer();
		koen.setNaam("Koen");
		koen.setVoornaam("De Voegt");
		
		Werknemer brent = new Werknemer();
		koen.setNaam("Courtois");
		koen.setVoornaam("Brent");
		
//		em.persist(koen);
//		em.persist(brent);
//
//		em.close();
		emf.close();
		
	}

}
