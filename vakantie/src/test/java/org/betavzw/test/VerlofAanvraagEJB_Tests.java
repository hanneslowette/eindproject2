package org.betavzw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.betavzw.ejb.VerlofAanvraagEJB;
import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Toestand;
import org.junit.Test;

public class VerlofAanvraagEJB_Tests {

	private VerlofAanvraagEJB verlofAanvraagEJB = new VerlofAanvraagEJB();

	LocalDate startDatum = LocalDate.now();
	LocalDate eindDatum = LocalDate.now().plusDays(1);
	LocalDate aanvraagDatum = LocalDate.now().minusDays(3);

	Toestand toestand = Toestand.PENDING;
	Werknemer werknemer = new Werknemer();

	@Test
	public void testVerlofAanmaken() {
		VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
		assertEquals(" ", verlofAanvraagEJB.verlofAanmaken(verlofAanvraag),
				verlofAanvraag);
	}

	@Test
	public void testAanmaken5Variables() {

		VerlofAanvraag verlofAanvraag = verlofAanvraagEJB.aanmaken(startDatum,
				eindDatum, aanvraagDatum, toestand, werknemer);
		assertEquals("failure - startDatum doesn't match",
				verlofAanvraag.getStartDatum(), startDatum);
		assertEquals("failure - eindDatum doesn't match",
				verlofAanvraag.getEindDatum(), eindDatum);
		assertEquals("failure - aanvraagDatum doesn't match",
				verlofAanvraag.getAanvraagDatum(), aanvraagDatum);
		assertEquals("failure - toestand doesn't match",
				verlofAanvraag.getToestand(), toestand);
		assertEquals("failure - werknemer doesn't match",
				verlofAanvraag.getWerknemer(), werknemer);

	}

	// commit

	// getVerlofAanvraagId

	@Test
	public void testGetStartDatum() {
		VerlofAanvraag verlofAanvraag = verlofAanvraagEJB.aanmaken(startDatum,
				eindDatum, aanvraagDatum, toestand, werknemer);
		assertEquals(verlofAanvraag.getStartDatum(), startDatum);
	}

	@Test
	public void testEindDatum() {
		VerlofAanvraag verlofAanvraag = verlofAanvraagEJB.aanmaken(startDatum,
				eindDatum, aanvraagDatum, toestand, werknemer);
		assertEquals(verlofAanvraag.getEindDatum(), eindDatum);
	}

	@Test
	public void testGetToestand() {
		verlofAanvraagEJB.setToestand("ACCEPTED");
		assertEquals("failure - toestand doesn't match",
				verlofAanvraagEJB.getToestand(), Toestand.ACCEPTED);
	}

	@Test
	public void testSetToestand() {
		assertTrue("Provide setter: setToestand(Toestand)", false);
	}

}
