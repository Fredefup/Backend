/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.BankManager;
import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountIdentifier;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerIdentifier;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.eto.CustomerBannedException;
import dk.cphbusiness.bank.contract.eto.InsufficientFundsException;
import dk.cphbusiness.bank.contract.eto.NoSuchAccountException;
import dk.cphbusiness.bank.contract.eto.NoSuchCustomerException;
import dk.cphbusiness.bank.contract.eto.TransferNotAcceptedException;
import static dk.cphbusiness.bank.control.Assembler.*;
import dk.cphbusiness.bank.entity.Account;
import dk.cphbusiness.bank.entity.Person;
import dk.cphbusiness.bank.entity.Transfer;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ahmed Sadiq
 */
@Stateless
public class BankManagerBean implements BankManager
{
    @PersistenceContext(unitName = "BankBackendPU")
    private EntityManager em;
    
    int i = 1;
    @Override
    public Collection<AccountSummary> listCustomerAccounts(CustomerIdentifier identifier)
    {
	Query query = em.createNamedQuery("Person.findByCpr");
	query.setParameter("cpr", identifier.getCpr());
//    List<Person> persons = query.getResultList();
	Person customer = (Person) query.getSingleResult();
	if (customer == null) {
	    return createAccountSummaries(null);
	}
	return createAccountSummaries(customer.getAccountCollection());
    }

  @Override
  public AccountDetail transferAmount(BigDecimal amount, AccountIdentifier source, AccountIdentifier target) throws NoSuchAccountException, TransferNotAcceptedException, InsufficientFundsException {
    Account sourceAccount = em.find(Account.class, source.getNumber());
    Account targetAccount = em.find(Account.class, target.getNumber());
    Transfer t =new Transfer(null, amount.doubleValue(), new Date(), sourceAccount, targetAccount);
    em.persist(t);
    return createAccountDetail(sourceAccount);
    }

    @Override
    public AccountDetail showAccountHistory(AccountIdentifier account)
    {
	return createAccountDetail(em.find(Account.class, account.getNumber()));
    }

    @Override
    public Collection<CustomerSummary> listCustomers()
    {
	Query query = em.createNamedQuery("Person.findAll");
	Collection<Person> persons = query.getResultList();
	return createCustomerSummaries(persons);
    }

    @Override
    public Collection<AccountSummary> listAccounts()
    {
	Query query = em.createNamedQuery("Account.findAll");
	Collection<Account> accounts = query.getResultList();
	return createAccountSummaries(accounts);
    }

    @Override
    public CustomerDetail saveCustomer(CustomerDetail customer)
    {
	Person p = em.find(Person.class, customer.getEmail());
	if (p == null) {
	    p = createCustomerEntity(customer);
	} else {
	    p = updateCustomerEntity(customer, p);
	}
	return createCustomerDetail(p);
    }

    @Override
    public AccountDetail createAccount(CustomerIdentifier customerId, AccountDetail detail) throws NoSuchCustomerException, CustomerBannedException
    {
	Query query = em.createNamedQuery("Person.findByCpr");
	query.setParameter("cpr", customerId.getCpr());
//    List<Person> persons = query.getResultList();
	Person owner = (Person) query.getSingleResult();
	if (owner == null) {
	    throw new NoSuchCustomerException(customerId);
	}
//	if (detail instanceof CheckingAccountDetail) {
//	    Checking account = createCheckingAccountEntity(owner, (CheckingAccountDetail) detail);
//	    return createAccountDetail(account);
//	}
	throw new RuntimeException("Unknown Account type");
    }

    @Override
    public CustomerDetail showCustomer(CustomerIdentifier customer) throws NoSuchCustomerException
    {
	Query query = em.createNamedQuery("Person.findByCpr");
	query.setParameter("cpr", customer.getCpr());
//    List<Person> persons = query.getResultList();
	Person person = (Person) query.getSingleResult();
	if (person == null) {
	    throw new NoSuchCustomerException(customer);
	}
	return createCustomerDetail(person);
    }

    @Override
    public Collection<String> listAccountTypes()
    {
	Query query = em.createQuery("Accounttype.findAll");
	Collection<String> types = query.getResultList();
	return types;
    }

    @Override
    public String sayHello(String name)
    {
	return "hello " + name + " from me";
    }

    public void persist(Object object)
    {
	em.persist(object);
    }
   
}