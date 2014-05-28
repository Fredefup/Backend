/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author Ahmed Sadiq
 */
@Entity
@Table(name = "POSTAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postal.findAll", query = "SELECT p FROM Postal p"),
    @NamedQuery(name = "Postal.findByCode", query = "SELECT p FROM Postal p WHERE p.code = :code"),
    @NamedQuery(name = "Postal.findByDistrict", query = "SELECT p FROM Postal p WHERE p.district = :district")})
public class Postal implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE")
    private Integer code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "DISTRICT")
    private String district;
    @OneToMany(mappedBy = "zipcode")
    private Collection<Person> personCollection;

    public Postal()
    {
    }

    public Postal(Integer code)
    {
	this.code = code;
    }

    public Postal(Integer code, String district)
    {
	this.code = code;
	this.district = district;
    }

    public Integer getCode()
    {
	return code;
    }

    public void setCode(Integer code)
    {
	this.code = code;
    }

    public String getDistrict()
    {
	return district;
    }

    public void setDistrict(String district)
    {
	this.district = district;
    }

    @XmlTransient
    public Collection<Person> getPersonCollection()
    {
	return personCollection;
    }

    public void setPersonCollection(Collection<Person> personCollection)
    {
	this.personCollection = personCollection;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (code != null ? code.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Postal)) {
	    return false;
	}
	Postal other = (Postal) object;
	if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return ""+code;
    }
    
}
