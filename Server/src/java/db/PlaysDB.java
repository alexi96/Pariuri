/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex_
 */
@Entity
@Table(name = "plays")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaysDB.findAll", query = "SELECT p FROM PlaysDB p")
    , @NamedQuery(name = "PlaysDB.findById", query = "SELECT p FROM PlaysDB p WHERE p.id = :id")})
public class PlaysDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "team", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TeamDB team;
    @JoinColumn(name = "game", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GameDB game;

    public PlaysDB() {
    }

    public PlaysDB(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TeamDB getTeam() {
        return team;
    }

    public void setTeam(TeamDB team) {
        this.team = team;
    }

    public GameDB getGame() {
        return game;
    }

    public void setGame(GameDB game) {
        this.game = game;
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
        if (!(object instanceof PlaysDB)) {
            return false;
        }
        PlaysDB other = (PlaysDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.PlaysDB[ id=" + id + " ]";
    }
    
}
