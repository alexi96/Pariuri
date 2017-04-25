package server;

import connection.Connection;
import db.ComposedTicketDB;
import db.CountryDB;
import db.StatisticTypeDB;
import db.UserDB;
import db.TeamDB;
import db.GameDB;
import db.ResultDB;
import db.PlaysDB;
import db.TicketDB;
import hiper.EntityManager;
import hiper.PersistenceUnit;
import hiper.QueryBy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
import model.StatisticType;
import model.Team;
import model.Ticket;
import model.User;
import utilities.Conversion;

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
            this.pu.register(TicketDB.class);
            this.pu.register(ComposedTicketDB.class);
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

            User r = Conversion.model(t, User.class);
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

            UserDB udb = Conversion.model(u, UserDB.class);
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
            all.forEach((c) -> res.add(Conversion.model(c, Country.class)));
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
            return Conversion.model(c, Country.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Team findTeam(int id) {
        try {
            TeamDB c = this.pu.select(TeamDB.class, id);
            return Conversion.model(c, Team.class);
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
            all.forEach((c) -> res.add(Conversion.model(c, Team.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public Team[] findTeams(Game g) {
        try {
            QueryBy<PlaysDB> q = new QueryBy<>(PlaysDB.class);
            q.parameter("game", "" + g.getId());
            List<PlaysDB> ps = this.pu.select(q);

            Team[] res = new Team[2];

            TeamDB t = this.pu.select(TeamDB.class, ps.get(0).getTeam());
            res[0] = Conversion.model(t, Team.class);

            t = this.pu.select(TeamDB.class, ps.get(1).getTeam());
            res[1] = Conversion.model(t, Team.class);
            return res;
        } catch (Exception ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

            TeamDB tdb = Conversion.model(t, TeamDB.class);
            this.pu.create(tdb);
            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Game findGame(int id) {
        try {
            GameDB c = this.pu.select(GameDB.class, id);
            return Conversion.model(c, Game.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Game> findFutureGames() {
        try {
            List<GameDB> all = this.pu.selectNative(GameDB.class, "select g.* from `game` g where not exists (select id from `result` where game = g.id )");
            List<Game> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, Game.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean createGame(Game g, Team a, Team b) {
        PlaysDB pa = new PlaysDB();
        PlaysDB pb = new PlaysDB();

        TeamDB ta = Conversion.model(a, TeamDB.class);
        TeamDB tb = Conversion.model(b, TeamDB.class);
        GameDB t = Conversion.model(g, GameDB.class);

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
    public StatisticType findStatisticType(int id) {
        try {
            StatisticTypeDB c = this.pu.select(StatisticTypeDB.class, id);
            return Conversion.model(c, StatisticType.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<StatisticType> findStatisticTypes() {
        try {
            List<StatisticTypeDB> all = this.pu.select(StatisticTypeDB.class);
            List<StatisticType> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, StatisticType.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    @Override
    public boolean createScore(Game g, float... statistics) {
        try {
            GameDB gdb = Conversion.model(g, GameDB.class);

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
            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean createComposedTiket(User user, Collection<Ticket> simpleTickets) {
        try {
            ComposedTicketDB ct = new ComposedTicketDB();
            ct.setUser(user.getId());

            this.pu.create(ct);
            int ctu = this.pu.lastId();
            
            for (Ticket s : simpleTickets) {
                s.setComposedTicket(ctu);
                TicketDB sdb = Conversion.model(s, TicketDB.class);
                this.pu.create(sdb);
            }

            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
