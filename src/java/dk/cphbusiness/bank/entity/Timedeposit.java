/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed Sadiq
 */
@Entity
@Table(name = "TIMEDEPOSIT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Timedeposit.findAll", query = "SELECT t FROM Timedeposit t"),
    @NamedQuery(name = "Timedeposit.findByAccountNr", query = "SELECT t FROM Timedeposit t WHERE t.accountNr = :accountNr"),
    @NamedQuery(name = "Timedeposit.findByReleaseDate", query = "SELECT t FROM Timedeposit t WHERE t.releaseDate = :releaseDate")})
public class Timedeposit implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNT_NR")
    private String accountNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @JoinColumn(name = "ACCOUNT_NR", referencedColumnName = "ACCOUNT_NR", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Account account;

    public Timedeposit()
    {
    }

    public Timedeposit(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public Timedeposit(String accountNr, Date releaseDate)
    {
	this.accountNr = accountNr;
	this.releaseDate = releaseDate;
    }

    public String getAccountNr()
    {
	return accountNr;
    }

    public void setAccountNr(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public Date getReleaseDate()
    {
	return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
	this.releaseDate = releaseDate;
    }

    public Account getAccount()
    {
	return account;
    }

    public void setAccount(Account account)
    {
	this.account = account;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (accountNr != null ? accountNr.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Timedeposit)) {
	    return false;
	}
	Timedeposit other = (Timedeposit) object;
	if ((this.accountNr == null && other.accountNr != null) || (this.accountNr != null && !this.accountNr.equals(other.accountNr))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Timedeposit[ accountNr=" + accountNr + " ]";
    }
    
}
