package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

/**
 * Voegt 
 * 
 * @author user104
 *
 */
@Named("team_toevoegen")
@RequestScoped public class TeamToevoegenIO implements Serializable{

	/**
	 * De versie ID van het geserializeerd object
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * De naam van het te invoeren team
	 */
	private String naam;
	
	/**
	 * De door de gebruiker opgegeven code
	 */
	private String code;
	
	/**
	 * De verantwoordelijke van het team
	 */
	private int verantwoordelijkeId;
	
	/**
	 * De bean die verantwoordelijk is voor teams
	 */
	@Inject private Bean<Team> team_bean;
	
	/**
	 * De bean die verantwoordelijk is vooor werknemers
	 */
	@Inject private Bean<Werknemer> werknemer_bean;
	
	public String add() {
		Team team = new Team();
		
		/*
		 * Vul de waarden van het team in
		 */
		team.setNaam(this.naam);
		team.setCode(this.code);
		team.setTeamverantwoordelijke(werknemer_bean.getSingle(new Filter("personeelsNr", verantwoordelijkeId)));
		
		/*
		 * Voeg het team toe aan de databank
		 */
		team_bean.offer(team);
		
		/*
		 * Ga naar de view om teams op te vragen
		 */
		return View.TEAM_OPVRAGEN;
	}
	
	public List<Werknemer> getWerknemers() {
		return werknemer_bean.get();
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getVerantwoordelijkeId() {
		return verantwoordelijkeId;
	}

	public void setVerantwoordelijkeId(int verantwoordelijkeId) {
		this.verantwoordelijkeId = verantwoordelijkeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}