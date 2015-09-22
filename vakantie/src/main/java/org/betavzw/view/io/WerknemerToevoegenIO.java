package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.WerknemerDAO;
/**
 * 
 * @author user107
 *
 */
@Named
@SessionScoped
public class WerknemerToevoegenIO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bean<Werknemer> werknemer_bean;
	
	@Inject
	private Bean<Team> team_bean;
	
	@Inject
	private Bean<Adres> adres_bean;
	
	@Inject
	private WerknemerDAO wndao;
	
	public List<Team> getTeams() {
		return team_bean.get();
	}
	
	public String voegWerknemerToe() {
		try {
			Werknemer tmp = wndao.getEntity();
			adres_bean.offer(tmp.getAdres());
			werknemer_bean.offer(tmp);
		} catch (GeboortedatumInDeToekomstException e) {
			return View.WERKNEMER_WIJZIGEN;
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Werknemer toegevoegd"));
		return View.HOME;
	}
	
}