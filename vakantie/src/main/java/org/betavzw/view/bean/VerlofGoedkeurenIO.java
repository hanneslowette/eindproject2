package org.betavzw.view.bean;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.ejb.WerknemerEJB;
import org.betavzw.ejb.VerlofAanvraagEJB;

@Named("verlofGoedkeuren")
@SessionScoped
public class VerlofGoedkeurenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraagEJB verlofAanvraagEJB;
	@EJB
	private WerknemerEJB werknemerEJB;
	private String voornaam;
	private String naam;
	private int personeelsNr;
	private String keuring;

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
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

	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public void setPersoneelsNr(int personeelsNr) {
		this.personeelsNr = personeelsNr;
	}

	public Date getStartDatum() {
		return Date.from(verlofAanvraagEJB.getStartDatum().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Date getEindDatum() {
		return Date.from(verlofAanvraagEJB.getEindDatum().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String verstuur() {
		if (keuring.equalsIgnoreCase("ACCEPT")) {
			verlofAanvraagEJB.setToestand("ACCEPT");
		} else if (keuring.equalsIgnoreCase("REJECT")) {
			verlofAanvraagEJB.setToestand("REJECT");
		} else if (keuring.equalsIgnoreCase("CANCEL")) {
			verlofAanvraagEJB.setToestand("CANCEL");
		}
		return "home";
	}
}
