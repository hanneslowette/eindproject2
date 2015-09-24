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

public class VoorstellingProject {

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
		 * admin
		 */
		Werknemer admin = new Werknemer();
		admin.setNaam("admin");
		admin.setVoornaam("admin");
		admin.setEmail("brentcourtois@gmail.com");
		Adres adresAdmin = new Adres("straat", "nummer", "busnummer",
				"postcode", "gemeente");
		admin.setGeboortedatum(LocalDate.parse("2000-jan-01", formatter));

		/**
		 * adressen persisten
		 */
		admin.setAdres(adresAdmin);
		em.persist(adresAdmin);

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
		HR.addWerknemer(admin);

		/**
		 * teamverantwoordelijken
		 */
		HR.setTeamverantwoordelijke(admin);

		/**
		 * teams persisten
		 */
		em.persist(HR);
		em.persist(teamRed);
		em.persist(teamBlue);

		/**
		 * credentials
		 */
		Credentials credentialsAdmin = new Credentials();
		credentialsAdmin.setUsername("admin");
		credentialsAdmin.setPassword(DigestUtils.md5Hex("admin"));
		credentialsAdmin.setWerknemer(admin);

		/**
		 * voorbeeld verlofaanvragen
		 */
		VerlofAanvraag verlofAanvraagAdmin1 = new VerlofAanvraag();
		verlofAanvraagAdmin1.setAanvraagDatum(LocalDate.parse("1995-jan-10",
				formatter));
		verlofAanvraagAdmin1.setStartDatum(LocalDate.parse("1995-apr-05",
				formatter));
		verlofAanvraagAdmin1.setEindDatum(LocalDate.parse("1995-apr-15",
				formatter));
		verlofAanvraagAdmin1.setWerknemer(admin);

		VerlofAanvraag verlofAanvraagAdmin2 = new VerlofAanvraag();
		verlofAanvraagAdmin2.setAanvraagDatum(LocalDate.parse("2003-nov-13",
				formatter));
		verlofAanvraagAdmin2.setStartDatum(LocalDate.parse("2003-dec-12",
				formatter));
		verlofAanvraagAdmin2.setEindDatum(LocalDate.parse("2003-dec-20",
				formatter));

		verlofAanvraagAdmin2.setWerknemer(admin);

		VerlofAanvraag verlofAanvraagAdmin3 = new VerlofAanvraag();
		verlofAanvraagAdmin3.setAanvraagDatum(LocalDate.parse("2014-jan-05",
				formatter));
		verlofAanvraagAdmin3.setStartDatum(LocalDate.parse("2014-apr-01",
				formatter));
		verlofAanvraagAdmin3.setEindDatum(LocalDate.parse("2014-apr-30",
				formatter));
		verlofAanvraagAdmin3.setWerknemer(admin);

		/**
		 * feestdag
		 */
		Feestdag kerstmis = new Feestdag();
		kerstmis.setStartDatum(LocalDate.parse("2015-dec-25", formatter));
		kerstmis.setOmschrijving("Kerstmis");
		em.persist(kerstmis);

		/**
		 * collectief verlof
		 */
		CollectiefVerlof collectiefVerlof1 = new CollectiefVerlof();
		collectiefVerlof1.setStartDatum(LocalDate.parse("2015-nov-01",
				formatter));
		collectiefVerlof1.setEindDatum(LocalDate
				.parse("2015-nov-01", formatter));
		em.persist(collectiefVerlof1);

		/**
		 * persisting
		 */
		em.persist(admin);
		em.persist(credentialsAdmin);

		/**
		 * jaarlijkse verloven
		 */
		JaarlijksVerlof jaarlijksVerlof2014a = new JaarlijksVerlof();
		jaarlijksVerlof2014a.setJaar(2014);
		jaarlijksVerlof2014a.setAantalDagen(20);

		JaarlijksVerlof jaarlijksVerlof2014b = new JaarlijksVerlof();
		jaarlijksVerlof2014b.setJaar(2014);
		jaarlijksVerlof2014b.setAantalDagen(25);

		JaarlijksVerlof jaarlijksVerlof2015a = new JaarlijksVerlof();
		jaarlijksVerlof2015a.setJaar(2015);
		jaarlijksVerlof2015a.setAantalDagen(30);

		JaarlijksVerlof jaarlijksVerlof2015b = new JaarlijksVerlof();
		jaarlijksVerlof2015b.setJaar(2015);
		jaarlijksVerlof2015b.setAantalDagen(20);

		JaarlijksVerlof jaarlijksVerlof2016 = new JaarlijksVerlof();
		jaarlijksVerlof2016.setJaar(2016);
		jaarlijksVerlof2016.setAantalDagen(30);

		admin.addJaarlijksVerlof(jaarlijksVerlof2014a);
		admin.addJaarlijksVerlof(jaarlijksVerlof2015b);
		admin.addJaarlijksVerlof(jaarlijksVerlof2016);

		/**
		 * commit & close
		 */
		tx.commit();
		em.close();
		emf.close();
	}

}
