package web;

import ejb.MainControllerRemote;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;

@ManagedBean(name = "user")
@SessionScoped
public class WebUser extends User {

    @EJB
    private MainControllerRemote mainController;

    public WebUser() {
    }

    public WebUser(Integer id) {
        super(id);
    }

    public WebUser(Integer id, String username, String password, String firstName, String lastName) {
        super(id, username, password, firstName, lastName);
    }

    public WebUser(String username, String password, String firstName, String lastName) {
        super(username, password, firstName, lastName);
    }

    public void log() {
        User u = this.mainController.log(super.username, super.password);
        
        if (u == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", "Worng username or password!"));
            return;
        }
        
        super.id = u.getId();
        super.username = u.getUsername();
        this.password = u.getPassword();
        super.firstName = u.getFirstName();
        super.lastName = u.getLastName();
    }

    public void create() {
        User u = new User(username, password, "nimic", "cimic");
        this.mainController.createUser(u);
    }

    public void edit() {
    }
}
