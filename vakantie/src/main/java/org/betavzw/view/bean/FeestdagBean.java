package org.betavzw.view.bean;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.betavzw.entity.Credentials;
import org.betavzw.entity.Feestdag;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class FeestdagBean extends AbstractBean<Feestdag> {

	@Override
	public List<Feestdag> get(QueryBuilder builder) {
		return builder.build(super.getEntityManager(), Feestdag.class).getResultList();
	}

}