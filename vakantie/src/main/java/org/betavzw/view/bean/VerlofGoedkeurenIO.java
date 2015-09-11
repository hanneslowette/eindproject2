package org.betavzw.view.bean;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entities.VerlofAanvraag;

@Named("verlofGoedkeuren")
@SessionScoped
public class VerlofGoedkeurenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraag verlofAanvraag;
	private String keuring;

	public String getWerknemer() {
		return verlofAanvraag.getWerknemer().getNaam();
	}

	public Date getStartDatum() {
		return Date.from(verlofAanvraag.getStartDatum().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public Date getEindDatum() {
		return Date.from(verlofAanvraag.getEindDatum().atStartOfDay()
				.atZone(ZoneId.systemDefault()).toInstant());
	}

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
	}

	public String verstuur() {
		if (keuring.equalsIgnoreCase("accept")) {
			verlofAanvraag.Goedkeuren();
		} else if (keuring.equalsIgnoreCase("reject")) {
			verlofAanvraag.Afkeuren();
		} else if (keuring.equalsIgnoreCase("cancel")) {
			verlofAanvraag.Annuleren();
		}
		return "home";
	}
}
