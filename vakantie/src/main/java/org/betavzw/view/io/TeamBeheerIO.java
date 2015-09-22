package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

@Named("team_beheer")
@SessionScoped
public class TeamBeheerIO implements Serializable {

	/**
	 * De versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Inject private Bean<Team> teams;

	/**
	 * 
	 */
	@Inject private Bean<Werknemer> werknemers;

	/**
	 * Het team dat bewerkt wordt.
	 */
	private Team team;
	
	/**
	 * De naam van het team
	 */
	private String naam;

	/**
	 * De id van de verantwoordelijke
	 */
	private int verantwoordelijkeId;

	/**
	 * Opent deze pagina met het gegeven team als het team dat bewerkt wordt
	 * 
	 * @param id
	 * @return
	 */
	public String beheer(int id) {
		this.team = teams.getSingle(new Filter("id", id));
		return View.TEAM_BEHEER;
	}

	/**
	 * Update een team
	 * 
	 * @return
	 */
	public String update() {
		this.team.setNaam(naam);
		this.team.setTeamverantwoordelijke(werknemers.getSingle(new Filter("personeelsNr", verantwoordelijkeId)));
		this.teams.update(team);
		return View.HOME;
	}

	/**
	 * Selecteerd een team en vraagt een bevestiging om te verwijderen
	 * 
	 * @param id
	 * @return
	 */
	public String select(int id) {
		this.team = teams.getSingle(new Filter("id", id));
		return "team_bevestiging";
	}

	/**
	 * Wordt opgeroepen wanneer de gebruiker zijn keuze bevestigd tijdens het verwijderen van de entity
	 * 
	 * @param delete
	 * @return
	 */
	public String confirm(boolean delete) {
		if (delete) {
			if (team.getTeamLeden().isEmpty() && team.getTeamverantwoordelijke() == null) {
				teams.delete(team);
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Team kon niet worden verwijderd: Er waren nog teamleden aanwezig in dit team.", "update geslaagd"));
			}
		}
		return View.HOME;
	}

	public String getCode() {
		return team.getCode();
	}
	
	public String getNaam() {
		return team.getNaam();
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setVerantwoordelijkeId(int id) {
		this.verantwoordelijkeId = id;
	}

	public int getVerantwoordelijkeId() {
		return verantwoordelijkeId;
	}

	public Team getTeam() {
		return team;
	}

}