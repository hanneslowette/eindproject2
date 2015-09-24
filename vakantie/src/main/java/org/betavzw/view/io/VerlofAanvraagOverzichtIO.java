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

@Named("verlofAanvraagOverzicht")
@SessionScoped
public class VerlofAanvraagOverzichtIO implements Serializable {

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
	 * De bean die verantwoordelijk is voor werknemers
	 */
	@Inject
	private Bean<Werknemer> werknemer_bean;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> verlofAanvraag_bean;

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();

	public List<VerlofAanvraag> getVerlofAanvragenWerknemer() {
		verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer.personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
		return verlofAanvragen;
	}

	public void setVerlofAanvragenWerknemer() {
		this.verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer.personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
	}

	public List<VerlofAanvraag> getVerlofAanvragenTeamverantwoordelijke() {
		List<Werknemer> werknemers = new ArrayList<Werknemer>();
		werknemers = werknemer_bean.get(new Filter("team.id", loginbean
				.getWerknemer().getTeam().getId()));
		for (Werknemer werknemer : werknemers) {
			verlofAanvragen.addAll(werknemer.getVerlofAanvragen());
		}
		return verlofAanvragen;
	}

	public void setVerlofAanvragenTeamverantwoordelijke() {
		List<Werknemer> werknemers = new ArrayList<Werknemer>();
		werknemers = werknemer_bean.get(new Filter("team.id", loginbean
				.getWerknemer().getTeam().getId()));
		for (Werknemer werknemer : werknemers) {
			verlofAanvragen.addAll(werknemer.getVerlofAanvragen());
		}
		this.verlofAanvragen = verlofAanvragen;
	}

	public List<VerlofAanvraag> getVerlofAanvragenHR() {
		verlofAanvragen = verlofAanvraag_bean.get();
		return verlofAanvragen;
	}

	public void setVerlofAanvragenHR() {
		this.verlofAanvragen = verlofAanvraag_bean.get();
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
	public String cancel(String id) {
		VerlofAanvraag verlofAanvraag = verlofAanvraag_bean
				.getSingle(new Filter("id", Integer.parseInt(id)));
		verlofAanvraag.setToestand(Toestand.CANCELED);
		verlofAanvraag_bean.update(verlofAanvraag);
		return null;
	}

	/**
	 * Toont cancel-knop voor ingelogde gebruiker
	 */
	public boolean toonCancel(String personeelsNr) {
		boolean cancel = false;
		// int personeelsNummer = Integer.parseInt(personeelsNr);
		System.out.println(personeelsNr);

		if (loginbean.getWerknemer().getPersoneelsNr() == Integer
				.parseInt(personeelsNr)) {
			cancel = true;
		}
		System.out.println(cancel);
		return cancel;
	}

}
