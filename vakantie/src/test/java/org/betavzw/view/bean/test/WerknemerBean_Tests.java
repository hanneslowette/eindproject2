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

import org.betavzw.entity.Werknemer;
import org.betavzw.view.bean.WerknemerBean;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WerknemerBean_Tests {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("unitName");
	
	private static EntityManager manager = emf.createEntityManager();
	private static WerknemerBean werknemerBean = new WerknemerBean();
	private static EntityTransaction tx;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	
	private Werknemer wn = new Werknemer();
	
	private String voornaam = "Koen";
	private String naam = "De Voegt";
	private String email = "koen@devoegt.be";
	private LocalDate date = LocalDate.parse("1980-apr-13", formatter);
		
	@BeforeClass
	public static void masterSetup() {
		tx = manager.getTransaction();
		werknemerBean.setEntityManager(manager);
	}
	
	@Before
	public void setup() {
		wn.setVoornaam(voornaam);
		wn.setNaam(naam);
		wn.setEmail(email);		

		wn.setGeboortedatum(date);		
	}
	
	@AfterClass
	public static void masterTeardown() {
		manager.close();
		emf.close();
	} 
	
	@Test
	public void test() {
		assertTrue(manager.isOpen());
	}
	
	@Test
	public void WerknemerOffer_Test() {
		
		tx.begin();
		werknemerBean.offer(wn);
		tx.commit();
		
		tx.begin();
		List<Werknemer> list = werknemerBean.get();
		assertTrue("failure - Werknemer not added", list.contains(wn));
		Werknemer wn_out = list.get(0);
		assertEquals("failure - Voornaam doesn't match", wn_out.getVoornaam(), voornaam);
		assertEquals("failure - Naam doesn't match", wn_out.getNaam(), naam);
		assertEquals("failure - email doesn't match", wn_out.getEmail(), email);
		assertEquals("failure - Geboortedatum doesn't match", wn_out.getGeboortedatum(), date);
		werknemerBean.delete(wn_out);
		tx.commit();
	}

	@Test
	public void WerknemerDelete_Test() {
		
		tx.begin();
		werknemerBean.offer(wn);
		tx.commit();
		
		tx.begin();
		werknemerBean.delete(wn);
		tx.commit();
		
		tx.begin();
		List<Werknemer> list = werknemerBean.get();
		assertEquals("failure - Werknemer not deleted", list.size(), 0);
		tx.commit();
		
	}
	
}
