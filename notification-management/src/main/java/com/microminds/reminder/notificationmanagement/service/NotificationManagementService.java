package com.microminds.reminder.notificationmanagement.service;

import com.microminds.reminder.notificationmanagement.exception.NotificationFailedException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class NotificationManagementService {
    public boolean sendEmail(String subject, String message, String to){
        boolean messageSent = false;
        String from = "microservicehackathon@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("microservicehackathon@gmail.com","giopsvrmmumlzgqc");
            }
        } );
        session.setDebug(true);

        MimeMessage mimeMessage= new MimeMessage(session);
        try{
            mimeMessage.setFrom(from);

            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            mimeMessage.setSubject(subject);

            mimeMessage.setText(message);

            Transport.send(mimeMessage);

            messageSent = true;
        }

        catch (Exception e){
            throw new NotificationFailedException("Notification process failed for user with mail ID -"+to);
        }

        return messageSent;
    }
}
