package org.betavzw.view.bean;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.ejb.IWerknemer;
import org.betavzw.ejb.VerlofAanvraagEJB;
import org.betavzw.util.Filter;

@Named("verlofGoedkeuren")
@SessionScoped
public class VerlofGoedkeurenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraagEJB verlofAanvraagEJB;
	@Inject
	private IWerknemer werknemerEJB;
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
		return werknemerEJB
				.getWerknemers(new Filter("voornaam", this.voornaam)).get(0)
				.getVoornaam();
	}

	public String getNaam() {
		return werknemerEJB.getWerknemers(new Filter("naam", this.naam)).get(0)
				.getNaam();
	}

	public int getPersoneelsNr() {
		werknemerEJB
				.getWerknemers(new Filter("personeelsNr", this.personeelsNr))
				.get(0).getPersoneelsNr();
		return personeelsNr;
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
