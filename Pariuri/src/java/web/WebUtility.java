package web;

import connection.Connection;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebUtility implements Serializable {

    private transient Connection mainController;
    private String text;
    private String serverMessage = "nimic";

    public WebUtility() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void doStuff() {
        System.out.println("DID IT: " + this.text);
        this.serverMessage = mainController.test(this.text);
    }
}
