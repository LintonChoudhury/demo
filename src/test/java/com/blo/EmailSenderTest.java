package com.blo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blo.services.EmailService;

@SpringBootTest
public class EmailSenderTest {
	
	@Autowired
	private EmailService emailService;
	
	@Test
	void  emailSendTest() {
		System.out.println("sending mail");
		
		emailService.sendEmail("lintonchoudhury90@gmail.com", "Email from spring boot",  "this email is sending using spring boot ");
	}
	@Test
	void sendEmailWithHtml() {
		String html =""+ "<h1 style='color:red;border:1px solid red;>Welcome to choudhury world</h1>"+"";
		emailService.sendEmail("lintonchoudhury90@gmail.com", "Email from spring boot",  "this email is sending using spring boot ");
	}

}
