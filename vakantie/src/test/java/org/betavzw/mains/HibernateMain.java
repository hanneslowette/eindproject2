package org.betavzw.mains;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.betavzw.entity.CollectiefVerlof;
import org.betavzw.entity.Credentials;
import org.betavzw.entity.Feestdag;
import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.entity.Team;
import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.AccountType;

public class HibernateMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("unitName");
		
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Werknemer koen = new Werknemer();
		koen.setNaam("Koen");
		koen.setVoornaam("De Voegt");
//		koen.setAdres("Zuidewendelaan 7");
//		koen.setGemeente("Hoboken");
//		koen.setPostcode(2660);
		koen.setPaswoord("paswoord");
		koen.setEmail("koen@devoegt.be");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDate date = LocalDate.parse("1980-apr-13", formatter);
		koen.setGeboortedatum(date);
		
		Team teamRed = new Team();
		teamRed.setNaam("Team Red");
		teamRed.setCode("WIN");
		em.persist(teamRed);
		
		Team teamBlue = new Team();
		teamBlue.setNaam("Team Blue");
		teamBlue.setCode("LOSE");
		em.persist(teamBlue);
		
		Werknemer thomas = new Werknemer();
		thomas.setNaam("Thomas");
		em.persist(thomas);
		
		teamRed.setTeamverantwoordelijke(thomas);
		
		Werknemer hannes = new Werknemer();
		hannes.setNaam("Hannes");
		teamRed.addWerknemer(hannes);
		em.persist(hannes);
		
		koen.setTeam(teamRed);
		
		Werknemer brent = new Werknemer();
		brent.setNaam("Courtois");
		brent.setVoornaam("Brent");
		brent.setTeam(teamBlue);
		
		em.persist(koen);
		em.persist(brent);
		
		JaarlijksVerlof jv2014 = new JaarlijksVerlof();
		jv2014.setJaar(2014);
		jv2014.setAantalDagen(20);
		
		JaarlijksVerlof jv2014a = new JaarlijksVerlof();
		jv2014a.setJaar(2014);
		jv2014a.setAantalDagen(25);
		
		JaarlijksVerlof jv2015 = new JaarlijksVerlof();
		jv2015.setJaar(2015);
		jv2015.setAantalDagen(30);
		
		em.persist(jv2014);
		em.persist(jv2014a);
		em.persist(jv2015);
		
		koen.addJaarlijksVerlof(jv2014a);
		koen.addJaarlijksVerlof(jv2015);
		
		hannes.addJaarlijksVerlof(jv2014);
//		hannes.addJaarlijksVerlof(jv2015);
		
		VerlofAanvraag va = new VerlofAanvraag();
		date = LocalDate.parse("2014-apr-01", formatter);
		va.setStartDatum(date);
		date = LocalDate.parse("2014-apr-30", formatter);
		va.setEindDatum(date);
		va.setWerknemer(koen);
		
		em.persist(va);
		
		CollectiefVerlof cv = new CollectiefVerlof();
		date = LocalDate.parse("2014-nov-01", formatter);
		cv.setStartDatum(date);
		date = LocalDate.parse("2014-nov-01", formatter);
		cv.setEindDatum(date);
		em.persist(cv);
		
		Feestdag f = new Feestdag();
		date = LocalDate.parse("2015-dec-25", formatter);
		f.setStartDatum(date);
		f.setOmschrijving("Kerstmis");
		em.persist(f);
		
		Credentials credentials = new Credentials();
		credentials.setUsername("hannes");
		credentials.setPassword("hannes");
		credentials.setType(AccountType.ADMINISTRATOR);
		credentials.setWerknemer(hannes);
		em.persist(credentials);
		
		tx.commit();
		
		em.close();
		emf.close();
		
		
	}

}
