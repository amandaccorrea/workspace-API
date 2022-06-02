package com.residencia.comercio.controllers;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@Validated
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMailTexto() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Application");
        message.setTo("amandacorrea625@gmail.comm");
        message.setSubject("Teste Envio de e-mail");
        message.setFrom("grupo01.serratec.turma01@gmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

    @RequestMapping(path = "/email-send-html", method = RequestMethod.GET)
    public String sendMailHtml() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mail);
            helper.setTo("amandacorrea625@gmail.com");
            helper.setSubject("Teste Envio de e-mail");
            helper.setText("<style>\r\n"
            		+ "header{\r\n"
            		+ "    text-align: center;\r\n"
            		+ "  background-color: #fcb39d;\r\n"
            		+ "  padding: 4rem;\r\n"
            		+ "  color: rgb(206, 188, 223);\r\n"
            		+ "}\r\n"
            		+ "div{\r\n"
            		+ "    text-align: center;\r\n"
            		+ "  background-color: #ecdba3;\r\n"
            		+ "  color: black;\r\n"
            		+ "}\r\n"
            		+ "img{\r\n"
            		+ "    border-radius: 50%;\r\n"
            		+ "  width: 14rem;\r\n"
            		+ "  height: 14rem;\r\n"
            		+ "}\r\n"
            		+ "    </style>"
            		+ "<header>\r\n"
            		+ "    <h1>Hello!!</h1>\r\n"
            		+ "    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus repellendus hic nobis veniam dignissimos corrupti incidunt quaerat doloremque minima provident?</p>\r\n"
            		+ "    </header>\r\n"
            		+ "    <div>\r\n"
            		+ "    <h3>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Temporibus.</h3>\r\n"
            		+ "    <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Labore sapiente veniam corporis tempore ipsam cumque itaque nemo eos quas placeat?</p>\r\n"
            		+ "    <img src=\"https://www.bonde.com.br/img/bondenews/2019/12/img_1_75_1491.jpg\" alt=\"cachorrinho fofinho\">\r\n"
            		+ "    </div>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }

}
