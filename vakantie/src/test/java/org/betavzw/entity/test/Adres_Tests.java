package org.betavzw.entity.test;

import static org.junit.Assert.assertEquals;

import org.betavzw.entity.Adres;
import org.junit.Test;

public class Adres_Tests {
	
	private Adres adres = new Adres();
	
	@Test
	public void Straat_Test() {
		String straat = "TestStraat";
		adres.setStraat(straat);
		assertEquals("failure - Straat doesn't match", straat, adres.getStraat());
	}
	
	@Test
	public void Huisnummer_Test() {
		String huisnummer = "TestHuisnummmer";
		adres.setHuisnummer(huisnummer);
		assertEquals("failure - Huisnummer doesn't match", huisnummer, adres.getHuisnummer());
	}
	
	@Test
	public void Busnummer_Test() {
		String busnummer = "TestBusnummmer";
		adres.setBusnummer(busnummer);
		assertEquals("failure - Busnummer doesn't match", busnummer, adres.getBusnummer());
	}
	
	@Test
	public void Postcode_Test() {
		String postcode = "TestPostcode";
		adres.setPostcode(postcode);
		assertEquals("failure - Postcode doesn't match", postcode, adres.getPostcode());
	}
	
	@Test
	public void Gemeente_Test() {
		String gemeente = "TestGemeente";
		adres.setGemeente(gemeente);
		assertEquals("failure - Gemeente doesn't match", gemeente, adres.getGemeente());
	}
	
}