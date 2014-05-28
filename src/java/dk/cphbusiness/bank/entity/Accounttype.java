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
@Table(name = "ACCOUNTTYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accounttype.findAll", query = "SELECT a FROM Accounttype a"),
    @NamedQuery(name = "Accounttype.findByType", query = "SELECT a FROM Accounttype a WHERE a.type = :type"),
    @NamedQuery(name = "Accounttype.findById", query = "SELECT a FROM Accounttype a WHERE a.id = :id")})
public class Accounttype implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TYPE")
    private String type;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @OneToMany(mappedBy = "typeId")
    private Collection<Account> accountCollection;

    public Accounttype()
    {
    }

    public Accounttype(Integer id)
    {
	this.id = id;
    }

    public Accounttype(Integer id, String type)
    {
	this.id = id;
	this.type = type;
    }

    public String getType()
    {
	return type;
    }

    public void setType(String type)
    {
	this.type = type;
    }

    public Integer getId()
    {
	return id;
    }

    public void setId(Integer id)
    {
	this.id = id;
    }

    @XmlTransient
    public Collection<Account> getAccountCollection()
    {
	return accountCollection;
    }

    public void setAccountCollection(Collection<Account> accountCollection)
    {
	this.accountCollection = accountCollection;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Accounttype)) {
	    return false;
	}
	Accounttype other = (Accounttype) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Accounttype[ id=" + id + " ]";
    }
    
}
