/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import db.GameDB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alex_
 */
@Stateless
public class GameDBFacade extends AbstractFacade<GameDB> {

    @PersistenceContext(unitName = "ServerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GameDBFacade() {
        super(GameDB.class);
    }
    
}
