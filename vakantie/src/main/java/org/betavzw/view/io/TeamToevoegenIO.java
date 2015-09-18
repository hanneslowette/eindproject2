package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
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
	 * De bean die verantwoordelijk is voor teams
	 */
	@Inject private Bean<Team> team_bean;
	
	public String add() {
		Team team = new Team();
		
		/*
		 * Vul de waarden van het team in
		 */
		team.setNaam(this.naam);
		
		/*
		 * Voeg het team toe aan de databank
		 */
		team_bean.offer(team);
		
		/*
		 * Ga naar de view om teams op te vragen
		 */
		return View.TEAM_OPVRAGEN;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

}