package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.view.View;

@Named
@SessionScoped
public class HomeIO implements Serializable {

	private static final long serialVersionUID = 1L;

	public String voegWerknemerToe() {
		return View.WERKNEMER_TOEVOEGEN;
	}

}
