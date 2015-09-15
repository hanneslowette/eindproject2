package org.betavzw.ejb;

import java.time.LocalDate;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Toestand;

public interface VerlofAanvraagInterface {

	VerlofAanvraag verlofAanmaken(VerlofAanvraag verlofAanvraag);

	VerlofAanvraag aanmaken(LocalDate startDatum, LocalDate eindDatum,
			LocalDate aanvraagDatum, Toestand toestand, Werknemer werknemer);

	VerlofAanvraag getVerlofAanvraag(int id);

	LocalDate getStartDatum();

	LocalDate getEindDatum();

	void setToestand(String toestand);

	Toestand getToestand();
	
}
