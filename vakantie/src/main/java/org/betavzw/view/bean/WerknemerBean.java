package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.QueryBuilder;

@ApplicationScoped
public class WerknemerBean extends AbstractBean<Werknemer> implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De adres bean
	 */
	@Inject private Bean<Adres> adres_bean;

	@Override
	public List<Werknemer> get(QueryBuilder query) {
		return query.build(super.getEntityManager(), Werknemer.class).getResultList();
	}

	@Override
	public void update(Werknemer entity) {
		/*
		 * Eerst het adres persisten
		 */
		adres_bean.update(entity.getAdres());
		
		/*
		 * Dan de werknemer
		 */
		super.update(entity);
	}

}