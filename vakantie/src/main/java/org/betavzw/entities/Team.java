package org.betavzw.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Team {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String naam;
	private String code;
	private Werknemer teamverantwoordelijke;
	private Set<Werknemer> teamLeden = new HashSet<Werknemer>();
	
	

	public Team() {
		super();
	}
	
	

	public Team(String naam) {
		super();
		this.naam = naam;
	}



	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Werknemer getTeamverantwoordelijke() {
		return teamverantwoordelijke;
	}

	public void setTeamverantwoordelijke(Werknemer teamverantwoordelijke) {
		this.teamverantwoordelijke = teamverantwoordelijke;
	}

	public Set<Werknemer> getTeamLeden() {
		return teamLeden;
	}

	public void setTeamLeden(Set<Werknemer> teamLeden) {
		this.teamLeden = teamLeden;
	}

	public void addWerknemer(Werknemer teamlid) {
		teamLeden.add(teamlid);
	}

}
