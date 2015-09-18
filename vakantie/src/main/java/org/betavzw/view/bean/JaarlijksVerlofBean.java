package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.util.QueryBuilder;

/**
 * Session Bean implementation class JaarlijksVerlofEJB
 */
@ApplicationScoped
public class JaarlijksVerlofBean extends AbstractBean<JaarlijksVerlof> {

	/**
	 * De entity manager
	 */
	@PersistenceContext private EntityManager manager;

	@Override
	public List<JaarlijksVerlof> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), JaarlijksVerlof.class).getResultList();
	}

}