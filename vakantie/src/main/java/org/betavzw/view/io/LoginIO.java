package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Credentials;
import org.betavzw.util.Filter;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named("login_view")
@SessionScoped
public class LoginIO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private Bean<Credentials> credential_bean;
	@Inject private LoginBean login;

	private String username;
	private String password;

	public String aanmelden() {
		try {
			Credentials credentials = credential_bean.getSingle(new Filter("username", username), new Filter("password", password));
			
			login.setType(credentials.getType());
			login.setWerknemer(credentials.getWerknemer());
			login.setAangemeld(true);
			return View.VERSTUURD;
		} catch (Exception ex) {
			System.out.println("PROBLEEM?????");
			System.out.println(ex);
			return null;
		}
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}