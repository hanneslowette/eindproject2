package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.ejb.IWerknemer;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

@Named
@SessionScoped
public class WerknemerOpvragenIO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<Werknemer> getLijst() {
		return lijst;
	}
	public void setLijst(List<Werknemer> lijst) {
		this.lijst = lijst;
	}
	@EJB
	private IWerknemer ejb;
	
	private String naam, voornaam;
	private int personeelsNummer = -1;
	private List<Werknemer> lijst = new ArrayList<Werknemer>();
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
	public void zoekWerknemer(){
		List<Filter> tmp = new ArrayList<Filter>();
		if (!naam.equals("")) {
			tmp.add(new Filter("naam", this.naam));
		}
		if (!voornaam.equals("")) {
			tmp.add(new Filter("voornaam", this.voornaam));
		}
		if (personeelsNummer!=-1) {
			tmp.add(new Filter("personeelsNr", this.personeelsNummer));
		}
		lijst = ejb.getWerknemers(tmp.toArray(new Filter[0]));
	}
}
