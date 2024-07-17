package com.blo.services;

import java.io.File;

import jakarta.mail.MessagingException;

public interface EmailService {
//send email to a single person
	void sendEmail(String to,String subject,String message);
	
	//send email to a multiple person
	
	void sendEmail(String []to,String subject,String message);
	
	//void sendEmailWithHtml
	
	void sendEmailWithHtml(String to,String subject,String htmlContent) throws MessagingException;
	
	
	
	void sendEmailWithFile(String to,String subject,String message,File file);
	
}
