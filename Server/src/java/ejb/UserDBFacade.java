/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.UserDB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alex_
 */
@Stateless
public class UserDBFacade extends AbstractFacade<UserDB> {

    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDBFacade() {
        super(UserDB.class);
    }

    public UserDB findByUsername(String username) {
        Query q = this.em.createNamedQuery("UserDB.findByUsername");
        q.setParameter("username", username);
        try {
            return (UserDB) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
        }
        return null;
    }
}
