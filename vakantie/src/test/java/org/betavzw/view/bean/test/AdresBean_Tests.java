package org.betavzw.view.bean.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.betavzw.entity.Adres;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.bean.AbstractBean;
import org.betavzw.view.bean.AdresBean;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AdresBean_Tests {

	private static EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("unitName");

	private static EntityManager manager;
	private static AdresBean adresBean = new AdresBean();
	private static EntityTransaction tx;

	private Adres adres = new Adres();

	private String straat = "Zuidwendelaan";
	private String huisnummer = "7";
	private String busnummer = "";
	private String postcode = "2660";
	private String gemeente = "Hoboken (Antwerpen)";

	@BeforeClass
	public static void masterSetup() {
		manager = emf.createEntityManager();
		tx = manager.getTransaction();
		adresBean.setEntityManager(manager);
	}

	@Before
	public void setup() throws GeboortedatumInDeToekomstException {

		tx.begin();
		Utils.purge((AbstractBean) adresBean);
		tx.commit();
		tx.begin();

		adres.setStraat(straat);
		adres.setHuisnummer(huisnummer);
		adres.setBusnummer(busnummer);
		adres.setPostcode(postcode);
		adres.setGemeente(gemeente);

	}

	@After
	public void teardown() {
		Utils.purge((AbstractBean) adresBean);
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
		adresBean.offer(adres);
		tx.commit();

		tx.begin();
		Utils.purge((AbstractBean) adresBean);
		tx.commit();

		tx.begin();
		List<Adres> list = adresBean.get();
		assertEquals("failure - Adressen not purged", list.size(), 0);

	}
}
