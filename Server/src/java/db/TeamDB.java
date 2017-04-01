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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TeamDB.findAll", query = "SELECT t FROM TeamDB t")
    , @NamedQuery(name = "TeamDB.findById", query = "SELECT t FROM TeamDB t WHERE t.id = :id")
    , @NamedQuery(name = "TeamDB.findByName", query = "SELECT t FROM TeamDB t WHERE t.name = :name")})
public class TeamDB implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "image")
    private byte[] image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    private Collection<PlaysDB> playsDBCollection;
    @JoinColumn(name = "country", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CountryDB country;

    public TeamDB() {
    }

    public TeamDB(Integer id) {
        this.id = id;
    }

    public TeamDB(Integer id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<PlaysDB> getPlaysDBCollection() {
        return playsDBCollection;
    }

    public void setPlaysDBCollection(Collection<PlaysDB> playsDBCollection) {
        this.playsDBCollection = playsDBCollection;
    }

    public CountryDB getCountry() {
        return country;
    }

    public void setCountry(CountryDB country) {
        this.country = country;
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
        if (!(object instanceof TeamDB)) {
            return false;
        }
        TeamDB other = (TeamDB) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.TeamDB[ id=" + id + " ]";
    }
    
}
