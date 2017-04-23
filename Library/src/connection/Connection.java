package connection;

import java.util.List;
import model.Country;
import model.Game;
import model.StatisticType;
import model.Team;
import model.User;

public interface Connection {

    int PORT = 3728;

    String test(String t);

    User log(String username, String password);

    boolean createUser(User u);

    Country findCountry(int id);

    List<Country> findCountryes();

    List<Team> findTeams();

    boolean createTeam(Team t);

    List<Game> findFutureGames();
    
    boolean createGame(Game g, Team a, Team b);

    List<StatisticType> findStatisticTypes();

    boolean createScore(Game g, float... statistics);
}
