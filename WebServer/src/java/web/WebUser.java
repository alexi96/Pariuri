package web;

import connection.Connection;
import model.User;

public class WebUser extends User {

    private String sig;

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    private Connection mainController;

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

    public void log() {
        this.log(this.username, this.password);
    }
    
    public void log(String user, String pass) {
        User u = this.mainController.log(user, pass);

        if (u == null) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Worng username or password!"));
            return;
        }

        super.id = u.getId();
        super.username = u.getUsername();
        this.password = u.getPassword();
        super.firstName = u.getFirstName();
        super.lastName = u.getLastName();
        this.email = u.getEmail();
    }

    public String create() {
        User u = new User(username, password, super.firstName, super.lastName, email);
        boolean r = this.mainController.createUser(u);
        if (!r) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "username already exists!", "Username already exists!"));
            return "error";
        }
        return "created";
    }

    public void edit() {
    }
}
