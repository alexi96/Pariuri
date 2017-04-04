/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.TeamDB;
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
public class TeamDBFacade extends AbstractFacade<TeamDB> {

    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TeamDBFacade() {
        super(TeamDB.class);
    }

    public TeamDB findByName(String name) {
        Query q = this.em.createNamedQuery("TeamDB.findByName");
        q.setParameter("name", name);
        try {
            return (TeamDB) q.getSingleResult();
        } catch (NoResultException | NonUniqueResultException ex) {
        }
        return null;
    }
    
}
