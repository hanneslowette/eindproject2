package org.betavzw.entity.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import org.betavzw.entity.Adres;
import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.entity.Team;
import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.entity.Werknemer;
import org.junit.Test;

public class Werknemer_Tests {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
	private Werknemer werknemer = new Werknemer();
	
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
	
	@Test
	public void Team_Test() {
		Team team = new Team();
		werknemer.setTeam(team);
		assertEquals("failure - Team doesn't match", werknemer.getTeam(), team);
	}
	
	@Test
	public void JaarlijksVerlof_Test() {
		JaarlijksVerlof jv1 = new JaarlijksVerlof();
		JaarlijksVerlof jv2 = new JaarlijksVerlof();
		Set<JaarlijksVerlof> jvList = new HashSet<JaarlijksVerlof>();
		jvList.add(jv1);
		jvList.add(jv2);
		
		werknemer.setJaarlijkseVerloven(jvList);
		assertEquals("failure - JaarlijkseVerloven don't match", werknemer.getJaarlijkseVerloven(), jvList);
	}
	
	@Test
	public void AddJaarlijksVerlof_Test() {
		JaarlijksVerlof jv = new JaarlijksVerlof();
		werknemer.addJaarlijksVerlof(jv);
		Set<JaarlijksVerlof> list = werknemer.getJaarlijkseVerloven();
		assertTrue("failure - JaarlijksVerlof not added", list.contains(jv));
	}
	
	@Test
	public void VerlofAanvraag_Test() {
		VerlofAanvraag va1 = new VerlofAanvraag();
		VerlofAanvraag va2 = new VerlofAanvraag();
		Set<VerlofAanvraag> vaList = new HashSet<VerlofAanvraag>();
		vaList.add(va1);
		vaList.add(va2);
		
		werknemer.setVerlofAanvragen(vaList);
		assertEquals("failure - VerlofAanvragen don't match",  werknemer.getVerlofAanvragen(), vaList);
	}
	
	@Test
	public void AddVerlofAanvraag_Test() {
		VerlofAanvraag va = new VerlofAanvraag();
		werknemer.addVerlofAanvraag(va);
		Set<VerlofAanvraag> list = werknemer.getVerlofAanvragen();
		assertTrue("failure - VerlofAanvraag not added", list.contains(va));
	}
}
