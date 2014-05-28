/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed Sadiq
 */
@Entity
@Table(name = "MONEYMARKET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Moneymarket.findAll", query = "SELECT m FROM Moneymarket m"),
    @NamedQuery(name = "Moneymarket.findByAccountNr", query = "SELECT m FROM Moneymarket m WHERE m.accountNr = :accountNr"),
    @NamedQuery(name = "Moneymarket.findByMinBalance", query = "SELECT m FROM Moneymarket m WHERE m.minBalance = :minBalance")})
public class Moneymarket implements Serializable
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
    @Column(name = "MIN_BALANCE")
    private double minBalance;
    @JoinColumn(name = "ACCOUNT_NR", referencedColumnName = "ACCOUNT_NR", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Account account;

    public Moneymarket()
    {
    }

    public Moneymarket(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public Moneymarket(String accountNr, double minBalance)
    {
	this.accountNr = accountNr;
	this.minBalance = minBalance;
    }

    public String getAccountNr()
    {
	return accountNr;
    }

    public void setAccountNr(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public double getMinBalance()
    {
	return minBalance;
    }

    public void setMinBalance(double minBalance)
    {
	this.minBalance = minBalance;
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
	if (!(object instanceof Moneymarket)) {
	    return false;
	}
	Moneymarket other = (Moneymarket) object;
	if ((this.accountNr == null && other.accountNr != null) || (this.accountNr != null && !this.accountNr.equals(other.accountNr))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Moneymarket[ accountNr=" + accountNr + " ]";
    }
    
}
