package dk.cphbusiness.bank.control;

import dk.cphbusiness.bank.contract.dto.AccountDetail;
import dk.cphbusiness.bank.contract.dto.AccountSummary;
import dk.cphbusiness.bank.contract.dto.CheckingAccountDetail;
import dk.cphbusiness.bank.contract.dto.CustomerDetail;
import dk.cphbusiness.bank.contract.dto.CustomerSummary;
import dk.cphbusiness.bank.contract.dto.TransferSummary;
import dk.cphbusiness.bank.entity.Account;
import dk.cphbusiness.bank.entity.Checking;
import dk.cphbusiness.bank.entity.Person;
import dk.cphbusiness.bank.entity.Transfer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Assembler {
    
  public static CustomerSummary createCustomerSummary(Person customer) {
    return new CustomerSummary(
      customer.getCpr(),
      customer.getFirstname()+" "+customer.getLastname(),
      customer.getStreet()+", "+customer.getZipcode()+" "+customer.getZipcode().getDistrict(),
      ""+customer.getPhone(),
      customer.getEmail()
      );
    }
  
  public static Collection<CustomerSummary> createCustomerSummaries(Collection<Person> customers) {
    Collection<CustomerSummary> summaries = new ArrayList<>();
    if (customers == null) return summaries;
    for (Person customer : customers) {
	if(customer.getRoleId().getName().equals("Customer")) summaries.add(createCustomerSummary(customer));
      }
    return summaries;
    }
  
  public static AccountSummary createAccountSummary(Account account) {
    return new AccountSummary(
        account.getAccountNr(),
        "Checking Account",
        new BigDecimal(100000)
        );
    }
  
  public static Collection<AccountSummary> createAccountSummaries(Collection<Account> accounts) {
    Collection<AccountSummary> summaries = new ArrayList<>();
    if (accounts == null) return summaries;
    for (Account account : accounts) summaries.add(createAccountSummary(account));
    return summaries;
    }

  public static TransferSummary createTransferSummary(Account account, Transfer transfer) {
    if (transfer.getSourceAccount() == account) 
        return new TransferSummary(
            transfer.getTransactionDate(),
            new BigDecimal(-transfer.getAmount()),
            transfer.getTargetAccount().getAccountNr()
            );
    else 
        return new TransferSummary(
            transfer.getTransactionDate(),
            new BigDecimal(transfer.getAmount()),
            transfer.getSourceAccount().getAccountNr()
            );
    }

  public static AccountDetail createAccountDetail(Account account) {
    List<Transfer> transfers = new ArrayList<>();
    if(account.getTransfers() != null){
    transfers.addAll(account.getTransfers());
    Collections.sort(transfers);
    }
    System.err.println("Transfers for #"+account.getAccountNr()+" "+transfers.size());
    Collection<TransferSummary> transferSummaries = new ArrayList<>();
    for (Transfer transfer : transfers) transferSummaries.add(createTransferSummary(account, transfer));
    return new CheckingAccountDetail(account.getAccountNr(), new BigDecimal(account.getInterest()), transferSummaries);
    }
  
  public static CustomerDetail createCustomerDetail(Person customer) {
    return new CustomerDetail(
        customer.getCpr(),
        customer.getTitle(),
        customer.getFirstname(),
        customer.getLastname(),
        customer.getStreet(),
        ""+customer.getZipcode().getCode(),
        customer.getZipcode().getDistrict(),
        ""+customer.getPhone(),
        customer.getEmail()
        );
    }
  
  public static Person updateCustomerEntity(CustomerDetail detail, Person p) {
          p.setTitle(detail.getTitle());
          p.setFirstname(detail.getFirstName());
          p.setLastname(detail.getLastName());
          p.setStreet(detail.getStreet());
          p.getZipcode().setCode(Integer.parseInt(detail.getPostalCode()));
          p.getZipcode().setDistrict(detail.getPostalDistrict());
          p.setPhone(Integer.parseInt(detail.getPhone()));
          p.setEmail(detail.getEmail());
      return p;
      }
    
public static Person createCustomerEntity(CustomerDetail detail) {
      return new Person(detail.getEmail(), 
	      detail.getCpr(), 
	      detail.getTitle(), 
	      detail.getFirstName(), 
	      detail.getLastName(), 
	      detail.getStreet(), 
	      Integer.parseInt(detail.getPhone()),null);
      }
  
//  public static Checking createCheckingAccountEntity(Person customer, CheckingAccountDetail detail) {
//    return new Checking(customer, detail.getInterest());
//    }
  
  }
