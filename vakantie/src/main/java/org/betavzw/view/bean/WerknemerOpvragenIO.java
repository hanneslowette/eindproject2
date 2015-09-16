package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.ejb.IWerknemer;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Filter;

@Named("werknemerOpvragenIO")
@SessionScoped
public class WerknemerOpvragenIO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IWerknemer ejb;
	private String naam, voornaam;
	private Integer personeelsNummer;
	private List<Werknemer> lijst = new ArrayList<Werknemer>();
	private Werknemer werknemer;
	public Werknemer getWerknemer() {
		return werknemer;
	}
	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}
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
	public Integer getPersoneelsNummer() {
		return personeelsNummer;
	}
	public void setPersoneelsNummer(Integer personeelsNummer) {
		this.personeelsNummer = personeelsNummer;
	}
	public List<Werknemer> getLijst() {
		return lijst;
	}
	public void setLijst(List<Werknemer> lijst) {
		this.lijst = lijst;
	}
	public String zoek(){
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
		return null;
	}
	
	public String wijzig(int id){
		setWerknemer(ejb.getWerknemer(id));
		return "wijzigWerknemer";
	}
}
