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
@Table(name = "result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResultDB.findAll", query = "SELECT r FROM ResultDB r")
    , @NamedQuery(name = "ResultDB.findById", query = "SELECT r FROM ResultDB r WHERE r.id = :id")
    , @NamedQuery(name = "ResultDB.findByValue", query = "SELECT r FROM ResultDB r WHERE r.value = :value")})
public class ResultDB implements Serializable {

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
    @JoinColumn(name = "game", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private GameDB game;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatisticTypeDB type;

    public ResultDB() {
    }

    public ResultDB(Integer id) {
        this.id = id;
    }

    public ResultDB(Integer id, float value) {
        this.id = id;
        this.value = value;
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

    public GameDB getGame() {
        return game;
    }

    public void setGame(GameDB game) {
        this.game = game;
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
        if (!(object instanceof ResultDB)) {
            return false;
        }
        ResultDB other = (ResultDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ResultDB[ id=" + id + " ]";
    }
    
}
