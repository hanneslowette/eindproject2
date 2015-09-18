package org.betavzw.view.io;

import java.io.Serializable;

import javax.inject.Inject;

import org.betavzw.entity.Team;
import org.betavzw.view.bean.Bean;

public class TeamBeheerIO implements Serializable {

	/**
	 * De versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	@Inject private Bean<Team> teams;

	public String beheer(int id) {
		return null;
	}

}