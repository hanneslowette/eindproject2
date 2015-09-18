package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.Credentials;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class CredentialsBean extends AbstractBean<Credentials> {

	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<Credentials> get(Filter... filters) {
		return new QueryBuilder().addFilters(filters)
				.build(super.getEntityManager(), Credentials.class).getResultList();
	}

	@Override
	public List<Credentials> get(QueryBuilder builder) {
		return builder.build(super.getEntityManager(), Credentials.class).getResultList();
	}

}