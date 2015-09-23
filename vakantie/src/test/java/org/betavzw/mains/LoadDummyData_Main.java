package org.betavzw.mains;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.codec.digest.DigestUtils;
import org.betavzw.entity.Adres;
import org.betavzw.entity.CollectiefVerlof;
import org.betavzw.entity.Credentials;
import org.betavzw.entity.Feestdag;
import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.entity.Team;
import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.exceptions.FoutAantalVerlofdagenException;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;

public class LoadDummyData_Main {

	public static void main(String[] args)
			throws GeboortedatumInDeToekomstException,
			FoutAantalVerlofdagenException {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("unitName");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MMM-dd");
		
		/**
		 * a
		 */
		Werknemer a = new Werknemer();
		a.setNaam("Adams");
		a.setVoornaam("a");
		a.setEmail("brentcourtois@gmail.com");
		Adres aAdres = new Adres("straat", "nummer", "busnummer", "postcode", "gemeente");
		a.setGeboortedatum(LocalDate.parse("2000-jan-01", formatter));
		
		/**
		 * Brent
		 */
		Werknemer brent = new Werknemer();
		brent.setNaam("Courtois");
		brent.setVoornaam("Brent");
		brent.setEmail("brentcourtois@gmail.com");
		Adres brentAdres = new Adres("Ibisstraat", "19", "3", "2170", "Merksem");
		brent.setGeboortedatum(LocalDate.parse("1993-jun-19", formatter));

		/**
		 * Koen
		 */
		Werknemer koen = new Werknemer();
		koen.setNaam("De Voegt");
		koen.setVoornaam("Koen");
		koen.setEmail("koen@devoegt.be");
		Adres koenAdres = new Adres("Zuidewendelaan", "7", "", "2660",
				"Hoboken");
		koen.setGeboortedatum(LocalDate.parse("1980-apr-13", formatter));

		/**
		 * Hannes
		 */
		Werknemer hannes = new Werknemer();
		hannes.setNaam("Lowette");
		hannes.setVoornaam("Hannes");
		hannes.setEmail("hanneslowette@live.be");
		Adres hannesAdres = new Adres("Sesamstraat", "420", "xxx", "6969",
				"Swagville");
		hannes.setGeboortedatum(LocalDate.parse("1911-apr-20", formatter));

		/**
		 * Thomas
		 */
		Werknemer thomas = new Werknemer();
		thomas.setNaam("Verhulst");
		thomas.setVoornaam("Thomas");
		thomas.setEmail("thomas@rothbard");
		Adres thomasAdres = new Adres("Kareloomstraat", "xxx", "aa", "1111",
				"Geheim");
		thomas.setGeboortedatum(LocalDate.parse("1985-jan-01", formatter));

		/**
		 * Yannick
		 */
		Werknemer yannick = new Werknemer();
		yannick.setNaam("Claes");
		yannick.setVoornaam("Yannick");
		yannick.setEmail("yannickclaes90@gmail.com");
		Adres yannickAdres = new Adres("Duwijckstraat", "18", "", "2500",
				"Lier");
		yannick.setGeboortedatum(LocalDate.parse("1990-jul-07", formatter));

		/**
		 * adressen persisten
		 */
		a.setAdres(aAdres);
		em.persist(aAdres);
		brent.setAdres(brentAdres);
		em.persist(brentAdres);
		koen.setAdres(koenAdres);
		em.persist(koenAdres);
		hannes.setAdres(hannesAdres);
		em.persist(hannesAdres);
		thomas.setAdres(thomasAdres);
		em.persist(thomasAdres);
		yannick.setAdres(yannickAdres);
		em.persist(yannickAdres);

		/**
		 * teams
		 */
		Team HR = new Team();
		HR.setNaam("Human Resources");
		HR.setCode("HR");
		em.persist(HR);

		Team teamRed = new Team();
		teamRed.setNaam("Team Red");
		teamRed.setCode("WIN");
		em.persist(teamRed);

		Team teamBlue = new Team();
		teamBlue.setNaam("Team Blue");
		teamBlue.setCode("LOSE");
		em.persist(teamBlue);
		
		/**
		 * werknemers aan teams toevoegen
		 */
		teamRed.addWerknemer(brent);
		teamRed.addWerknemer(hannes);
		HR.addWerknemer(thomas);
		HR.addWerknemer(a);
		teamBlue.addWerknemer(koen);
		teamBlue.addWerknemer(yannick);
		
		
		/**
		 * teamverantwoordelijken
		 */
		
		teamRed.setTeamverantwoordelijke(brent);
		HR.setTeamverantwoordelijke(thomas);
		teamBlue.setTeamverantwoordelijke(koen);
		
		em.persist(teamRed);
		em.persist(HR);
		em.persist(teamBlue);

		/**
		 * credentials
		 */
		Credentials credentialsA = new Credentials();
		credentialsA.setUsername("a");
		credentialsA.setPassword(DigestUtils.md5Hex("a"));
		credentialsA.setWerknemer(a);
		
		
		Credentials credentialsBrent = new Credentials();
		credentialsBrent.setUsername("brent");
		credentialsBrent.setPassword(DigestUtils.md5Hex("brent"));
		credentialsBrent.setWerknemer(brent);

		Credentials credentialsKoen = new Credentials();
		credentialsKoen.setUsername("koen");
		credentialsKoen.setPassword(DigestUtils.md5Hex("koen"));
		credentialsKoen.setWerknemer(koen);

		Credentials credentialsHannes = new Credentials();
		credentialsHannes.setUsername("hannes");
		credentialsHannes.setPassword(DigestUtils.md5Hex("hannes"));
		credentialsHannes.setWerknemer(hannes);

		Credentials credentialsThomas = new Credentials();
		credentialsThomas.setUsername("thomas");
		credentialsThomas.setPassword(DigestUtils.md5Hex("thomas"));
		credentialsThomas.setWerknemer(thomas);

		Credentials credentialsYannick = new Credentials();
		credentialsYannick.setUsername("yannick");
		credentialsYannick.setPassword(DigestUtils.md5Hex("yannick"));
		credentialsYannick.setWerknemer(yannick);


		/**
		 * voorbeeld verlofaanvragen
		 */
		VerlofAanvraag vaYannick1 = new VerlofAanvraag();
		vaYannick1.setStartDatum(LocalDate.parse("1995-apr-05",
				formatter));
		vaYannick1.setEindDatum(LocalDate.parse("1995-apr-15",
				formatter));
		vaYannick1.setAanvraagDatum(LocalDate.parse("1995-apr-10",
				formatter));
		vaYannick1.setWerknemer(yannick);

		VerlofAanvraag vaYannick2 = new VerlofAanvraag();
		vaYannick2.setStartDatum(LocalDate.parse("2003-dec-12",
				formatter));
		vaYannick2.setEindDatum(LocalDate.parse("2003-dec-20",
				formatter));
		vaYannick2.setAanvraagDatum(LocalDate.parse("2003-dec-29",
				formatter));
		vaYannick2.setWerknemer(yannick);
		
		VerlofAanvraag va = new VerlofAanvraag();
		va.setStartDatum(LocalDate.parse("2014-apr-01", formatter));
		va.setEindDatum(LocalDate.parse("2014-apr-30", formatter));
		va.setWerknemer(koen);

		
		
		/**
		 * feestdag
		 */
		
		Feestdag f = new Feestdag();
		f.setStartDatum(LocalDate.parse("2015-dec-25", formatter));
		f.setOmschrijving("Kerstmis");
		em.persist(f);
		
		/**
		 * collectief verlof
		 */
		
		CollectiefVerlof cv = new CollectiefVerlof();
		cv.setStartDatum(LocalDate.parse("2014-nov-01", formatter));
		cv.setEindDatum(LocalDate.parse("2014-nov-01", formatter));
		em.persist(cv);
		
		/**
		 * persisting
		 */
		
		em.persist(a);
		em.persist(credentialsA);
		
		em.persist(brent);
		em.persist(credentialsBrent);
		
		em.persist(koen);
		em.persist(credentialsKoen);
		
		em.persist(hannes);
		em.persist(credentialsHannes);
		
		em.persist(thomas);
		em.persist(credentialsThomas);
		
		em.persist(yannick);
		em.persist(credentialsYannick);
		em.persist(vaYannick1);
		em.persist(vaYannick2);
		
		
		/**
		 * jaarlijkse verloven
		 */
		
		JaarlijksVerlof jv2014 = new JaarlijksVerlof();
		jv2014.setJaar(2014);
		jv2014.setAantalDagen(20);

		JaarlijksVerlof jv2014a = new JaarlijksVerlof();
		jv2014a.setJaar(2014);
		jv2014a.setAantalDagen(25);

		JaarlijksVerlof jv2015 = new JaarlijksVerlof();
		jv2015.setJaar(2015);
		jv2015.setAantalDagen(30);

//		em.persist(jv2014);
//		em.persist(jv2014a);
//		em.persist(jv2015);
		
		hannes.addJaarlijksVerlof(jv2014);
		koen.addJaarlijksVerlof(jv2014a);
		koen.addJaarlijksVerlof(jv2015);
		

		/**
		 * commit & close
		 */
		tx.commit();

		em.close();
		emf.close();

	}

}
