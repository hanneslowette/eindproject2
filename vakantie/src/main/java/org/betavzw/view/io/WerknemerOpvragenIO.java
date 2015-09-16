package org.betavzw.view.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

/**
 * Wat de fuck was dees, gast
 * 
 * @author red
 *
 */
@Named("werknemerOpvragenIO")
@SessionScoped
public class WerknemerOpvragenIO implements Serializable{
	
	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * De bean die instaat voor de werknemers
	 */
	@Inject private Bean<Werknemer> werknemer_bean;
	
	/**
	 * De door de gebruiker ingevulde achternaam
	 */
	private String naam;
	
	/**
	 * De door de gebruiker ingevulde voornaam
	 */
	private String voornaam;
	
	/**
	 * De door de gebruiker ingevulde personeelsnummer
	 */
	private Integer personeelsNummer;
	
	/**
	 * 
	 */
	private Werknemer werknemer;
	
	/**
	 * De opgevraagde lijst met werknemers
	 */
	private List<Werknemer> lijst = new ArrayList<Werknemer>();
	
	/**
	 * De code die wordt uitgevoerd wanneer de gebruiker op "zoek" klikt
	 */
	public void zoek(){
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
		lijst = werknemer_bean.get(tmp.toArray(new Filter[0]));
	}
	
	/**
	 * Zet de geselecteerde werknemer als werknemer die bewerkt wordt en gaat dan naar
	 * de view die hem kan bewerken
	 * 
	 * @param id
	 * @return
	 */
	public String wijzig(int id){
		setWerknemer(werknemer_bean.getSingle(new Filter("id", id)));
		return View.WIJZIG_WERKNEMER;
	}

	public List<Werknemer> getLijst() {
		return lijst;
	}

	public void setLijst(List<Werknemer> lijst) {
		this.lijst = lijst;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
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

}