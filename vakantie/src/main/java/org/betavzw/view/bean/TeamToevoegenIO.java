package org.betavzw.view.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.ejb.TeamEJB;
import org.betavzw.entities.Team;

@Named("verlofAanvraag")
@SessionScoped
public class TeamToevoegenIO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String naam;
	
	@EJB
	private TeamEJB team;
	
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
	
	

}
