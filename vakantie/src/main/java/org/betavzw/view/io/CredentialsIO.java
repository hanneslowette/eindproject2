package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Credentials;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named
@SessionScoped
public class CredentialsIO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Inject private Bean<Credentials> credential_bean;
	
	private String username;
	private String paswoord;
	public Bean<Credentials> getCredential_bean() {
		return credential_bean;
	}
	public void setCredential_bean(Bean<Credentials> credential_bean) {
		this.credential_bean = credential_bean;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPaswoord() {
		return paswoord;
	}
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}
	
	
	

}
