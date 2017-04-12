package server;

import connection.Connection;
import hiper.PersistenceUnit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BetServer implements Connection {

    private PersistenceUnit pu;

    public BetServer() {
        try {
            this.pu = new PersistenceUnit("jdbc:mysql://localhost:3306/pariuri", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(BetServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
