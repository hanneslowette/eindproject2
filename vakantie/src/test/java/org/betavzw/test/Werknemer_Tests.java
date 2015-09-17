package org.betavzw.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Werknemer;
import org.junit.Test;

public class Werknemer_Tests {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	
	Werknemer werknemer = new Werknemer();
	
	@Test
	public void Naam_Test() {
		String naam = "TestNaam";
		werknemer.setNaam(naam);
		assertEquals("failure - Naam doesn't match", werknemer.getNaam(), naam);
	}

	@Test
	public void Voornaam_Test() {
		String voornaam = "TestVoornaam";
		werknemer.setVoornaam(voornaam);
		assertEquals("failure - Voornaam doesn't match", werknemer.getVoornaam(), voornaam);
	}
	
	@Test
	public void Email_Test() {
		String email = "test@server.com";
		werknemer.setEmail(email);
		assertEquals("failure - Email doesn't match", werknemer.getEmail(), email);
	}
	
	@Test
	public void Geboortedatum_Test() {
		LocalDate date = LocalDate.parse("1980-apr-13", formatter);
		werknemer.setGeboortedatum(date);
		assertEquals("failure - Geboortedatum doesn't match", werknemer.getGeboortedatum(), date);
	}
	
	@Test
	public void Adres_Test() {
		Adres adres = new Adres();
		werknemer.setAdres(adres);
		assertEquals("failure - Adres doesn't match", werknemer.getAdres(), adres);
	}
	
}
