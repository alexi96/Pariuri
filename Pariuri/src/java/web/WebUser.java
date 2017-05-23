package web;

import controllers.MainController;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.User;

public class WebUser extends User {

    private transient final MainController mainController = MainController.getInstance();
    private boolean error;

    public WebUser() {
    }

    public WebUser(Integer id) {
        super(id);
    }

    public WebUser(Integer id, String username, String password, String firstName, String lastName, String email) {
        super(id, username, password, firstName, lastName, email);
    }

    public WebUser(String username, String password, String firstName, String lastName, String email) {
        super(username, password, firstName, lastName, email);
    }

    public boolean isError() {
        return error;
    }

    public boolean log() {
        return this.log(this.username, this.password);
    }

    public void logOut() {
        User u = new User();
        super.id = u.getId();
        super.username = u.getUsername();
        this.password = u.getPassword();
        super.firstName = u.getFirstName();
        super.lastName = u.getLastName();
        this.email = u.getEmail();
    }

    public boolean log(String user, String pass) {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PariuriDB");

            User u = this.mainController.log(user, pass, ds);

            if (u == null) {
                this.error = true;
                return false;
            }

            super.id = u.getId();
            super.username = u.getUsername();
            this.password = u.getPassword();
            super.firstName = u.getFirstName();
            super.lastName = u.getLastName();
            this.email = u.getEmail();
            this.error = false;
            return true;
        } catch (NamingException ex) {
            Logger.getLogger(WebUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean create() {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PariuriDB");

            User u = new User(username, password, super.firstName, super.lastName, email);
            return this.mainController.createUser(u, ds);
        } catch (NamingException ex) {
            Logger.getLogger(WebUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void edit() {
    }
}
