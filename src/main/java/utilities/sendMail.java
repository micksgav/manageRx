package utilities;

import java.io.File;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class sendMail {
    static final String sep = System.getProperty("file.separator");
    static String pharma = "Smith Pharmacy #5654";

    public static boolean send(String to, String subject, String messageIn) {
        String from = "managerx@gavinm.ca";
        final String username = "managerx@gavinm.ca";
        final String password = "manageRx*!";
        String host = "mail.gavinm.ca";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(
                props, new jakarta.mail.Authenticator() { protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject(subject);
            //set the content of the email message
            message.setText(messageIn);
            //send the email message
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            logErrors.log(e.getMessage() + " in send in sendMail to email " + to);
            return false;

        }
    }

    public static boolean restockRequest(String DIN, String QTY) {
        return send("restock@gavinm.ca", "Refill " + DIN + " " + QTY + "x", "Sent from ManageRx for " + pharma);
    }

    public static boolean sendError(String error) {
        String from = "managerx@gavinm.ca";
        final String username = "managerx@gavinm.ca";
        final String password = "manageRx*!";
        String host = "mail.gavinm.ca";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(
                props, new jakarta.mail.Authenticator() { protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("errors@gavinm.ca"));
            message.setSubject("Error: " + error);
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Log Attatched");
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(new File("data" + sep + "log.txt"));
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);
            message.setContent(multipart);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            logErrors.log(e.getMessage() + " in send in sendMail to log");
            return false;

        }
    }
}
