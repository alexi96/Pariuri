package ejb;

import db.GameDB;
import db.PlaysDB;
import db.ResultDB;
import db.StatisticTypeDB;
import db.TeamDB;
import db.UserDB;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
