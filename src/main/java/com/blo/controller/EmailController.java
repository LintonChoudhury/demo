package com.blo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blo.entities.EmailRequest;
import com.blo.services.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException{
		
		emailService.sendEmailWithHtml(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		
		return (ResponseEntity<?>) ResponseEntity.ok();
		
	}

}
