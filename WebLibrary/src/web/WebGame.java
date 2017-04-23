package web;

import connection.Connection;
import controllers.MainController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Game;
import model.Team;

public class WebGame extends Game {

    private Connection connection = MainController.getInstance().getConnection();
    
    public WebGame() {
        super.date = new Date();
    }

    public WebGame(Integer id) {
        super(id);
        super.date = new Date();
    }

    public WebGame(Integer id, String name, float chance, Date date, String description) {
        super(id, name, chance, date, description);
    }

    public WebGame(String name, float chance, Date date, String description) {
        super(name, chance, date, description);
    }

    public void setDateAsText(String ds) {
        try {
            this.date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(ds);
        } catch (ParseException ex) {
            Logger.getLogger(WebGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean create(int ta, int tb) {
        Game g = new Game(name, super.chance, date, description);
        return this.connection.createGame(g, new Team(ta), new Team(tb));
    }
}
