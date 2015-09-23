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
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.Toestand;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named("verlofAanvraagKeuren")
@SessionScoped
public class VerlofAanvraagKeurenIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean die verantwoordelijk is voor login
	 */
	@Inject
	private LoginBean loginbean;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> verlofAanvraag_bean;

	/**
	 * De bean die verantwoordelijk is voor werknemers
	 */
	@Inject
	private Bean<Werknemer> werknemer_bean;

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();

	private String keuring;

	public List<VerlofAanvraag> getVerlofAanvragen() {
		List<Werknemer> werknemers = new ArrayList<Werknemer>();
		werknemers = werknemer_bean.get(new Filter("team_id", loginbean
				.getWerknemer().getTeam().getId()));
		for (Iterator<Werknemer> iterator = werknemers.iterator(); iterator
				.hasNext();) {
			Werknemer werknemer = iterator.next();
			verlofAanvragen = verlofAanvraag_bean.get(new Filter(
					"werknemer.personeelsNr", werknemer.getPersoneelsNr()));
		}
		return verlofAanvragen;
	}

	public void setVerlofPeriodes() {
		List<Werknemer> werknemers = new ArrayList<Werknemer>();
		List<VerlofAanvraag> verlofAanvragenTeam = new ArrayList<VerlofAanvraag>();
		werknemers = werknemer_bean.get(new Filter("team_id", loginbean
				.getWerknemer().getTeam().getId()));
		for (Iterator<Werknemer> iterator = werknemers.iterator(); iterator
				.hasNext();) {
			Werknemer werknemer = iterator.next();
			verlofAanvragenTeam = verlofAanvraag_bean.get(new Filter(
					"werknemer.personeelsNr", werknemer.getPersoneelsNr()));
		}
		this.verlofAanvragen = verlofAanvragenTeam;
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

	public String getKeuring() {
		return keuring;
	}

	public void setKeuring(String keuring) {
		this.keuring = keuring;
	}

	// TODO: toestand updaten bij juist DataTable Column entiteit van
	// verlofaanvraag
	/**
	 * De actie die gebeurt wanneer de gebruiker op "Update" klikt in de view
	 */
	public String update(String id) {
		if (keuring != null) {
			if (keuring.equalsIgnoreCase("accept")) {
				VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
						.getSingle(new Filter("id", Integer.parseInt(id)));
				verlofAanvraag.setToestand(Toestand.ACCEPTED);
				verlofAanvraag_bean.update(verlofAanvraag);
				return null;
			} else if (keuring.equalsIgnoreCase("reject")) {
				VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
						.getSingle(new Filter("id", Integer.parseInt(id)));
				verlofAanvraag.setToestand(Toestand.REJECTED);
				verlofAanvraag_bean.update(verlofAanvraag);
				return null;
			} else if (keuring.equalsIgnoreCase("cancel")) {
				VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
						.getSingle(new Filter("id", Integer.parseInt(id)));
				verlofAanvraag.setToestand(Toestand.CANCELED);
				verlofAanvraag_bean.update(verlofAanvraag);
				return null;
			}
		} else {
			return null;
		}
		return null;
	}

}