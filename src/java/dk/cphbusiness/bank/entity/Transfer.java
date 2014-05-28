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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ahmed Sadiq
 */
@Entity
@Table(name = "TRANSFERS")
@XmlRootElement
@SequenceGenerator(name = "TRANSFERSEQ", sequenceName = "Transfer_Seq")
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t"),
    @NamedQuery(name = "Transfer.findByTransferid", query = "SELECT t FROM Transfer t WHERE t.transferid = :transferid"),
    @NamedQuery(name = "Transfer.findByAmount", query = "SELECT t FROM Transfer t WHERE t.amount = :amount"),
    @NamedQuery(name = "Transfer.findByTransactionDate", query = "SELECT t FROM Transfer t WHERE t.transactionDate = :transactionDate")})
public class Transfer implements Serializable, Comparable<Transfer>
{

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSFERID")
    @GeneratedValue(generator = "TRANSFERSEQ", strategy = GenerationType.SEQUENCE)
    private Integer transferid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AMOUNT")
    private double amount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    @JoinColumn(name = "SOURCE_ACCOUNT", referencedColumnName = "ACCOUNT_NR")
    @ManyToOne
    private Account sourceAccount;
    @JoinColumn(name = "TARGET_ACCOUNT", referencedColumnName = "ACCOUNT_NR")
    @ManyToOne
    private Account targetAccount;

    public Transfer()
    {
    }

    public Transfer(Integer transferid)
    {
	this.transferid = transferid;
    }

    public Transfer(Integer transferid, double amount, Date transactionDate)
    {
	this.transferid = transferid;
	this.amount = amount;
	this.transactionDate = transactionDate;
    }

    public Transfer(Integer transferid, double amount, Date transactionDate, Account sourceAccount, Account targetAccount)
    {
	this.transferid = transferid;
	this.amount = amount;
	this.transactionDate = transactionDate;
	this.sourceAccount = sourceAccount;
	sourceAccount.getOutgoingTransfers().add(this);
	sourceAccount.setBalance(sourceAccount.getBalance()-amount);
	this.targetAccount = targetAccount;
	targetAccount.getinCommingTransfers().add(this);
	targetAccount.setBalance(targetAccount.getBalance()+amount);
    }

    public Integer getTransferid()
    {
	return transferid;
    }

    public void setTransferid(Integer transferid)
    {
	this.transferid = transferid;
    }

    public double getAmount()
    {
	return amount;
    }

    public void setAmount(double amount)
    {
	this.amount = amount;
    }

    public Date getTransactionDate()
    {
	return transactionDate;
    }

    public void setTransactionDate(Date transactionDate)
    {
	this.transactionDate = transactionDate;
    }

    public Account getSourceAccount()
    {
	return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount)
    {
	this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount()
    {
	return targetAccount;
    }

    public void setTargetAccount(Account targetAccount)
    {
	this.targetAccount = targetAccount;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (transferid != null ? transferid.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Transfer)) {
	    return false;
	}
	Transfer other = (Transfer) object;
	if ((this.transferid == null && other.transferid != null) || (this.transferid != null && !this.transferid.equals(other.transferid))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Transfer[ transferid=" + transferid + " ]";
    }

    @Override
    public int compareTo(Transfer other)
    {
	return this.transactionDate.compareTo(other.transactionDate);
    }

}
