package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.ejb.ITeam;
import org.betavzw.entities.Team;
import org.betavzw.util.Filter;

@Named
@RequestScoped
public class TeamOpvragenIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ITeam ejb;
	
	private String naam;
	private String verantwoordelijke;
	private String code;
	private List<Team> lijst = new ArrayList<Team>();
	
	
	public String zoek(){
		List<Filter> tmp = new ArrayList<Filter>();
		if (!naam.equals("")) {
			tmp.add(new Filter("naam", this.naam));
		}
		if (!code.equals("")) {
			tmp.add(new Filter("code", this.code));
		}
		if (!verantwoordelijke.equals("")) {
			tmp.add(new Filter("verantwoordelijke", this.verantwoordelijke));
		}
		
		lijst = ejb.getTeams();
		//lijst = ejb.getWerknemers(tmp.toArray(new Filter[0]));
		return null;
	}
	
	
	
	public ITeam getEjb() {
		return ejb;
	}
	public void setEjb(ITeam ejb) {
		this.ejb = ejb;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getVerantwoordelijke() {
		return verantwoordelijke;
	}
	public void setVerantwoordelijke(String verantwoordelijke) {
		this.verantwoordelijke = verantwoordelijke;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<Team> getLijst() {
		return lijst;
	}
	public void setLijst(List<Team> lijst) {
		this.lijst = lijst;
	}
	
	
	
	

}
