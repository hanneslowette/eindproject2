package org.betavzw.view.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.view.bean.Bean;

@Named("verlofAanvraagKeuren")
@SessionScoped
public class VerlofAanvraagKeurenIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> bean;

	private String keuring;

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
	}

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();

	public List<VerlofAanvraag> getVerlofAanvragen() {
		verlofAanvragen = bean.get();
		return verlofAanvragen;
	}

	public void setVerlofPeriodes(List<VerlofAanvraag> verlofAanvragen) {
		this.verlofAanvragen = verlofAanvragen;
	}

	/**
	 * De actie die gebeurt wanneer de gebruiker op "Verstuur" klikt in de view
	 */

	public String update() {
		if (keuring.equalsIgnoreCase("accept")) {

		} else if (keuring.equalsIgnoreCase("reject")) {

		} else if (keuring.equalsIgnoreCase("cancel")) {

		}
		// TODO: toestand updaten bij juist DataTable Column entiteit van
		// verlofaanvraag
		return null;
	}

}