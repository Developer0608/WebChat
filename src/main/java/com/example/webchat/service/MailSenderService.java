package com.example.webchat.service;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;

import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderService {

    // function to send the mail
    public void sendmail(String to, String body, String subject, int retryCount) {
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chatappservice481@gmail.com", "test1test2");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chatapp481@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            System.err.println("[ERROR]::MailSenderService::" + retryCount + e);
            if (retryCount <= 5) {
                sendmail(to, body, subject, retryCount + 1);
            }
        }
    }
}