package org.betavzw.view.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
import org.betavzw.util.Filter;
import org.betavzw.view.bean.Bean;

@Named
@RequestScoped
public class TeamOpvragenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * De bean die verantwoordelijk is voor teams
	 */
	@Inject private Bean<Team> bean;
	
	/**
	 * De lijst met gevonden teams
	 */
	private List<Team> teams;
	
	/**
	 * De naam van het team
	 */
	private String naam;
	
	/**
	 * De verantwoordelijke van het team
	 */
	private String verantwoordelijke;
	
	/**
	 * De unieke code van het team
	 */
	private String code;
	
	/**
	 * De actie die gebeurt wanneer de gebruiker op "Zoek" klikt in de view
	 */
	public void zoek() {
		List<Filter> tmp = new ArrayList<Filter>();
		if (!naam.equals("")) {
			tmp.add(new Filter("naam", this.naam));
		}
		if (!code.equals("")) {
			tmp.add(new Filter("code", this.code));
		}
		if (!verantwoordelijke.equals("")) {
			tmp.add(new Filter("verantwoordelijke", this.verantwoordelijke));
		}
		
		teams = bean.get(tmp);
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getVerantwoordelijke() {
		return verantwoordelijke;
	}

	public void setVerantwoordelijke(String verantwoordelijke) {
		this.verantwoordelijke = verantwoordelijke;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}