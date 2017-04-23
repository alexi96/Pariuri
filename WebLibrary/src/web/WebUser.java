package web;

import connection.Connection;
import controllers.MainController;
import model.User;

public class WebUser extends User {

    private final Connection mainController = MainController.getInstance().getConnection();

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
        User u = this.mainController.log(user, pass);

        if (u == null) {
            return false;
        }

        super.id = u.getId();
        super.username = u.getUsername();
        this.password = u.getPassword();
        super.firstName = u.getFirstName();
        super.lastName = u.getLastName();
        this.email = u.getEmail();
        return true;
    }

    public boolean create() {
        User u = new User(username, password, super.firstName, super.lastName, email);
        return this.mainController.createUser(u);
    }

    public void edit() {
    }
}
