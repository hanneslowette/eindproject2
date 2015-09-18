package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * 
 * 
 * @author user104
 */
@Named("date")
@RequestScoped
public class DateIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate date = LocalDate.now();

	public void syso() {
		System.out.println(date);
	}

	public Date getDate() {
		return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public void setDate(Date date) {
		this.date = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

}