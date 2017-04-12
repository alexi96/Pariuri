package web;

import ejb.MainControllerRemote;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "util")
@ApplicationScoped
public class WebUtility implements Serializable {

    @EJB
    private MainControllerRemote mainController;
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
