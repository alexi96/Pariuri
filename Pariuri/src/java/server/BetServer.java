package server;

import db.ComposedTicketDB;
import db.CountryDB;
import db.StatisticTypeDB;
import db.UserDB;
import db.TeamDB;
import db.GameDB;
import db.ResultDB;
import db.PlaysDB;
import db.TicketDB;
import hiper.PersistenceUnit;
import hiper.QueryBy;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;
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
import javax.sql.DataSource;
import model.ComposedTicket;
import model.Country;
import model.Game;
import model.Result;
import model.StatisticType;
import model.Team;
import model.Ticket;
import model.User;
import utilities.Conversion;

public class BetServer {

    private final PersistenceUnit pu = new PersistenceUnit();

    public BetServer() {
        this.pu.register(UserDB.class);
        this.pu.register(StatisticTypeDB.class);
        this.pu.register(TeamDB.class);
        this.pu.register(GameDB.class);
        this.pu.register(ResultDB.class);
        this.pu.register(PlaysDB.class);
        this.pu.register(CountryDB.class);
        this.pu.register(TicketDB.class);
        this.pu.register(ComposedTicketDB.class);
    }

    public String test(String t) {
        return "Response: " + t;
    }

    public User log(String username, String password, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            QueryBy<UserDB> byUsername = new QueryBy<>(UserDB.class);
            byUsername.parameter("username", username);
            List<UserDB> res = this.pu.select(byUsername, st);
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

    public boolean createUser(User u, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            QueryBy<UserDB> byUsername = new QueryBy<>(UserDB.class);
            byUsername.parameter("username", u.getUsername());
            List<UserDB> res = this.pu.select(byUsername, st);
            if (!res.isEmpty()) {
                return false;
            }

            UserDB udb = Conversion.model(u, UserDB.class);
            this.pu.create(udb, st);
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
            message.setContent("<h1 style=\"color: red\">Bun venit la CS GO Betting!</h1>\n"
                    + "<p>Contul dumneavoastra a fost creeat!</p>", "text/html");

            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("sent");
                } catch (MessagingException ex) {
                    Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        } catch (MessagingException e) {
            return false;
        }

        return true;
    }

