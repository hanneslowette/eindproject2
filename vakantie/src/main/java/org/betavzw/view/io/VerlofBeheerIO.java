package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;
import org.betavzw.util.Toestand;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofBeheer")
@SessionScoped
public class VerlofBeheerIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> bean;

	@Inject
	private LoginBean loginBean;

	/**
	 * De door de gebruiker ingevulde jaartal waarvan hij zijn verlofdagen wilt
	 * zien
	 */
	@Pattern(regexp = "\\d{4}")
	@NotEmpty
	@NotNull
	private String jaartal;

	/**
	 * De opgevraagde lijst met verlofaanvragen
	 */
	private List<VerlofAanvraag> verlofPeriodes = new ArrayList<VerlofAanvraag>();

	public String getJaartal() {
		return jaartal;
	}

	public void setJaartal(String jaartal) {
		this.jaartal = jaartal;
	}

	public List<VerlofAanvraag> getVerlofPeriodes() {
		return verlofPeriodes;
	}

	public void setVerlofPeriodes(List<VerlofAanvraag> verlofPeriodes) {
		this.verlofPeriodes = verlofPeriodes;
	}

	/**
	 * De actie die gebeurt wanneer de gebruiker op "Zoek" klikt in de view
	 */
	public String zoek() {
		// TODO: NullPointerException oplossen
		verlofPeriodes = bean.get(new Filter("personeelsNr", loginBean
				.getWerknemer().getPersoneelsNr()));

		verlofPeriodes = bean.get(
				new Filter("startDatum", LocalDate.of(
						Integer.parseInt(jaartal), 1, 1)),
				new Filter("eindDatum", LocalDate.of(Integer.parseInt(jaartal),
						12, 31)), new Filter("Toestand", Toestand.ACCEPTED));
		// tmp.add(new Filter("startDatum", LocalDate.of(
		// Integer.parseInt(jaartal), 1, 1)));
		// tmp.add(new Filter("eindDatum",
		// LocalDate.of(Integer.parseInt(jaartal),
		// 12, 31)));
		// tmp.add(new Filter("Toestand", Toestand.ACCEPTED));
		return null;
	}
}
