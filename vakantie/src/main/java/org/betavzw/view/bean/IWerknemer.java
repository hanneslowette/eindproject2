package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

public interface IWerknemer {

	/**
	 * 
	 * 
	 * @param filter
	 * @return
	 */
	public List<Werknemer> getWerknemers(Filter... filter);

	// voegt een werknemer toe in de database
	public void voegWerknemerToe(Werknemer werknemer);

	// wijzigen van werknemer doorvoeren ind atabase
	public void wijzigWerknemer(Werknemer werknemer);

	// verwijderen van werknemer uit database
	public void verwijderWerknemer(Werknemer werknemer);
	
	public Werknemer getWerknemer(int id);

}
