/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.PlaysDB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex_
 */
@Stateless
public class PlaysDBFacade extends AbstractFacade<PlaysDB> {

    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaysDBFacade() {
        super(PlaysDB.class);
    }
    
}
