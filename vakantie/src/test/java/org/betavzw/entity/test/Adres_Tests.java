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
	
	
}