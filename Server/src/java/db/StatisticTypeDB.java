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
@Table(name = "statistic_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatisticTypeDB.findAll", query = "SELECT s FROM StatisticTypeDB s")
    , @NamedQuery(name = "StatisticTypeDB.findById", query = "SELECT s FROM StatisticTypeDB s WHERE s.id = :id")
    , @NamedQuery(name = "StatisticTypeDB.findByName", query = "SELECT s FROM StatisticTypeDB s WHERE s.name = :name")})
public class StatisticTypeDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Collection<ResultDB> resultDBCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type")
    private Collection<TicketDB> ticketDBCollection;

    public StatisticTypeDB() {
    }

    public StatisticTypeDB(Integer id) {
        this.id = id;
    }

    public StatisticTypeDB(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @XmlTransient
    public Collection<ResultDB> getResultDBCollection() {
        return resultDBCollection;
    }

    public void setResultDBCollection(Collection<ResultDB> resultDBCollection) {
        this.resultDBCollection = resultDBCollection;
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
        if (!(object instanceof StatisticTypeDB)) {
            return false;
        }
        StatisticTypeDB other = (StatisticTypeDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.StatisticTypeDB[ id=" + id + " ]";
    }
    
}
