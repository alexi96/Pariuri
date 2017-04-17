package controllers;

import connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpc.HiRpc;

public final class MainController {

    private static final MainController INSTANCE = new MainController();
    private final Connection connection;

    private MainController() {
        try {
            this.connection = HiRpc.connectSimple("localhost", Connection.PORT, Connection.class);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static MainController getInstance() {
        return MainController.INSTANCE;
    }
}
