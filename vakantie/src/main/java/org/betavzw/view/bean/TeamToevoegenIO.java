package org.betavzw.view.bean;

//import java.io.;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.ejb.TeamEJB;
import org.betavzw.ejb.WerknemerEJB;
import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;

/**
 * Voegt 
 * 
 * @author user104
 *
 */
@Named("teamToevoegen")
@SessionScoped public class TeamToevoegenIO implements Serializable{

	/**
	 * De versie ID van de 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private String naam;
	
	/**
	 * 
	 */
	@Inject private TeamEJB team;
	
	/**
	 * 
	 */
	@Inject private WerknemerEJB werknemerEJB;
	
	public String voegTeamToe() {
		
		Team t = new Team();
		t.setNaam(naam);
		team.aanmaken(t);
		
		return "home";
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public List<Werknemer> getWerknemers() {
		return werknemerEJB.getWerknemers();
	}
	
	

}
