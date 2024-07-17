package com.blo.serviceimpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.blo.services.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("lintonchoudhury90@gmail.com");
		javaMailSender.send(simpleMailMessage);
		logger.info("Email has been sent ..");
	}

	@Override
	public void sendEmail(String[] to, String subject, String message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		simpleMailMessage.setFrom("lintonchoudhury90@gmail.com");
		javaMailSender.send(simpleMailMessage);
		
		logger.info("Email has been sent ..");
	}

	@Override
	public void sendEmailWithHtml(String to, String subject, String htmlContent)  {
		MimeMessage simpleMailMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(simpleMailMessage,true,"UTF-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom("lintonchoudhury90@gmail.com");
			helper.setText(htmlContent,true);
			javaMailSender.send(simpleMailMessage);
			logger.info("Email has been sent ..");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void sendEmailWithFile(String to, String subject, String message, File file) {
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
			helper.setFrom("lintonchoudhury90@gmail.com");
			helper.setTo(to);
			helper.setText(message);
			helper.setSubject(subject);
//			File file = new File("test.png");
//			Files.copy(is,file.toPath(), StandardCopyOption.REPLACE_EXISTING)
			FileSystemResource fileSystemResource = new FileSystemResource(file);
			helper.addAttachment(fileSystemResource.getFilename(),file);
			javaMailSender.send(mimeMessage);
			logger.info("Email has been sent ..");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
	}

}
