/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex_
 */
@Entity
@Table(name = "composed_ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComposedTicketDB.findAll", query = "SELECT c FROM ComposedTicketDB c")
    , @NamedQuery(name = "ComposedTicketDB.findById", query = "SELECT c FROM ComposedTicketDB c WHERE c.id = :id")})
public class ComposedTicketDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "game", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GameDB game;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserDB user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "composedTicket")
    private Collection<TicketDB> ticketDBCollection;

    public ComposedTicketDB() {
    }

    public ComposedTicketDB(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GameDB getGame() {
        return game;
    }

    public void setGame(GameDB game) {
        this.game = game;
    }

    public UserDB getUser() {
        return user;
    }

    public void setUser(UserDB user) {
        this.user = user;
    }

    @XmlTransient
    public Collection<TicketDB> getTicketDBCollection() {
        return ticketDBCollection;
    }

    public void setTicketDBCollection(Collection<TicketDB> ticketDBCollection) {
        this.ticketDBCollection = ticketDBCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComposedTicketDB)) {
            return false;
        }
        ComposedTicketDB other = (ComposedTicketDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ComposedTicketDB[ id=" + id + " ]";
    }
    
}
