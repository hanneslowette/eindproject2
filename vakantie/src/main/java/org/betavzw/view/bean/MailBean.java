package org.betavzw.view.bean;

import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ApplicationScoped
public class MailBean {
	
//	@Resource(lookup = "redmail")
//    private Session mailSession;
	
	public void sendmail(String to, String subject, String body) {
		System.out.println("mailbean sendmailmethod has been reached");
//		MimeMessage message = new MimeMessage(mailSession);
//		System.out.println("localhost:"+ mailSession.getProperty("mail.smtp.localhost"));
//        try {
//        	System.out.println("mailtryblock has been reached");
//            message.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
//            InternetAddress[] address = {new InternetAddress(to)};
//            message.setRecipients(Message.RecipientType.TO, address);
//            message.setSubject(subject);
//            message.setSentDate(new Date());
//            message.setText(body);
//            Transport.send(message);
//        } catch (MessagingException ex) {
//        	System.out.println("Mail has not been sent, exception was thrown");
//            ex.printStackTrace();
//        }
        
        String from = "brentcourtois@gmail.com";
//        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("port", "420");
        properties.setProperty("mail.smtp.host", "out.telenet.be");
        Session session = Session.getDefaultInstance(properties);

        try{
           MimeMessage message = new MimeMessage(session);
           message.setFrom(new InternetAddress(from));
           message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
           message.setSubject(subject);
           message.setText(body);
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
	}

}
