package org.betavzw.mains;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.betavzw.entity.Adres;
import org.betavzw.entity.CollectiefVerlof;
import org.betavzw.entity.Credentials;
import org.betavzw.entity.Feestdag;
import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.entity.Team;
import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.AccountType;
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

		Werknemer koen = new Werknemer();
		koen.setNaam("De Voegt");
		koen.setVoornaam("Koen");

		Adres adres = new Adres();
		adres.setStraat("Zuidewendelaan");
		adres.setHuisnummer("7");
		adres.setBusnummer("");
		adres.setGemeente("Hoboken");
		adres.setPostcode("2660");

		em.persist(adres);
		koen.setAdres(adres);
		koen.setEmail("koen@devoegt.be");

		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MMM-dd");
		LocalDate date = LocalDate.parse("1980-apr-13", formatter);
		koen.setGeboortedatum(date);

		Team teamRed = new Team();
		teamRed.setNaam("Team Red");
		// teamRed.setCode("WIN");
		em.persist(teamRed);

		Team teamBlue = new Team();
		teamBlue.setNaam("Team Blue");
		// teamBlue.setCode("LOSE");
		em.persist(teamBlue);

		Werknemer thomas = new Werknemer();
		thomas.setVoornaam("Thomas");
		em.persist(thomas);

		teamRed.setTeamverantwoordelijke(thomas);

		Werknemer hannes = new Werknemer();
		hannes.setVoornaam("Hannes");
		hannes.setNaam("Lowette");
		teamRed.addWerknemer(hannes);
		em.persist(hannes);

		Adres adresJorik = new Adres();
		adresJorik.setStraat("Klaprozenweg");
		adresJorik.setHuisnummer("53");
		adresJorik.setBusnummer("b");
		adresJorik.setGemeente("Hoboken");
		adresJorik.setPostcode("2660");

		Werknemer jorik = new Werknemer();
		jorik.setVoornaam("Jorik");
		jorik.setNaam("Janssens");
		date = LocalDate.parse("1985-apr-10", formatter);
		jorik.setGeboortedatum(date);
		jorik.setEmail("jorikjanssens@hotmail.com");
		jorik.setAdres(adresJorik);
		jorik.setTeam(teamBlue);

		Credentials credentialsJorik = new Credentials();
		credentialsJorik.setUsername("Jorik");
		credentialsJorik.setPassword("Jorik");
		credentialsJorik.setType(AccountType.WERKNEMER);
		credentialsJorik.setWerknemer(jorik);

		em.persist(jorik);
		em.persist(adresJorik);
		em.persist(credentialsJorik);

		Adres adresYannick = new Adres();
		adresYannick.setStraat("Duwijckstraat");
		adresYannick.setHuisnummer("18");
		adresYannick.setBusnummer("");
		adresYannick.setGemeente("Lier");
		adresYannick.setPostcode("2500");

		Werknemer yannick = new Werknemer();
		yannick.setVoornaam("Yannick");
		yannick.setNaam("Claes");
		date = LocalDate.parse("1990-jul-07", formatter);
		yannick.setGeboortedatum(date);
		yannick.setEmail("yannickclaes90@gmail.com");
		yannick.setAdres(adresYannick);
		yannick.setTeam(teamRed);

		Credentials credentialsYannick = new Credentials();
		credentialsYannick.setUsername("Yannick");
		credentialsYannick.setPassword("Yannick");
		credentialsYannick.setType(AccountType.ADMINISTRATOR);
		credentialsYannick.setWerknemer(yannick);

		VerlofAanvraag verlofAanvraagYannick1 = new VerlofAanvraag();
		date = LocalDate.parse("1995-apr-05", formatter);
		verlofAanvraagYannick1.setStartDatum(date);
		date = LocalDate.parse("1995-apr-15", formatter);
		verlofAanvraagYannick1.setEindDatum(date);
		date = LocalDate.parse("1995-apr-10", formatter);
		verlofAanvraagYannick1.setAanvraagDatum(date);
		verlofAanvraagYannick1.setWerknemer(yannick);

		VerlofAanvraag verlofAanvraagYannick2 = new VerlofAanvraag();
		date = LocalDate.parse("2003-dec-12", formatter);
		verlofAanvraagYannick2.setStartDatum(date);
		date = LocalDate.parse("2003-dec-20", formatter);
		verlofAanvraagYannick2.setEindDatum(date);
		date = LocalDate.parse("2003-dec-29", formatter);
		verlofAanvraagYannick2.setAanvraagDatum(date);
		verlofAanvraagYannick2.setWerknemer(yannick);

		em.persist(yannick);
		em.persist(adresYannick);
		em.persist(credentialsYannick);
		em.persist(verlofAanvraagYannick1);
		em.persist(verlofAanvraagYannick2);

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
		// hannes.addJaarlijksVerlof(jv2015);

		VerlofAanvraag va = new VerlofAanvraag();
		formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
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
