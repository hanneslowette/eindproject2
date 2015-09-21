package org.betavzw.view.bean.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.bean.AbstractBean;
import org.betavzw.view.bean.WerknemerBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WerknemerBean_Tests {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("unitName");

	private static EntityManager manager;
	private static WerknemerBean werknemerBean = new WerknemerBean();
	private static EntityTransaction tx;

	private DateTimeFormatter formatter = DateTimeFormatter
			.ofPattern("yyyy-MMM-dd");

	private Werknemer wn = new Werknemer();
	private Adres adres = new Adres();

	private String voornaam = "Koen";
	private String naam = "De Voegt";
	private String email = "koen@devoegt.be";
	private LocalDate date = LocalDate.parse("1980-apr-13", formatter);

	private String straat = "Zuidwendelaan";
	private String huisnummer = "7";
	private String busnummer = "";
	private String postcode = "2660";
	private String gemeente = "Hoboken (Antwerpen)";

	@BeforeClass
	public static void masterSetup() {
		manager = emf.createEntityManager();
		tx = manager.getTransaction();
		werknemerBean.setEntityManager(manager);
	}

	@Before
	public void setup() throws GeboortedatumInDeToekomstException {

		tx.begin();
		Utils.purge((AbstractBean) werknemerBean);
		tx.commit();
		tx.begin();

		wn.setVoornaam(voornaam);
		wn.setNaam(naam);
		wn.setEmail(email);
		wn.setGeboortedatum(date);

	}

	@After
	public void teardown() {
		tx.commit();
	}

	@AfterClass
	public static void masterTeardown() {
		manager.close();
		emf.close();
	}

	@Test
	public void EntityManager_open() {
		assertTrue(manager.isOpen());
	}

	@Test
	public void Purge_Test() {
		werknemerBean.offer(wn);
		werknemerBean.offer(new Werknemer("Jan", "Van Gent"));
		werknemerBean.offer(new Werknemer("Joske", "Vermeulen"));

		Utils.purge((AbstractBean) werknemerBean);
		tx.commit();

		tx.begin();
		List<Werknemer> list = werknemerBean.get();
		assertEquals("failure - Werknemers not purged", list.size(), 0);
	}

	@Test
	public void SetAdres_Test() {

		adres.setStraat(straat);
		adres.setHuisnummer(huisnummer);
		adres.setBusnummer(busnummer);
		adres.setPostcode(postcode);
		adres.setGemeente(gemeente);

		wn.setAdres(adres);

		try {
			werknemerBean.offer(wn);
			tx.commit();
			List<Werknemer> list = werknemerBean.get();
			Werknemer wn_out = list.get(0);
			Adres adres_out = wn_out.getAdres();

			assertEquals("failure - straat doesn't match", straat,
					adres_out.getStraat());
			assertEquals("failure - huisnummer doesn't match", huisnummer,
					adres_out.getHuisnummer());
			assertEquals("failure - busnummer doesn't match", busnummer,
					adres_out.getBusnummer());
			assertEquals("failure - gemeente doesn't match", gemeente,
					adres_out.getGemeente());
		} catch (RollbackException a) {
			tx.begin();
			tx.rollback();
			assertTrue("failure - unable to retrieve Adres", false);
		}
		tx.begin();
	}
	
	@Test
	public void removeAdres() {
		assertTrue("check if able to delete with adres constraint", false);
		
	}

	@Test
	public void WerknemerOffer_Test() {

		werknemerBean.offer(wn);
		tx.commit();

		tx.begin();
		List<Werknemer> list = werknemerBean.get();
		assertTrue("failure - Werknemer not added", list.contains(wn));
		Werknemer wn_out = list.get(0);
		assertEquals("failure - Voornaam doesn't match", voornaam,
				wn_out.getVoornaam());
		assertEquals("failure - Naam doesn't match", naam, wn_out.getNaam());
		assertEquals("failure - Email doesn't match", email, wn_out.getEmail());
		assertEquals("failure - Geboortedatum doesn't match", date,
				wn_out.getGeboortedatum());
	}

	@Test
	public void WerknemerDelete_Test() {

		werknemerBean.offer(wn);
		tx.commit();

		tx.begin();
		werknemerBean.delete(wn);
		tx.commit();

		tx.begin();
		List<Werknemer> list = werknemerBean.get();
		assertEquals("failure - Werknemer not deleted", list.size(), 0);

	}

//	@Test
//	public void WerknemerNaam_KleinerDan_personeelsNr_Test() {
//		Werknemer werknemer1 = new Werknemer("A", "A");		
//		Werknemer werknemer2 = new Werknemer("A", "A");
//		tx.commit();
//		System.out.println(werknemer1.getPersoneelsNr());
//		
//		tx.begin();
//		List<Werknemer> list = werknemerBean.get();
//		
//		//list.g
//		
//		int result = werknemer1.compareTo(werknemer2);
//		assertTrue("failure - personeelsNr not less than: " + result,  result  <= -1);
//	}
//	
//	@Test
//	public void WerknemerNaam_GroterDan_personeelsNr_Test() {
//		Werknemer werknemer1 = new Werknemer("A", "A");		
//		Werknemer werknemer2 = new Werknemer("A", "A");
//		int result = werknemer1.compareTo(werknemer2);
//		assertTrue("failure - personeelsNr not greater than: " + result,  result >= 1);
//	}
	
}
