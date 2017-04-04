package ejb;

import db.GameDB;
import db.PlaysDB;
import db.ResultDB;
import db.StatisticTypeDB;
import db.TeamDB;
import db.UserDB;
import java.util.List;
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
import model.Game;
import model.Team;
import model.User;

@Stateless
public class MainController implements MainControllerRemote {

    @EJB
    private UserDBFacade userDBFacade;
    @EJB
    private TeamDBFacade teamDBFacade;
    @EJB
    private GameDBFacade gameDBFacade;
    @EJB
    private PlaysDBFacade playsDBFacade;
    @EJB
    private StatisticTypeDBFacade statisticTypeDBFacade;
    @EJB
    private ResultDBFacade resultDBFacade;

    @Override
    public String test(String val) {
        return "Salut " + val + " " + System.currentTimeMillis();
    }

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
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("from-email@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
            message.setSubject("Testing");
            message.setText("Merge?");

            Transport.send(message);
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean createTeam(Team t) {
        TeamDB tdb = this.teamDBFacade.findByName(t.getName());
        if (tdb != null) {
            return false;
        }

        tdb = MainControllerRemote.model(t, TeamDB.class);
        this.teamDBFacade.create(tdb);
        return true;
    }

    @Override
    public boolean createGame(Game g, Team a, Team b) {
        PlaysDB pa = new PlaysDB();
        PlaysDB pb = new PlaysDB();

        TeamDB ta = MainControllerRemote.model(a, TeamDB.class);
        TeamDB tb = MainControllerRemote.model(b, TeamDB.class);
        GameDB t = MainControllerRemote.model(g, GameDB.class);

        pa.setGame(t);
        pb.setGame(t);
        pa.setTeam(ta);
        pb.setTeam(tb);

        this.playsDBFacade.create(pa);
        this.playsDBFacade.create(pb);
        return true;
    }

    @Override
    public void createScore(Game g, float... st) {
        GameDB gdb = MainControllerRemote.model(g, GameDB.class);

        List<StatisticTypeDB> all = this.statisticTypeDBFacade.findAll();
        int index = 0;
        for (StatisticTypeDB s : all) {
            ResultDB r = new ResultDB(null, st[index]);
            r.setGame(gdb);
            r.setType(s);
            this.resultDBFacade.create(r);

            ++index;
        }
    }
}
