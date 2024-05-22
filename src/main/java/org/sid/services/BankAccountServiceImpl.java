package org.sid.services;

import java.util.Date;


import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;

import org.sid.dto.BankAccountDTO;
import org.sid.dto.CurrentBankAccountDTO;
import org.sid.dto.CustomerDTO;
import org.sid.dto.SavingBankAccountDTO;
import org.sid.entities.AccountOperation;
import org.sid.entities.BankAccount;
import org.sid.entities.CurrentAccount;
import org.sid.entities.Customer;
import org.sid.entities.SavingAccount;
import org.sid.enums.OperationType;
import org.sid.exceptions.BalanceNotSufficientException;

import org.sid.exceptions.BankAcountNotFoundException;
import org.sid.exceptions.CustomerNotFoundException;
import org.sid.mappers.BankAccountMapperImpl;
import org.sid.repositories.AccountOperationRepository;
import org.sid.repositories.BankAccountRepository;
import org.sid.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;

import com.sun.tools.javac.util.Log;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@AllArgsConstructor
@Slf4j
public  class BankAccountServiceImpl implements BankAccountService{

private BankAccountMapperImpl dtoMapper;
private CustomerRepository customerRepository;
private BankAccountRepository bankAccountRepository;
private AccountOperationRepository accountOperationRepository;

//org.slf4j.Logger log =LoggerFactory.getLogger(this.getClass().getName());
@Override
	public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
	//org.slf4j.Logger log =LoggerFactory.getLogger(this.getClass().getName());
	
