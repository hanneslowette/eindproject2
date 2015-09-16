package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;

/**
 * Session Bean implementation class VerlofAanvraagEJB
 */

@ApplicationScoped
public class VerlofAanvraagBean extends AbstractBean<VerlofAanvraag> implements Serializable {

	/**
	 * Default Version id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<VerlofAanvraag> get(Filter... filters) {
		return null;
	}

}
