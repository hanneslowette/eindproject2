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
	

}
