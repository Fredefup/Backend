/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
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
@Table(name = "ACCOUNTS")
@SequenceGenerator(name = "ACCOUNTSEQ", sequenceName = "Account_Seq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountNr", query = "SELECT a FROM Account a WHERE a.accountNr = :accountNr"),
    @NamedQuery(name = "Account.findByBalance", query = "SELECT a FROM Account a WHERE a.balance = :balance"),
    @NamedQuery(name = "Account.findByInterest", query = "SELECT a FROM Account a WHERE a.interest = :interest")})
public class Account implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACCOUNT_NR")
    @GeneratedValue(generator = "ACCOUNTSEQ", strategy = GenerationType.SEQUENCE)
    private String accountNr;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BALANCE")
    private double balance;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTEREST")
    private double interest;
    @JoinColumn(name = "OWNER_CPR", referencedColumnName = "CPR")
    @ManyToOne
    private Person ownerCpr;
    @JoinColumn(name = "MANAGER_MAIL", referencedColumnName = "EMAIL")
    @ManyToOne
    private Employee managerMail;
    @JoinColumn(name = "BANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private Bank bankId;
    @JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Accounttype typeId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Timedeposit timedeposit;
    @OneToMany(mappedBy = "sourceAccount")
    private Collection<Transfer> outGoingTransfers;
    @OneToMany(mappedBy = "targetAccount")
    private Collection<Transfer> inCommingTransfers;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Checking checking;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    private Moneymarket moneymarket;

    public Account()
    {
    }

    public Account(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public Account(String accountNr, double balance, double interest)
    {
	this.accountNr = accountNr;
	this.balance = balance;
	this.interest = interest;
    }

    public String getAccountNr()
    {
	return accountNr;
    }

    public void setAccountNr(String accountNr)
    {
	this.accountNr = accountNr;
    }

    public double getBalance()
    {
	return balance;
    }

    public void setBalance(double balance)
    {
	this.balance = balance;
    }

    public double getInterest()
    {
	return interest;
    }

    public void setInterest(double interest)
    {
	this.interest = interest;
    }

    public Person getOwnerCpr()
    {
	return ownerCpr;
    }

    public void setOwnerCpr(Person ownerCpr)
    {
	this.ownerCpr = ownerCpr;
    }

    public Employee getManagerMail()
    {
	return managerMail;
    }

    public void setManagerMail(Employee managerMail)
    {
	this.managerMail = managerMail;
    }

    public Bank getBankId()
    {
	return bankId;
    }

    public void setBankId(Bank bankId)
    {
	this.bankId = bankId;
    }

    public Accounttype getTypeId()
    {
	return typeId;
    }

    public void setTypeId(Accounttype typeId)
    {
	this.typeId = typeId;
    }

    public Timedeposit getTimedeposit()
    {
	return timedeposit;
    }

    public void setTimedeposit(Timedeposit timedeposit)
    {
	this.timedeposit = timedeposit;
    }

    @XmlTransient
    public Collection<Transfer> getOutgoingTransfers()
    {
	return outGoingTransfers;
    }

    public void setTransferCollection(Collection<Transfer> transferCollection)
    {
	this.outGoingTransfers = transferCollection;
    }

    @XmlTransient
    public Collection<Transfer> getinCommingTransfers()
    {
	return inCommingTransfers;
    }

    public void setTransferCollection1(Collection<Transfer> transferCollection1)
    {
	this.inCommingTransfers = transferCollection1;
    }

    public Collection<Transfer> getTransfers()
    {
	Collection<Transfer> transfers = new ArrayList<>();
	transfers.addAll(inCommingTransfers);
	transfers.addAll(outGoingTransfers);
	return transfers;
	
    }

    public Checking getChecking()
    {
	return checking;
    }

    public void setChecking(Checking checking)
    {
	this.checking = checking;
    }

    public Moneymarket getMoneymarket()
    {
	return moneymarket;
    }

    public void setMoneymarket(Moneymarket moneymarket)
    {
	this.moneymarket = moneymarket;
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
	if (!(object instanceof Account)) {
	    return false;
	}
	Account other = (Account) object;
	if ((this.accountNr == null && other.accountNr != null) || (this.accountNr != null && !this.accountNr.equals(other.accountNr))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Account[ accountNr=" + accountNr + " ]";
    }

}
