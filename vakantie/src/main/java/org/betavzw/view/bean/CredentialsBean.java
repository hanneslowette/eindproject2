package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.Credentials;
import org.betavzw.util.Filter;
import org.betavzw.util.Queries;

@ApplicationScoped
public class CredentialsBean extends AbstractBean<Credentials> {

	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<Credentials> get(Filter... filters) {
		return Queries.create(super.getEntityManager(), Credentials.class, filters).getResultList();
	}

}