package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.util.Filter;

public interface IVerlofAanvraag {

	/**
	 * 
	 * 
	 * @param filter
	 * @return
	 */
	public List<VerlofAanvraag> getVerlofAanvragen(Filter... filter);

	// voegt een verlofaanvraag toe in de database
	public void voegVerlofAanvraagToe(VerlofAanvraag verlofAanvraag);

	// verwijderen van verlofaanvraag uit de database
	public void verwijderVerlofAanvraag(VerlofAanvraag verlofAanvraag);

	// wijziging van verlofaanvraag doorvoeren in de database
	public void wijzigVerlofAanvraag(VerlofAanvraag verlofAanvraag);

}
