package org.betavzw.view.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.betavzw.ejb.IWerknemer;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

public class WerknemerOpvragenIO {
	@EJB
	private IWerknemer ejb;
	
	private String naam, voornaam;
	private int personeelsNummer = -1;
	public IWerknemer getEjb() {
		return ejb;
	}
	public void setEjb(IWerknemer ejb) {
		this.ejb = ejb;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public int getPersoneelsNummer() {
		return personeelsNummer;
	}
	public void setPersoneelsNummer(int personeelsNummer) {
		this.personeelsNummer = personeelsNummer;
	}
	public List<Werknemer> zoekWerknemer(){
		List<Filter> lijst = new ArrayList<Filter>();
		if (!naam.equals("")) {
			lijst.add(new Filter("naam", this.naam));
		}
		if (!voornaam.equals("")) {
			lijst.add(new Filter("voornaam", this.voornaam));
		}
		if (personeelsNummer!=-1) {
			lijst.add(new Filter("id", this.personeelsNummer));
		}
		return ejb.getWerknemers(lijst.toArray(new Filter[0]));
	}
}
