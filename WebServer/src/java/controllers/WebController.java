package controllers;

import ejb.MainControllerRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class WebController {

    MainControllerRemote mainController = lookupMainControllerRemote();

    private MainControllerRemote lookupMainControllerRemote() {
        try {
            Context c = new InitialContext();
            return (MainControllerRemote) c.lookup("java:global/Server/MainController!ejb.MainControllerRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
