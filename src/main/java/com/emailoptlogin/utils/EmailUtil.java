package com.emailoptlogin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	@Autowired
	JavaMailSender mailSender;


	public String sendMail( String subject,String body, String to) {
		
		String msg = "Otp send successfully on your email id";
		
		try {
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);
			mailSender.send(mimeMsg);
 
		} catch (MessagingException e) {

			e.printStackTrace();
		}


		return msg;

	}
}
