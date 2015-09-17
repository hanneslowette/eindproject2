package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
import org.betavzw.util.Filter;
import org.betavzw.view.bean.Bean;

/**
 * 
 * @author user104
 *
 */
@Named("team")
@RequestScoped
public class TeamIO implements Serializable {

	/**
	 * Versie ID van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean voor het beheren van de teams in de databank
	 */
	@Inject private Bean<Team> bean;
	
	/**
	 * De zoek query
	 */
	private String query;

	/**
	 * 
	 */
	public List<Team> search() {
		if (query == null || query.isEmpty()) {
			return null;
		}
		System.out.println(query);
		return bean.get(new Filter("naam", query));
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

}