package com.cg.dd.app.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
public interface EmailService {

	/**
	 * Method to send email to the email provided.
	 * 
	 * @param email
	 */
	public void sendEmail(SimpleMailMessage email) throws MailException;

}
