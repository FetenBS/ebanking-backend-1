package org.sid.services;

import java.util.List;

import org.sid.dto.BankAccountDTO;
import org.sid.dto.CurrentBankAccountDTO;
import org.sid.dto.CustomerDTO;
import org.sid.dto.SavingBankAccountDTO;
import org.sid.entities.BankAccount;
import org.sid.entities.CurrentAccount;
import org.sid.entities.Customer;
import org.sid.entities.SavingAccount;
import org.sid.exceptions.BalanceNotSufficientException;
import org.sid.exceptions.BankAcountNotFoundException;
import org.sid.exceptions.CustomerNotFoundException;

public interface BankAccountService {
// CustomerDTO saveCustomer(CustomerDTO customerDTO);
BankAccount saveAccount(double initialBalance, String type, Long CustomerId) throws CustomerNotFoundException;
	List<CustomerDTO> listCustomers();
	BankAccountDTO getBankAccount(Long accountId) throws BankAcountNotFoundException;
	void debit(Long accountId, double amount, String description) throws BankAcountNotFoundException, BalanceNotSufficientException;
	void credit(Long accountId, double amount, String description) throws BalanceNotSufficientException, BankAcountNotFoundException;
	void transfert(Long accountIdSource,Long accountIdDestination,double amount) throws BankAcountNotFoundException, BalanceNotSufficientException;
	CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long CustomerId) throws CustomerNotFoundException;
	SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long CustomerId)throws CustomerNotFoundException;
	List<BankAccountDTO> bankAccountList();;
	public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException;
	CustomerDTO saveCustomer(CustomerDTO customer);
	public CustomerDTO updateCustomer(CustomerDTO customerDTO);
	void deleteCustomer(Long customerId);
}
