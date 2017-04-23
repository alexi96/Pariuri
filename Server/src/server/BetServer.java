package server;

import connection.Connection;
import db.CountryDB;
import db.StatisticTypeDB;
import db.UserDB;
import db.TeamDB;
import db.GameDB;
import db.ResultDB;
import db.PlaysDB;
import hiper.EntityManager;
import hiper.PersistenceUnit;
import hiper.QueryBy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Country;
import model.Game;
import model.Team;
import model.User;

public class BetServer implements Connection {

    private PersistenceUnit pu;

    public BetServer() {
        try {
            this.pu = new PersistenceUnit("jdbc:mysql://localhost:3306/pariuri", "root", "");
            this.pu.register(UserDB.class);
            this.pu.register(StatisticTypeDB.class);
            this.pu.register(TeamDB.class);
            this.pu.register(GameDB.class);
            this.pu.register(ResultDB.class);
            this.pu.register(PlaysDB.class);
            this.pu.register(CountryDB.class);
        } catch (SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String test(String t) {
        return "Response: " + t;
    }

    @Override
    public User log(String username, String password) {
        try {
            QueryBy<UserDB> byUsername = new QueryBy<>(UserDB.class);
            byUsername.parameter("username", username);
            List<UserDB> res = this.pu.select(byUsername);
            if (res.isEmpty()) {
                return null;
            }

            UserDB t = res.get(0);
            if (!t.getPassword().equals(password)) {
                return null;
            }

            User r = BetServer.model(t, User.class);
            return r;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean createUser(User u) {
        try {
            QueryBy<UserDB> byUsername = new QueryBy<>(UserDB.class);
            byUsername.parameter("username", u.getUsername());
            List<UserDB> res = this.pu.select(byUsername);
            if (!res.isEmpty()) {
                return false;
            }

            UserDB udb = BetServer.model(u, UserDB.class);
            this.pu.create(udb);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        String to = u.getEmail();
        String host = "smtp.gmail.com";
        final String username = "betting231@gmail.com";
        final String password = "bettingproiect";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("CS GO Betting");
            message.setContent("Merge?\n<h1 style=\"color: red\">MERGE BAAAA</h1>", "text/html");

            Transport.send(message);
            System.out.println("sent");
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Country> findCountryes() {
        try {
            List<CountryDB> all = this.pu.select(CountryDB.class);
            List<Country> res = new ArrayList<>();
            all.forEach((c) -> res.add(BetServer.model(c, Country.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Country findCountry(int id) {
        try {
            CountryDB c = this.pu.select(CountryDB.class, id);
            return BetServer.model(c, Country.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Team> findTeams() {
        try {
            List<TeamDB> all = this.pu.select(TeamDB.class);
            List<Team> res = new ArrayList<>();
            all.forEach((c) -> res.add(BetServer.model(c, Team.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean createTeam(Team t) {
        try {
            QueryBy<TeamDB> byName = new QueryBy<>(TeamDB.class);
            byName.parameter("name", t.getName());
            List<TeamDB> all = this.pu.select(byName);
            if (!all.isEmpty()) {
                return false;
            }

            TeamDB tdb = BetServer.model(t, TeamDB.class);
            this.pu.create(tdb);
            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean createGame(Game g, Team a, Team b) {
        PlaysDB pa = new PlaysDB();
        PlaysDB pb = new PlaysDB();

        TeamDB ta = BetServer.model(a, TeamDB.class);
        TeamDB tb = BetServer.model(b, TeamDB.class);
        GameDB t = BetServer.model(g, GameDB.class);

        pa.setTeam(ta.getId());
        pb.setTeam(tb.getId());

        try {
            this.pu.create(t);

            QueryBy<GameDB> q = new QueryBy<>(GameDB.class);
            q.parameter("date", EntityManager.getDatabaseFormat().format(t.getDate()));
            q.parameter("name", t.getName());
            
            t = this.pu.select(q).get(0);
            
            pa.setGame(t.getId());
            pb.setGame(t.getId());

            this.pu.create(pa);
            this.pu.create(pb);
            return true;
        } catch (IllegalArgumentException | SQLException | IndexOutOfBoundsException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void createScore(Game g, float... statistics) {
        try {
            GameDB gdb = BetServer.model(g, GameDB.class);

            List<StatisticTypeDB> all = this.pu.select(StatisticTypeDB.class);
            int index = 0;
            for (StatisticTypeDB s : all) {
                ResultDB r = new ResultDB();
                r.setValue(statistics[index]);
                r.setGame(gdb.getId());
                r.setType(s.getId());
                this.pu.create(r);

                ++index;
            }
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T, F> T model(F fo, Class<T> tc) {
        try {
            Class<F> fc = (Class<F>) fo.getClass();
            Constructor<T> tCons = tc.getDeclaredConstructor();
            tCons.setAccessible(true);
            T to = tCons.newInstance();

            Field[] tfs = tc.getDeclaredFields();
            for (Field tf : tfs) {
                if (Modifier.isStatic(tf.getModifiers())) {
                    continue;
                }

                try {
                    Field ff = fc.getDeclaredField(tf.getName());

                    ff.setAccessible(true);
                    tf.setAccessible(true);

                    if (ff.getType().equals(tf.getType())) {
                        tf.set(to, ff.get(fo));
                    } else {
                        Object t = BetServer.model(ff.get(fo), tf.getType());
                        if (t != null) {
                            tf.set(to, t);
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                }
            }
            return to;
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
