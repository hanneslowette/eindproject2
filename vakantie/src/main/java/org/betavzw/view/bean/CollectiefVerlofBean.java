package org.betavzw.view.bean;

import java.util.List;


import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.CollectiefVerlof;
import org.betavzw.util.QueryBuilder;

/**
 * Session Bean implementation class 
 */
@ApplicationScoped
public class CollectiefVerlofBean extends AbstractBean<CollectiefVerlof> {

	@Override
	public List<CollectiefVerlof> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), CollectiefVerlof.class).getResultList();
	}

}