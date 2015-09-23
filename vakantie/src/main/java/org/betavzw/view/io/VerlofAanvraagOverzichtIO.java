package org.betavzw.view.io;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.util.Toestand;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named("verlofAanvraagOverzicht")
@SessionScoped
public class VerlofAanvraagOverzichtIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	 * De opgevraagde lijst met aanvraagdatums
	 */
	public List<Date> getAanvraagDatums() {
		List<Date> aanvraagDatums = new ArrayList<Date>();
		for (Iterator<VerlofAanvraag> iterator = verlofAanvragen.iterator(); iterator
				.hasNext();) {
			VerlofAanvraag verlofAanvraag = iterator.next();
			aanvraagDatums
					.add(Date.from(verlofAanvraag.getAanvraagDatum()
							.atStartOfDay().atZone(ZoneId.systemDefault())
							.toInstant()));
		}
		return aanvraagDatums;
	}

	/**
	 * De opgevraagde lijst met startdatums
	 */
	public List<Date> getStartDatums() {
		List<Date> startDatums = new ArrayList<Date>();
		for (Iterator<VerlofAanvraag> iterator = verlofAanvragen.iterator(); iterator
				.hasNext();) {
			VerlofAanvraag verlofAanvraag = iterator.next();
			startDatums
					.add(Date.from(verlofAanvraag.getStartDatum()
							.atStartOfDay().atZone(ZoneId.systemDefault())
							.toInstant()));
		}
		return startDatums;
	}

	/**
	 * De opgevraagde lijst met einddatums
	 */
	public List<Date> getEindDatums() {
		List<Date> eindDatums = new ArrayList<Date>();
		for (Iterator<VerlofAanvraag> iterator = verlofAanvragen.iterator(); iterator
				.hasNext();) {
			VerlofAanvraag verlofAanvraag = iterator.next();
			eindDatums
					.add(Date.from(verlofAanvraag.getEindDatum().atStartOfDay()
							.atZone(ZoneId.systemDefault()).toInstant()));
		}
		return eindDatums;
	}

	/**
	 * De actie die gebeurt wanneer de gebruiker op "Cancel" klikt in de view
	 */
	public String cancel() {
		VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
				.getSingle(new Filter("id", id));
		// VerlofAanvraag verlofAanvraag = verlofAanvragen.get(Integer
		// .parseInt("id"));
		verlofAanvraag.setToestand(Toestand.CANCELED);
		verlofAanvraag_bean.update(verlofAanvraag);
		return null;
	}

}
