package org.betavzw.entity;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class CollectiefVerlof extends CollectieveSluiting {
	
	private static final long serialVersionUID = -1348741390310980710L;

	private LocalDate eindDatum;

	public LocalDate getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDate eindDatum) {
		this.eindDatum = eindDatum;
	}
}
