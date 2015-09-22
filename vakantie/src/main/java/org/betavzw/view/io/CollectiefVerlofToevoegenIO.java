package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.CollectiefVerlof;
import org.betavzw.entity.CollectieveSluiting;
import org.betavzw.entity.Feestdag;
import org.betavzw.view.bean.Bean;

@Named
@SessionScoped
public class CollectiefVerlofToevoegenIO implements Serializable {

	private static final long serialVersionUID = 1857446570390400499L;

	// @Inject
	// private Bean<Werknemer> werknemer_bean;

	@Inject
	private Bean<Feestdag> collectiefVerlof_bean;

	private CollectiefVerlof collectiefVerlof;
	private Feestdag feestdag;

	public Feestdag getFeestdag() {
		return feestdag;
	}

	public void setFeestdag(Feestdag feestdag) {
		this.feestdag = feestdag;
	}

	public CollectiefVerlof getCollectiefVerlof() {
		return collectiefVerlof;
	}

	public void setCollectiefVerlof(CollectiefVerlof collectiefVerlof) {
		this.collectiefVerlof = collectiefVerlof;
	}

	public Bean<Feestdag> getCollectiefVerlof_bean() {
		return collectiefVerlof_bean;
	}

	public void setCollectiefVerlof_bean(Bean<Feestdag> collectiefVerlof_bean) {
		this.collectiefVerlof_bean = collectiefVerlof_bean;
	}

	// public Bean<CollectieveSluiting> getCollectiefVerlof_bean() {
	// return collectiefVerlof_bean;
	// }
	//
	// public void setCollectiefVerlof_bean(
	// Bean<CollectieveSluiting> collectiefVerlof_bean) {
	// this.collectiefVerlof_bean = collectiefVerlof_bean;
	// }

}
