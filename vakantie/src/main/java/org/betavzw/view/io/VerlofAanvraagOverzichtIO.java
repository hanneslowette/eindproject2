package org.betavzw.view.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named("verlofAanvraagOverzicht")
@SessionScoped
public class VerlofAanvraagOverzichtIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginBean loginbean;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> verlofAanvraag_bean;

	private String keuring;

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
	}

	public List<VerlofAanvraag> getVerlofAanvragen() {
		verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer_personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
		return verlofAanvragen;
	}

	public void setVerlofPeriodes(List<VerlofAanvraag> verlofAanvragen) {
		this.verlofAanvragen = verlofAanvragen;
	}

	/**
	 * De actie die gebeurt wanneer de gebruiker op "Verstuur" klikt in de view
	 */
	public String cancel() {
//		verlofAanvraag_bean.update(entity);

		return null;
	}

}
