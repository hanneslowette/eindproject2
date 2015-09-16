package org.betavzw.view.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Toestand;

@SessionScoped
public class LoginBean implements Serializable {

	/**
	 * De versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De aangemelde werknemer
	 */
	private Werknemer werknemer;
	
	/**
	 * Het team dat de persoon beheert
	 */
	private Team team;
	
	/**
	 * toestand van deze sessie (bezoeker/werknemer/teamverantwoordelijke)
	 */
	private Toestand toestand;

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

}