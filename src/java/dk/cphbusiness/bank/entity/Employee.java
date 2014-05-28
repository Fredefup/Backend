/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ahmed Sadiq
 */
@Entity
@Table(name = "EMPLOYEES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e"),
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email"),
    @NamedQuery(name = "Employee.findBySalary", query = "SELECT e FROM Employee e WHERE e.salary = :salary"),
    @NamedQuery(name = "Employee.findByDateofemployment", query = "SELECT e FROM Employee e WHERE e.dateofemployment = :dateofemployment")})
public class Employee implements Serializable
{
    private static final long serialVersionUID = 1L;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "SALARY")
    private double salary;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DATEOFEMPLOYMENT")
    @Temporal(TemporalType.DATE)
    private Date dateofemployment;
    @OneToMany(mappedBy = "managerMail")
    private Collection<Account> accountCollection;
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Employee()
    {
    }

    public Employee(String email)
    {
	this.email = email;
    }

    public Employee(String email, double salary, Date dateofemployment)
    {
	this.email = email;
	this.salary = salary;
	this.dateofemployment = dateofemployment;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public double getSalary()
    {
	return salary;
    }

    public void setSalary(double salary)
    {
	this.salary = salary;
    }

    public Date getDateofemployment()
    {
	return dateofemployment;
    }

    public void setDateofemployment(Date dateofemployment)
    {
	this.dateofemployment = dateofemployment;
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

    public Person getPerson()
    {
	return person;
    }

    public void setPerson(Person person)
    {
	this.person = person;
    }

    @Override
    public int hashCode()
    {
	int hash = 0;
	hash += (email != null ? email.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object)
    {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof Employee)) {
	    return false;
	}
	Employee other = (Employee) object;
	if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Employee[ email=" + email + " ]";
    }
    
}
