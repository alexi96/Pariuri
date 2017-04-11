package controllers;

import ejb.MainControllerRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WebController {
    static MainControllerRemote mainController = lookupMainControllerRemote();

    private static MainControllerRemote lookupMainControllerRemote() {
        try {
            Context c = new InitialContext();
            return (MainControllerRemote) c.lookup("java:global/Server/MainController!ejb.MainControllerRemote");
        } catch (NamingException ne) {
            Logger.getLogger(WebController.class.getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
