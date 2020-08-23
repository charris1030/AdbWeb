package com.adb.web.app.services;

import com.adb.web.app.domain.ContactEmail;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Component
public class EmailService {
    //ses-smtp-user.20200812-191304
    //SMTP Username: AKIAQHGOY3HBZUJZP7G7
    //SMTP Password: BL9qaMIVrgcPpO0FO8eM/jKIOF7vkhzLViGQNRBVpRWU
    // Replace smtp_username with your Amazon SES SMTP user name.
    static final String SMTP_USERNAME = "AKIAS34JN7JXLBDQEC36";

    // Replace smtp_password with your Amazon SES SMTP password.
    static final String SMTP_PASSWORD = "BKbk+gtOYj18MS0PtoVgZMXV54kutI2mMQnilJTGjhB6";

    // The name of the Configuration Set to use for this message.
    // If you comment out or remove this variable, you will also need to
    // comment out or remove the header below.
    //static final String CONFIGSET = "ConfigSet";

    // Amazon SES SMTP host name.
    static final String HOST = "email-smtp.us-east-2.amazonaws.com";

    // Replace sender@example.com with your "From" address.
    // This address must be verified.
    static final String FROM = "adustless@hotmail.com";
    static final String FROMNAME = "Affordable Dustless Blasting Web";

    // Replace recipient@example.com with a "To" address. If your account
    // is still in the sandbox, this address must be verified.
    static final String TO = "adustless@hotmail.com";

    // The port you will connect to on the Amazon SES SMTP endpoint.
    static final int PORT = 587;

    static final String SUBJECT = "New Contact From ADustlessBlasting.com!";

    static final String BODY = String.join(
            System.getProperty("line.separator"),
            "<h1>New Contact From  ADustlessBlasting.com</h1>",
            "<h5>Name</h5>\n" +
                    "<p></p>"
    );

    public void sendContactEmail(ContactEmail contactEmail) throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Create a Session object to represent a mail session with the specified properties.
        Session session = Session.getDefaultInstance(props);

        String BODY = String.join(
                System.getProperty("line.separator"),
                "<h1>New Contact From ADustlessBlasting.com</h1>",
                "<h3>Name</h3>\n" + contactEmail.getFirstName() + " " + contactEmail.getLastName() +
                        "<h3>Email</h3>\n" + contactEmail.getEmail() +
                        "<h3>Phone Number</h3>\n" + contactEmail.getPhoneNum() +
                        "<h3>Project Details</h3>\n" + contactEmail.getProjectDetails()
        );

        // Create a message with the specified information.
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(FROM,FROMNAME));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
        msg.setSubject(SUBJECT);
        msg.setContent(BODY,"text/html");

        // Add a configuration set header. Comment or delete the
        // next line if you are not using a configuration set
        //msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

        // Create a transport.
        Transport transport = session.getTransport();

        // Send the message.
        try
        {
            System.out.println("Sending...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            System.out.println("Email sent!");
        }
        catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
        finally
        {
            // Close and terminate the connection.
            transport.close();
        }
    }
}
