/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.TicketDB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex_
 */
@Stateless
public class TicketDBFacade extends AbstractFacade<TicketDB> {

    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TicketDBFacade() {
        super(TicketDB.class);
    }
    
}
