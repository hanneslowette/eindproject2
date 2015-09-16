package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.Team;
import org.betavzw.util.Filter;
import org.betavzw.util.Queries;

@ApplicationScoped
public class TeamBean extends AbstractBean<Team> implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<Team> get(Filter... filters) {
		return Queries.create(super.getEntityManager(), Team.class, filters).getResultList();
	}

}