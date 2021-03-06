package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.Team;
//import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

public interface ITeam {
	// geeft een lijst met teams terug, om te gebruiken in werknemertoevoegen
	// public List<Team> getTeams();

	// geeft een team terug, om te gebruiken in zoekfuncties
	// public Team getTeam(String naam);

	/**
	 * 
	 * 
	 * @param filter
	 * @return
	 */
	public List<Team> getTeams(Filter... filter);

	// voegt een team toe in de database
	public void voegTeamToe(Team team);

	// verwijderen van team uit database
	public void verwijderTeam(Team team);

	// wijziging van team doorvioeren ind atabase
	public void wijzigTeam(Team team);

}
