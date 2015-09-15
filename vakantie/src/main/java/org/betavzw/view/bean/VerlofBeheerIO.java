package org.betavzw.view.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofBeheer")
@SessionScoped
public class VerlofBeheerIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Digits(integer = 4, fraction = 0)
	@NotEmpty
	@NotNull
	private int jaartal;
	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*")
	@NotEmpty
	@NotNull
	private String voornaam;
	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*")
	@NotEmpty
	@NotNull
	private String naam;
	@Digits(integer = 10, fraction = 0)
	@NotEmpty
	@NotNull
	private int personeelsNr;

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public void setPersoneelsNr(int personeelsNr) {
		this.personeelsNr = personeelsNr;
	}

	public int getJaartal() {
		return jaartal;
	}

	public void setJaartal(int jaartal) {
		this.jaartal = jaartal;
	}

}
