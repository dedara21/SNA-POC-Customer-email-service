package com.sna.email.utils;

import java.io.File;
import java.io.IOException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

	private static final Logger log = LogManager.getLogger(EmailUtil.class);

	@Autowired
	private JavaMailSender javaMailSender;

	/**
	 * This method will compose and send the message
	 * 
	 * @param toEmail
	 * @param subject
	 * @param message
	 * @throws MessagingException
	 */
	public void sendMail(String toEmail, String subject, String body) throws MessagingException {

		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(toEmail);

		helper.setSubject(subject);

		helper.setText(body, true);

		log.debug("Sending email without attachment...");

		javaMailSender.send(message);

		log.debug("Email without attachment sent sucessfully...");
	}

	/**
	 * This method will compose and send the mail with attachment
	 * 
	 * @param toEmail
	 * @param subject
	 * @param body
	 * @param fileToAttach
	 */
	public void sendMailWithAttachment(String toEmail, String subject, String body, DataSource fds)
			throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo(toEmail);

		helper.setSubject(subject);

		helper.setText(body, true);

		/*File file = new File(fileToAttach);

		FileSystemResource fileSystemResource = new FileSystemResource(file);

		helper.addAttachment(file.getName(), fileSystemResource);*/
		
		helper.addAttachment("sna_expiry_report.xlsx", fds);

		log.debug("Sending email with attachment...");

		javaMailSender.send(msg);

		log.debug("Email with attachment sent sucessfully...");
	}
}
