package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.Team;

public interface ITeam {
	// geeft een lijst met teams terug, om te gebruiken in werknemertoevoegen
	public List<Team> getTeams();

	// geeft een team terug, om te gebruiken in zoekfuncties
	public Team getTeam(String naam);

}
