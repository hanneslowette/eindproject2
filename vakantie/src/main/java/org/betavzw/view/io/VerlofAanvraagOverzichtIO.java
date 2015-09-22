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

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();

	public List<VerlofAanvraag> getVerlofAanvragen() {
		verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer.personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
		return verlofAanvragen;
	}

	public void setVerlofPeriodes() {
		this.verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer.personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
	}

	/**
	 * De actie die gebeurt wanneer de gebruiker op "Cancel" klikt in de view
	 */
	public String cancel() {
		// VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
		// .getSingle(new Filter("", value));
		// verlofAanvraag.setToestand(Toestand.CANCELED);
		// verlofAanvraag_bean.update(verlofAanvraag);
		return null;
	}

}
