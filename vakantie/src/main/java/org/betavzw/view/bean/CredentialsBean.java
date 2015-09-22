package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.Credentials;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class CredentialsBean extends AbstractBean<Credentials> {

	@Override
	public List<Credentials> get(QueryBuilder builder) {
		return builder.build(super.getEntityManager(), Credentials.class).getResultList();
	}

}