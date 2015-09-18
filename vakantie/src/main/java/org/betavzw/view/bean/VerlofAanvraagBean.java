package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;

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
		return new QueryBuilder().addFilters(filters)
				.build(super.getEntityManager(), VerlofAanvraag.class).getResultList();
	}

	@Override
	public List<VerlofAanvraag> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), VerlofAanvraag.class).getResultList();
	}

}