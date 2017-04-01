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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alex_
 */
@Entity
@Table(name = "game")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GameDB.findAll", query = "SELECT g FROM GameDB g")
    , @NamedQuery(name = "GameDB.findById", query = "SELECT g FROM GameDB g WHERE g.id = :id")
    , @NamedQuery(name = "GameDB.findByName", query = "SELECT g FROM GameDB g WHERE g.name = :name")
    , @NamedQuery(name = "GameDB.findByChance", query = "SELECT g FROM GameDB g WHERE g.chance = :chance")})
public class GameDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "chance")
    private float chance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private Collection<ResultDB> resultDBCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private Collection<ComposedTicketDB> composedTicketDBCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "game")
    private Collection<PlaysDB> playsDBCollection;

    public GameDB() {
    }

    public GameDB(Integer id) {
        this.id = id;
    }

    public GameDB(Integer id, String name, float chance) {
        this.id = id;
        this.name = name;
        this.chance = chance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    @XmlTransient
    public Collection<ResultDB> getResultDBCollection() {
        return resultDBCollection;
    }

    public void setResultDBCollection(Collection<ResultDB> resultDBCollection) {
        this.resultDBCollection = resultDBCollection;
    }

    @XmlTransient
    public Collection<ComposedTicketDB> getComposedTicketDBCollection() {
        return composedTicketDBCollection;
    }

    public void setComposedTicketDBCollection(Collection<ComposedTicketDB> composedTicketDBCollection) {
        this.composedTicketDBCollection = composedTicketDBCollection;
    }

    @XmlTransient
    public Collection<PlaysDB> getPlaysDBCollection() {
        return playsDBCollection;
    }

    public void setPlaysDBCollection(Collection<PlaysDB> playsDBCollection) {
        this.playsDBCollection = playsDBCollection;
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
        if (!(object instanceof GameDB)) {
            return false;
        }
        GameDB other = (GameDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.GameDB[ id=" + id + " ]";
    }
    
}
