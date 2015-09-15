package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;

public interface IWerknemer {

	// geeft een lijst met werknemers terug, om te gebruiken in
	// "opvragen werknemers"
	public List<Werknemer> getWerknemers();

	// geeft een werknemer terug, om te gebruiken in zoekfuncties
	public Werknemer getWerknemer(String naam);

}