    @Deprecated
    public List<Country> findCountryes(DataSource ds) {
        try (Connection con = ds.getConnection()) {
            return this.findCountryes(con);
        } catch (SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public List<Country> findCountryes(Connection con) {
        try (Statement st = con.createStatement()) {
            List<CountryDB> all = this.pu.select(CountryDB.class, st);
            List<Country> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, Country.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public Country findCountry(int id, Connection con) {
        try (Statement st = con.createStatement()) {
            CountryDB c = this.pu.select(CountryDB.class, st, id);
            return Conversion.model(c, Country.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public Country findCountry(int id, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            CountryDB c = this.pu.select(CountryDB.class, st, id);
            return Conversion.model(c, Country.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Team findTeam(int id, Connection con) {
        try (Statement st = con.createStatement()) {
            TeamDB c = this.pu.select(TeamDB.class, st, id);
            return Conversion.model(c, Team.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public Team findTeam(int id, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            TeamDB c = this.pu.select(TeamDB.class, st, id);
            return Conversion.model(c, Team.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public List<Team> findTeams(DataSource ds) {
        try (Connection con = ds.getConnection()) {
            return this.findTeams(con);
        } catch (SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public List<Team> findTeams(Connection con) {
        try (Statement st = con.createStatement()) {
            List<TeamDB> all = this.pu.select(TeamDB.class, st);
            List<Team> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, Team.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public Team[] findTeams(Game g, Connection con) {
        try (Statement st = con.createStatement()) {
            QueryBy<PlaysDB> q = new QueryBy<>(PlaysDB.class);
            q.parameter("game", "" + g.getId());
            List<PlaysDB> ps = this.pu.select(q, st);

            if (ps.isEmpty()) {
                return null;
            }

            Team[] res = new Team[2];

            TeamDB t = this.pu.select(TeamDB.class, st, ps.get(0).getTeam());
            res[0] = Conversion.model(t, Team.class);

            t = this.pu.select(TeamDB.class, st, ps.get(1).getTeam());
            res[1] = Conversion.model(t, Team.class);
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public Team[] findTeams(Game g, DataSource ds) {
        try (Connection con = ds.getConnection()) {
            return this.findTeams(g, con);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public boolean createTeam(Team t, DataSource ds) {
        try (Connection con = ds.getConnection()) {
            return this.createTeam(t, con);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean createTeam(Team t, Connection con) {
        try (Statement st = con.createStatement()) {
            QueryBy<TeamDB> byName = new QueryBy<>(TeamDB.class);
            byName.parameter("name", t.getName());
            List<TeamDB> all = this.pu.select(byName, st);
            if (!all.isEmpty()) {
                return false;
            }

            TeamDB tdb = Conversion.model(t, TeamDB.class);
            this.pu.create(tdb, st);
            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Game findGame(int id, Connection con) {
        try (Statement st = con.createStatement()) {
            GameDB c = this.pu.select(GameDB.class, st, id);
            return Conversion.model(c, Game.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public Game findGame(int id, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            GameDB c = this.pu.select(GameDB.class, st, id);
            return Conversion.model(c, Game.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Game> findFutureGames(Connection con) {
        try (Statement st = con.createStatement()) {
            List<GameDB> all = this.pu.selectNative(GameDB.class, "select g.* from `game` g where not exists (select id from `result` where game = g.id )", st);
            List<Game> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, Game.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public boolean createGame(Game g, Team a, Team b, DataSource ds) {
        PlaysDB pa = new PlaysDB();
        PlaysDB pb = new PlaysDB();

        TeamDB ta = Conversion.model(a, TeamDB.class);
        TeamDB tb = Conversion.model(b, TeamDB.class);
        GameDB t = Conversion.model(g, GameDB.class);

        pa.setTeam(ta.getId());
        pb.setTeam(tb.getId());

        try (Statement st = ds.getConnection().createStatement()) {
            this.pu.create(t, st);
            t.setId(this.pu.lastId(st));

            pa.setGame(t.getId());
            pb.setGame(t.getId());

            this.pu.create(pa, st);
            this.pu.create(pb, st);
            return true;
        } catch (IllegalArgumentException | SQLException | IndexOutOfBoundsException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public StatisticType findStatisticType(int id, Connection con) {
        try (Statement st = con.createStatement()) {
            StatisticTypeDB c = this.pu.select(StatisticTypeDB.class, st, id);
            return Conversion.model(c, StatisticType.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Deprecated
    public StatisticType findStatisticType(int id, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            StatisticTypeDB c = this.pu.select(StatisticTypeDB.class, st, id);
            return Conversion.model(c, StatisticType.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<StatisticType> findStatisticTypes(Connection con) {
        try (Statement st = con.createStatement()) {
            List<StatisticTypeDB> all = this.pu.select(StatisticTypeDB.class, st);
            List<StatisticType> res = new ArrayList<>();
            all.forEach((c) -> res.add(Conversion.model(c, StatisticType.class)));
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public boolean createScore(Game g, Connection con, float... statistics) {
        try (Statement st = con.createStatement()) {
            GameDB gdb = Conversion.model(g, GameDB.class);

            List<StatisticTypeDB> all = this.pu.select(StatisticTypeDB.class, st);
            int index = 0;
            for (StatisticTypeDB s : all) {
                ResultDB r = new ResultDB();
                r.setValue(statistics[index]);
                r.setGame(gdb.getId());
                r.setType(s.getId());
                this.pu.create(r, st);

                ++index;
            }
            return true;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Integer createComposedTicket(ComposedTicket ticket, Collection<Ticket> simpleTickets, Connection con) {
        try (Statement st = con.createStatement()) {
            ComposedTicketDB ct = new ComposedTicketDB(ticket.getAmmount(), ticket.getUser(), new Date(), false);

            this.pu.create(ct, st);
            int ctu = this.pu.lastId(st);

            for (Ticket s : simpleTickets) {
                s.setComposedTicket(ctu);
                TicketDB sdb = Conversion.model(s, TicketDB.class);
                this.pu.create(sdb, st);
            }

            return ctu;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TreeSet<ComposedTicket> findComposedTickets(User u, Connection con) {
        return this.findComposedTickets(u, null, con);
    }

    public TreeSet<ComposedTicket> findComposedTickets(User u, Boolean validated, Connection con) {
        try (Statement st = con.createStatement()) {
            QueryBy q = new QueryBy(ComposedTicketDB.class);
            q.parameter("user", "" + u.getId());
            if (validated != null) {
                q.parameter("validated", "" + validated);
            }

            TreeSet<ComposedTicket> res = new TreeSet<>();
            List<ComposedTicketDB> ts = this.pu.select(q, st);
            ts.forEach((t) -> {
                res.add(Conversion.model(t, ComposedTicket.class));
            });
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new TreeSet<>();
    }

    public ArrayList<Ticket> findTickets(ComposedTicket ct, Connection con) {
        try (Statement st = con.createStatement()) {
            QueryBy q = new QueryBy(TicketDB.class);
            q.parameter("composedTicket", "" + ct.getId());

            ArrayList<Ticket> res = new ArrayList<>();
            List<TicketDB> ts = this.pu.select(q, st);
            ts.forEach((t) -> {
                res.add(Conversion.model(t, Ticket.class));
            });
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public ArrayList<Result> findResults(Game g, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            QueryBy q = new QueryBy(ResultDB.class);
            q.parameter("game", "" + g.getId());

            ArrayList<Result> res = new ArrayList<>();
            List<ResultDB> ts = this.pu.select(q, st);
            ts.forEach((t) -> {
                res.add(Conversion.model(t, Result.class));
            });
            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }

    public Result findResult(Game g, StatisticType t, Connection con) {
        try (Statement st = con.createStatement()) {
            QueryBy q = new QueryBy(ResultDB.class);
            q.parameter("game", "" + g.getId());
            q.parameter("type", "" + t.getId());

            List<ResultDB> ts = this.pu.select(q, st);
            if (ts.isEmpty()) {
                return null;
            }
            return Conversion.model(ts.get(0), Result.class);
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private boolean ticketWon(Ticket t, Result res, StatisticType type) {
        float error = t.getOperation() * type.getDeviation();
        return Math.abs(res.getValue() - t.getValue()) <= error;
    }

    public Float validateTicket(int id, Connection con) {
        try (Statement st = con.createStatement()) {
            ComposedTicketDB ct = this.pu.select(ComposedTicketDB.class, st, id);

            if (ct == null || ct.isValidated()) {
                return null;
            }

            List<Ticket> ts = this.findTickets(new ComposedTicket(id), con);
            float res = ct.getAmmount();

            StatisticType type;
            Result r;

            for (Ticket t : ts) {
                type = this.findStatisticType(t.getType(), con);
                r = this.findResult(new Game(t.getGame()), type, con);

                if (r == null) {
                    return null;
                }

                float multy;
                switch (t.getOperation()) {
                    case 1:
                        multy = type.getMediumMultiply();
                        break;
                    case 2:
                        multy = type.getFarMultiply();
                        break;
                    default:
                        multy = type.getExactMultiply();
                        break;
                }

                if (this.ticketWon(t, r, type)) {
                    res *= multy;
                } else {
                    res = 0f;
                    break;
                }
            }

            ct.setValidated(true);
            this.pu.edit(ct, st);

            return res;
        } catch (IllegalArgumentException | SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void forgotPassword(String username, String email, DataSource ds) {
        try (Connection con = ds.getConnection(); Statement st = con.createStatement()) {
            QueryBy<UserDB> byUsername = new QueryBy<>(UserDB.class);
            byUsername.parameter("username", username);
            byUsername.parameter("email", email);
            List<UserDB> res = this.pu.select(byUsername, st);
            if (res.isEmpty()) {
                return;
            }

            UserDB t = res.get(0);

            String to = t.getEmail();
            String host = "smtp.gmail.com";
            final String usn = "betting231@gmail.com";
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
                    return new PasswordAuthentication(usn, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(usn));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            message.setSubject("Recuperare parola CS GO Betting");
            message.setContent("<h1 style=\"color: red\">Recuperare parola CS GO Betting!</h1>\n"
                    + "<p>Parola dumneavoastra este " + t.getPassword() + "</p>"
                    + "<p>Fii mai atent!</p>", "text/html");

            new Thread(() -> {
                try {
                    Transport.send(message);
                    System.out.println("sent");
                } catch (MessagingException ex) {
                    Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }).start();
        } catch (IllegalArgumentException | SQLException | MessagingException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
