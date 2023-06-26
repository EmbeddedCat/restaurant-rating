package uni.exercise.email;

import uni.exercise.servlets.users.email_user.EmailUser;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class EmailSender {
    static private String subject = "No-reply";
    static private String host    = "smtp.gmail.com";

    public static void sendEmail(String msg, String to) throws IOException {
        EmailUser user = new EmailUser();
        user.retrieveCredentials();

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            // TODO - retrieve the password from a file, to make it more secure.
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(user.getEmail(), user.getPassword());
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(user.getEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }
}