package org.betavzw.view.io;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.view.bean.MailBean;
/**
 * 
 * @author user107
 *
 */
@Named("blabla")
@SessionScoped
public class BlablaIO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private MailBean mailbean;
	
	private String to;
	private String subject;
	private String body;
	
	public String send(){
		mailbean.sendmail(to, subject, body);
		return null;
	}
	
	
	public MailBean getMailbean() {
		return mailbean;
	}
	public void setMailbean(MailBean mailbean) {
		this.mailbean = mailbean;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
