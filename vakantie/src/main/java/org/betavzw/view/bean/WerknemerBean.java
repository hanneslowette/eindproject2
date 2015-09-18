package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class WerknemerBean extends AbstractBean<Werknemer> implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Werknemer> get(Filter... filters) {
		return new QueryBuilder().addFilters(filters)
				.build(super.getEntityManager(), Werknemer.class).getResultList();
	}

	@Override
	public List<Werknemer> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), Werknemer.class).getResultList();
	}

}