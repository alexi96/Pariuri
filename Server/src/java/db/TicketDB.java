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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex_
 */
@Entity
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketDB.findAll", query = "SELECT t FROM TicketDB t")
    , @NamedQuery(name = "TicketDB.findById", query = "SELECT t FROM TicketDB t WHERE t.id = :id")
    , @NamedQuery(name = "TicketDB.findByValue", query = "SELECT t FROM TicketDB t WHERE t.value = :value")
    , @NamedQuery(name = "TicketDB.findByOperation", query = "SELECT t FROM TicketDB t WHERE t.operation = :operation")})
public class TicketDB implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private float value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "operation")
    private short operation;
    @JoinColumn(name = "composed_ticket", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ComposedTicketDB composedTicket;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatisticTypeDB type;

    public TicketDB() {
    }

    public TicketDB(Integer id) {
        this.id = id;
    }

    public TicketDB(Integer id, float value, short operation) {
        this.id = id;
        this.value = value;
        this.operation = operation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public short getOperation() {
        return operation;
    }

    public void setOperation(short operation) {
        this.operation = operation;
    }

    public ComposedTicketDB getComposedTicket() {
        return composedTicket;
    }

    public void setComposedTicket(ComposedTicketDB composedTicket) {
        this.composedTicket = composedTicket;
    }

    public StatisticTypeDB getType() {
        return type;
    }

    public void setType(StatisticTypeDB type) {
        this.type = type;
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
        if (!(object instanceof TicketDB)) {
            return false;
        }
        TicketDB other = (TicketDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TicketDB[ id=" + id + " ]";
    }
    
}
