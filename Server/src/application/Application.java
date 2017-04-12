/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import connection.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import rpc.HiRpc;
import server.BetServer;

/**
 *
 * @author alex_
 */
public class Application {

    public static void main(String[] args) {
        try {
            HiRpc.start(new BetServer(), Connection.PORT);
        } catch (IOException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