	log.info("saving new Customer");
	Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
	Customer savedCustomer=customerRepository.save(customer);
		return dtoMapper.fromCustomer(savedCustomer);
	}

	@Override
	public BankAccount saveAccount(double initialBalance, String type, Long CustomerId) throws CustomerNotFoundException  {
		Customer customer=customerRepository.findById(CustomerId).orElse(null);
 
if(customer==null)
	throw new CustomerNotFoundException("Customer Not Found");
		CurrentAccount currentAccount=new CurrentAccount();
		
		currentAccount.setId((long) Math.random());
		currentAccount.setCreatedAt(new Date());
		currentAccount.setBalance(initialBalance);
		currentAccount.setCustomer(customer);
		currentAccount.setOverDraft(initialBalance);
		
		CurrentAccount saveBankAccount= bankAccountRepository.save(currentAccount);
		return saveBankAccount;
	}

	@Override
	public List<CustomerDTO> listCustomers() {
		List<Customer> customers=customerRepository.findAll();
	List<CustomerDTO> customerDTOs=	customers.stream().map(customer->dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
		return customerDTOs;
		
		
	}

	

	@Override
	public void debit(Long accountId, double amount, String description) throws BankAcountNotFoundException, BalanceNotSufficientException {
		
		BankAccount bankAccount= bankAccountRepository.findById(accountId).orElseThrow(()->new BankAcountNotFoundException("Bank account not found"));
		if(bankAccount.getBalance()<amount) throw new BalanceNotSufficientException("Balance Not sufficient");
			AccountOperation accountOperation=new AccountOperation();
			accountOperation.setType(OperationType.DEBIT);
			accountOperation.setAmount(amount);
			accountOperation.setDescription(description);
			accountOperation.setOperationDate(new Date());
			accountOperation.setBankAccount(bankAccount);
			accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()-amount);
		bankAccountRepository.save(bankAccount);
	}

	@Override
	public void credit(Long accountId, double amount, String description) throws BalanceNotSufficientException, BankAcountNotFoundException {
		BankAccount bankAccount= bankAccountRepository.findById(accountId).orElseThrow(()->new BankAcountNotFoundException("Bank account not found"));
	
			AccountOperation accountOperation=new AccountOperation();
			accountOperation.setType(OperationType.CREDIT);
			accountOperation.setAmount(amount);
			accountOperation.setDescription(description);
			accountOperation.setOperationDate(new Date());
			accountOperation.setBankAccount(bankAccount);
			accountOperationRepository.save(accountOperation);
		bankAccount.setBalance(bankAccount.getBalance()+amount);
		bankAccountRepository.save(bankAccount);
	}
	

	@Override
	public void transfert(Long accountIdSource, Long accountIdDestination, double amount) throws BankAcountNotFoundException, BalanceNotSufficientException {
	debit(accountIdSource,amount,"transfer",accountIdDestination);
	credit(accountIdDestination,amount,"transfer",accountIdSource);
	
	}

	private void credit(Long accountIdDestination, double amount, String string, Long accountIdSource) {
		
		
	}

	private void debit(Long accountIdSource, double amount, String string, Long accountIdDestination) {
	
		
	}

	@Override
	public BankAccountDTO getBankAccount(Long accountId) throws BankAcountNotFoundException {
		
	BankAccount bankAccount	= bankAccountRepository.findById(accountId).orElseThrow(()->new BankAcountNotFoundException("g"));
	if(bankAccount instanceof SavingAccount) {
		SavingAccount savingAccount=(SavingAccount) bankAccount;
	
	return dtoMapper.fromSavingBankAccount(savingAccount);
	}else {
		CurrentAccount currentAccount=(CurrentAccount)bankAccount;
		return dtoMapper.fromCurrentBankAccount((CurrentAccount)currentAccount);
	}
	
	}
	@Override
	public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long CustomerId)
			throws CustomerNotFoundException {
		Customer customer=customerRepository.findById(CustomerId).orElse(null);
		 
		if(customer==null)
			throw new CustomerNotFoundException("Customer Not Found");
				CurrentAccount currentAccount = new CurrentAccount();
				
				currentAccount.setId((long) Math.random());
				currentAccount.setCreatedAt(new Date());
				currentAccount.setBalance(initialBalance);
				currentAccount.setOverDraft(overDraft);
			    currentAccount.setCustomer(customer);
				
				CurrentAccount savedBankAccount=bankAccountRepository.save(currentAccount);
				return dtoMapper.fromCurrentBankAccount(savedBankAccount);
	}

	public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long CustomerId)
			throws CustomerNotFoundException {
		
	
			Customer customer=customerRepository.findById(CustomerId).orElse(null);
			 
			if(customer==null)
				throw new CustomerNotFoundException("Customer Not Found");
					SavingAccount savingAccount = new SavingAccount();
					
					savingAccount.setId((long) Math.random());
					savingAccount.setCreatedAt(new Date());
					savingAccount.setBalance(initialBalance);
					savingAccount.setInterestRate(interestRate);
				    savingAccount.setCustomer(customer);
					
				SavingAccount savedBankAccount=bankAccountRepository.save(savingAccount);
					
				

		return dtoMapper.fromSavingBankAccount(savedBankAccount);


		}
	@Override
public List<BankAccountDTO> bankAccountList(){
		
	List<BankAccount> bankAccounts=bankAccountRepository.findAll();
	List<BankAccountDTO> bankAccountDTOs=bankAccounts.stream().map(bankAccount ->{
		if(bankAccount instanceof SavingAccount) {
			SavingAccount savingAccount=(SavingAccount) bankAccount;
		return	dtoMapper.fromSavingBankAccount(savingAccount);
		}else {
			CurrentAccount currentAccount=(CurrentAccount) bankAccount;
			return dtoMapper.fromCurrentBankAccount(currentAccount);
		
		 }
	}).collect(Collectors.toList());
	
	return bankAccountDTOs;	
	}
	public CustomerDTO getCustomer(Long customerId) throws CustomerNotFoundException {
		Customer customer=customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
		return dtoMapper.fromCustomer(customer);
		
	}
	@Override
	public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
		//org.slf4j.Logger log =LoggerFactory.getLogger(this.getClass().getName());
		
		log.info("saving new Customer");
		Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
		Customer savedCustomer=customerRepository.save(customer);
			return dtoMapper.fromCustomer(savedCustomer);
		}
	@Override
	public void deleteCustomer(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	

}
