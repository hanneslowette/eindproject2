package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.view.bean.Bean;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofBeheer")
@SessionScoped
public class VerlofBeheerIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean die verantwoordelijk is voor verlofaanvragen
	 */
	@Inject
	private Bean<VerlofAanvraag> aanvraag_bean;

	@Digits(integer = 4, fraction = 0)
	@NotEmpty
	@NotNull
	private int jaartal;

	public int getJaartal() {
		return jaartal;
	}

	public void setJaartal(int jaartal) {
		this.jaartal = jaartal;
	}

	public List<VerlofAanvraag> getVerlofAanvragen() {
		return null;
	}

}
