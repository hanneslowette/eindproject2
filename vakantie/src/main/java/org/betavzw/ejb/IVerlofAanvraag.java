package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.util.Filter;

public interface IVerlofAanvraag {

	// toevoegen van een verlofaanvraag in de database
	public void voegVerlofAanvraagToe(VerlofAanvraag verlofAanvraag);

	// verwijderen van een verlofaanvraag uit de database
	public void verwijderVerlofAanvraag(VerlofAanvraag verlofAanvraag);

	// wijzigiging van een verlofaanvraag doorvoeren in de database
	public void wijzigVerlofAanvraag(VerlofAanvraag verlofAanvraag);

	// weergave lijst met alle verlofaanvragen
	public List<VerlofAanvraag> getVerlofAanvragen();
	
	// weergave lijst met verlofaanvragen gefilterd op kolom(veld)
	public List<VerlofAanvraag> getVerlofAanvragen(Filter... filter);
}
