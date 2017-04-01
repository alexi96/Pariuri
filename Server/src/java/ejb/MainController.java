/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.TeamDB;
import db.UserDB;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import model.Country;
import model.Team;
import model.User;

/**
 *
 * @author alex_
 */
@Stateless
public class MainController implements MainControllerRemote {

    @EJB
    private UserDBFacade userDBFacade;

    @Override
    public String test(String val) {

        return "Salut " + val + " " + System.currentTimeMillis();
    }

// Add business logic below. (Right-click in editor and choose
// "Insert Code > Add Business Method")
    @Override
    public User log(String username, String password) {
        UserDB t = this.userDBFacade.findByUsername(username);

        if (t == null) {
            return null;
        }
        User r = MainControllerRemote.model(t, User.class);

        return r;
    }

    @Override
    public boolean createUser(User u) {
        UserDB t = this.userDBFacade.findByUsername(u.getUsername());
        if (t != null) {
            return false;
        }

        UserDB udb = MainControllerRemote.model(t, UserDB.class);
        this.userDBFacade.create(udb);
        return true;
    }
}
