package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.betavzw.entity.Credentials;
import org.betavzw.util.Filter;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;

@Named("login_view")
@SessionScoped
public class LoginIO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * De bean die verantwoordelijk is voor credentials
	 */
	@Inject
	private Bean<Credentials> credential_bean;

	/**
	 * De bean die verantwoordelijk is voor login
	 */
	@Inject
	private LoginBean login;

	/**
	 * De username om in te loggen
	 */
	private String username;

	/**
	 * Het password om in te loggen
	 */
	private String password;

	/**
	 * Meld de gebruiker aan
	 * 
	 * @return null
	 */
	public String inloggen() {
		System.out.println(DigestUtils.md5Hex(password));
		if (!password.equals("") && !username.equals("")) {
			try {
				Credentials credentials = credential_bean
						.getSingle(new Filter("username", username),
								new Filter("password", DigestUtils.md5Hex(password)));
				login.setType(credentials.getType());
				login.setWerknemer(credentials.getWerknemer());
				login.setAangemeld(true);
				return View.VERSTUURD;
			} catch (Exception ex) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage("", new FacesMessage(
						"Username of password niet gevonden"));
				System.out
						.println("PROBLEEM??? Username of password niet gevonden in database");
				System.out.println(ex);
				return null;
			}
			// }
		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("", new FacesMessage(
					"Gelieve username en password in te voeren"));
			return null;
		}
	}

	/**
	 * Meld de gebruiker af en geeft dezelfde pagina weer
	 * 
	 * @return null
	 */
	public String uitloggen() {
		login.setType(null);
		username = null;
		return null;
	}

	/**
	 * Meld de gebruiker af en gaat naar de loginpagina
	 * 
	 * @return "login"
	 */
	public String uitloggen2() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		session.invalidate();
		return "login";
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

}