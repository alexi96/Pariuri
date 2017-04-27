package connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import model.ComposedTicket;
import model.Country;
import model.Game;
import model.Result;
import model.StatisticType;
import model.Team;
import model.Ticket;
import model.User;

public interface Connection {

    int PORT = 3728;

    String test(String t);

    User log(String username, String password);

    boolean createUser(User u);

    Country findCountry(int id);

    List<Country> findCountryes();

    List<Team> findTeams();

    Team findTeam(int id);

    Team[] findTeams(Game g);

    boolean createTeam(Team t);

    Game findGame(int id);

    List<Game> findFutureGames();

    boolean createGame(Game g, Team a, Team b);

    StatisticType findStatisticType(int id);

    List<StatisticType> findStatisticTypes();

    boolean createScore(Game g, float... statistics);

    Integer createComposedTiket(User user, Collection<Ticket> simpleTickets);

    TreeSet<ComposedTicket> findComposedTickets(User u);

    ArrayList<Ticket> findTickets(ComposedTicket ct);

    Result findResult(Game g, StatisticType t);

    ArrayList<Result> findResults(Game g);

    TreeMap<Ticket, Float> validateTicket(int id);
}
