package com.karim.controller;

import com.karim.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EmailController {

    private String username = "3lafn207@gmail.com";
    private String password = "kmaged207";

//-------------------------- Send Email ---------------------------
    @PostMapping("/sendMail")
    public String sendEmail( @RequestBody Email email)throws AddressException , MessagingException , IOException{
        sendMail(email);
        return "email sent successfully";
    }

//--------------------------- info of send email
    private void sendMail(Email email)throws AddressException , MessagingException , IOException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth" , "true");
        properties.put("mail.smtp.starttls.enable" , "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.host" , "smtp.gmail.com");
        properties.put("mail.smtp.port" , "587");
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator(){
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(username , password);
                    }
                }
                );
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username , false));
        msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email.getTo()));
        msg.setSubject(email.getSubject());
        msg.setContent(email.getBody()  , "test/html");
        msg.setSentDate(new Date());

        Transport.send(msg);
    }

}
