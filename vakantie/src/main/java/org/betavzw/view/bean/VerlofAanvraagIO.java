package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entities.VerlofAanvraag;
import org.betavzw.util.Toestand;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraag verlofAanvraag;
	private Date startDatum;
	private Date eindDatum;

	public VerlofAanvraag getVerlofAanvraag() {
		return verlofAanvraag;
	}

	public void setVerlofAanvraag(VerlofAanvraag verlofAanvraag) {
		this.verlofAanvraag = verlofAanvraag;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public Date getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}

	public String verstuur() {
		String pagina = "";
		if (startDatum.before(eindDatum)) {
			// TODO: zet message dat startdatum voor einddatum moet komen
			pagina = "verlofaanvraag";
			if (verlofAanvraag.getToestand() == Toestand.ACCEPTED) {
				pagina = "verlofgoedgekeurd";
			}
			if (verlofAanvraag.getToestand() == Toestand.REJECTED) {
				pagina = "verlofafgekeurd";
			}
		}
		return pagina;
	}
}
