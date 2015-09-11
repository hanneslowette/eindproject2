package org.betavzw.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String naam;
	private String code;
	@ManyToOne()
	private Werknemer teamverantwoordelijke;
	@OneToMany(mappedBy="team")
	private Set<Werknemer> teamLeden = new HashSet<Werknemer>();

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
		teamverantwoordelijke.setTeam(this);
		this.teamverantwoordelijke = teamverantwoordelijke;
	}

	/**
	 * Doesn't update werknemers just adds a list to this team
	 * **/
	public Set<Werknemer> getTeamLeden() {
		return teamLeden;
	}

	public void setTeamLeden(Set<Werknemer> teamLeden) {
		this.teamLeden = teamLeden;
	}

	public void addWerknemer(Werknemer teamlid) {
		teamlid.setTeam(this);
		teamLeden.add(teamlid);
	}

}
