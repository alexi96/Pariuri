/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.UserDB;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.User;

/**
 *
 * @author alex_
 */
@Stateless
public class MainController implements MainControllerRemote {

    @EJB
    private UserDBFacade userDBFacade;

    @Override
    public String test(String val) {

        return "Salut " + val + " " + System.currentTimeMillis();
    }

// Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")
    @Override
    public User log(String username, String password) {
        UserDB t = this.userDBFacade.findByUsername(username);

        if (t == null) {
            return null;
        }
        if (!t.getPassword().equals(password)) {
            return null;
        }

        User r = MainControllerRemote.model(t, User.class);
        return r;
    }

    @Override
    public boolean createUser(User u) {
        UserDB t = this.userDBFacade.findByUsername(u.getUsername());
        if (t != null) {
            return false;
        }

        UserDB udb = MainControllerRemote.model(t, UserDB.class);
        this.userDBFacade.create(udb);

        final String username = "username@gmail.com";
        final String password = "password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(e.getEmail()));
            message.setSubject("Testing");
            message.setText("Merge?");

            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }
}
