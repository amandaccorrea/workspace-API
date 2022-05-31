package com.residencia.comercio.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Validated
public class EmailService {
	@Value("spring.mail.host")
	private String mailHost;
	@Value("spring.mail.port")
	private String mailPort;
	@Value("spring.mail.username")
	private String mailUsername;
	@Value("spring.mail.password")
	private String mailPassword;
	@Autowired
	JavaMailSender emailSender;
	
	public void MailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
				
	}
	public void enviarEmailTexto(String destinatarioEmail, String assunto, String mensagemEmail) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		sMailMessage.setTo(destinatarioEmail);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(mensagemEmail);
		sMailMessage.setFrom("teste@teste.com");
		
		emailSender.send(sMailMessage);
	}
	
    public void enviarEmailHtml(String destinatarioEmail, String assunto, String mensagemEmail) throws MessagingException {
       
            MimeMessage mail = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(destinatarioEmail);
            helper.setSubject(assunto);
            helper.setText(mensagemEmail, true);
            emailSender.send(mail);

            
    }
	
	public String getMailHost() {
		return mailHost;
	}
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}
	public String getMailPort() {
		return mailPort;
	}
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	public JavaMailSender getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(JavaMailSender emailSender) {
		this.emailSender = emailSender;
	}
	@Override
	public String toString() {
		return "EmailService [mailHost=" + mailHost + ", mailPort=" + mailPort + ", mailUsername=" + mailUsername
				+ ", mailPassword=" + mailPassword + ", emailSender=" + emailSender + "]";
	}
	
}
