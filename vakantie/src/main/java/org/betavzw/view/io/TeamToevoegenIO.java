package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

/**
 * Voegt 
 * 
 * @author user104
 *
 */
@Named("teamToevoegenIO")
@SessionScoped public class TeamToevoegenIO implements Serializable{

	/**
	 * De versie ID van het geserializeerd object
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * De naam van het te invoeren team
	 */
	private String naam;
	
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
	
	public String voegTeamToe() {
		Team t = new Team();
		t.setNaam(naam);
		team_bean.offer(t);
		return View.HOME;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public List<Werknemer> getWerknemers() {
		return werknemer_bean.get();
	}

	public int getVerantwoordelijkeId() {
		return verantwoordelijkeId;
	}

	public void setVerantwoordelijkeId(int verantwoordelijkeId) {
		this.verantwoordelijkeId = verantwoordelijkeId;
	}

}