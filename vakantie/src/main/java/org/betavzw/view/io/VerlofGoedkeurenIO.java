package org.betavzw.view.io;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.view.bean.Bean;

@Named("verlofGoedkeuren")
@SessionScoped
public class VerlofGoedkeurenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Bean<VerlofAanvraag> aanvraag_bean;
	private String keuring;

	public List<VerlofAanvraag> getVerlofAanvragen() {
		return aanvraag_bean.get();
	}

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
	}

	public Date getStartDatum() {
//		return Date.from(verlofAanvraagEJB.getStartDatum().atStartOfDay()
//				.atZone(ZoneId.systemDefault()).toInstant());
		return null;
	}

	public Date getEindDatum() {
//		return Date.from(verlofAanvraagEJB.getEindDatum().atStartOfDay()
//				.atZone(ZoneId.systemDefault()).toInstant());
		return null;
	}

	public String verstuur() {
		
		
//		if (keuring.equalsIgnoreCase("ACCEPT")) {
//			verlofAanvraagEJB.setToestand("ACCEPT");
//		} else if (keuring.equalsIgnoreCase("REJECT")) {
//			verlofAanvraagEJB.setToestand("REJECT");
//		} else if (keuring.equalsIgnoreCase("CANCEL")) {
//			verlofAanvraagEJB.setToestand("CANCEL");
//		}
		return "home";
	}
	
}