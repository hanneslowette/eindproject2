package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.CollectieveSluiting;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class CollectieveSluitingBean extends AbstractBean<CollectieveSluiting> {

	@Override
	public List<CollectieveSluiting> get(QueryBuilder builder) {
		return builder.build(super.getEntityManager(), CollectieveSluiting.class).getResultList();
	}

}