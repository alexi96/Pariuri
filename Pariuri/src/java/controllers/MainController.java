package controllers;

import connection.Connection;
import server.BetServer;

public final class MainController extends BetServer {

    private static final MainController INSTANCE = new MainController();

    private MainController() {
    }

    public static MainController getInstance() {
        return MainController.INSTANCE;
    }
}
