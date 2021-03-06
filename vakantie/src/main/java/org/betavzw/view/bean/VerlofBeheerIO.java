package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.betavzw.ejb.VerlofAanvraagEJB;
import org.betavzw.entities.VerlofAanvraag;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofBeheer")
@SessionScoped
public class VerlofBeheerIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraagEJB verlofAanvraagEJB;
	@Digits(integer = 4, fraction = 0)
	@NotEmpty
	@NotNull
	private int jaartal;
	@Digits(integer = 10, fraction = 0)
	@NotEmpty
	@NotNull
	private int personeelsNr;

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

	public List<VerlofAanvraag> getVerlofAanvragen() {
		return verlofAanvraagEJB.getVerlofAanvraagPersoneelsNr(personeelsNr);		
		// TODO: zoeken op jaartal
	}

}
