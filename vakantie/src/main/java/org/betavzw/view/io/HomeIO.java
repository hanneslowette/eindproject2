package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class HomeIO  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String voegWerknemerToe() {
		return "werknemerToevoegen";
	}

}
