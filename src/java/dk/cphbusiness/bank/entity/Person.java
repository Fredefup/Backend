/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.cphbusiness.bank.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "PERSONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
    @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email"),
    @NamedQuery(name = "Person.findByCpr", query = "SELECT p FROM Person p WHERE p.cpr = :cpr"),
    @NamedQuery(name = "Person.findByTitle", query = "SELECT p FROM Person p WHERE p.title = :title"),
    @NamedQuery(name = "Person.findByFirstname", query = "SELECT p FROM Person p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Person.findByLastname", query = "SELECT p FROM Person p WHERE p.lastname = :lastname"),
    @NamedQuery(name = "Person.findByStreet", query = "SELECT p FROM Person p WHERE p.street = :street"),
    @NamedQuery(name = "Person.findByPhone", query = "SELECT p FROM Person p WHERE p.phone = :phone"),
    @NamedQuery(name = "Person.findByPassword", query = "SELECT p FROM Person p WHERE p.password = :password")})
public class Person implements Serializable
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
    @Size(min = 1, max = 11)
    @Column(name = "CPR")
    private String cpr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "STREET")
    private String street;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONE")
    private int phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "ownerCpr")
    private Collection<Account> accountCollection;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Role roleId;
    @JoinColumn(name = "ZIPCODE", referencedColumnName = "CODE")
    @ManyToOne
    private Postal zipcode;
    @JoinColumn(name = "BANK_ID", referencedColumnName = "ID")
    @ManyToOne
    private Bank bankId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "person")
    private Employee employee;

    public Person()
    {
    }

    public Person(String email)
    {
	this.email = email;
    }

    public Person(String email, String cpr, String title, String firstname, String lastname, String street, int phone, String password)
    {
	this.email = email;
	this.cpr = cpr;
	this.title = title;
	this.firstname = firstname;
	this.lastname = lastname;
	this.street = street;
	this.phone = phone;
	this.password = password;
    }

    public String getEmail()
    {
	return email;
    }

    public void setEmail(String email)
    {
	this.email = email;
    }

    public String getCpr()
    {
	return cpr;
    }

    public void setCpr(String cpr)
    {
	this.cpr = cpr;
    }

    public String getTitle()
    {
	return title;
    }

    public void setTitle(String title)
    {
	this.title = title;
    }

    public String getFirstname()
    {
	return firstname;
    }

    public void setFirstname(String firstname)
    {
	this.firstname = firstname;
    }

    public String getLastname()
    {
	return lastname;
    }

    public void setLastname(String lastname)
    {
	this.lastname = lastname;
    }

    public String getStreet()
    {
	return street;
    }

    public void setStreet(String street)
    {
	this.street = street;
    }

    public int getPhone()
    {
	return phone;
    }

    public void setPhone(int phone)
    {
	this.phone = phone;
    }

    public String getPassword()
    {
	return password;
    }

    public void setPassword(String password)
    {
	this.password = password;
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

    public Role getRoleId()
    {
	return roleId;
    }

    public void setRoleId(Role roleId)
    {
	this.roleId = roleId;
    }

    public Postal getZipcode()
    {
	return zipcode;
    }

    public void setZipcode(Postal zipcode)
    {
	this.zipcode = zipcode;
    }

    public Bank getBankId()
    {
	return bankId;
    }

    public void setBankId(Bank bankId)
    {
	this.bankId = bankId;
    }

    public Employee getEmployee()
    {
	return employee;
    }

    public void setEmployee(Employee employee)
    {
	this.employee = employee;
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
	if (!(object instanceof Person)) {
	    return false;
	}
	Person other = (Person) object;
	if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString()
    {
	return "dk.cphbusiness.bank.entity.Person[ email=" + email + " ]";
    }
    
}
