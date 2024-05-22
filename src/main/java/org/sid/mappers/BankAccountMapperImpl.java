package org.sid.mappers;

import org.sid.dto.CurrentBankAccountDTO;
import org.sid.dto.CustomerDTO;
import org.sid.dto.SavingBankAccountDTO;
import org.sid.entities.CurrentAccount;
import org.sid.entities.Customer;
import org.sid.entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import net.minidev.json.writer.BeansMapper.Bean;
@Service
public class BankAccountMapperImpl {
public CustomerDTO fromCustomer(Customer customer) {
	CustomerDTO customerDTO=new CustomerDTO();
	BeanUtils.copyProperties(customer, customerDTO);
	return customerDTO;
}
public Customer fromCustomerDTO(CustomerDTO customerDTO) {
	Customer customer=new Customer();
BeanUtils.copyProperties(customerDTO, customer);
return customer;
	
}
public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount) {
	SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
	BeanUtils.copyProperties(savingAccount, savingBankAccountDTO);
	savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
	return savingBankAccountDTO;
}
public SavingAccount fromSavingAccountDTO(SavingBankAccountDTO savingBankAccountDTO) {
	SavingAccount savingAccount=new SavingAccount();
	BeanUtils.copyProperties(savingBankAccountDTO, savingAccount);
	savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
	return savingAccount;
	
}
public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount) {
	CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
	BeanUtils.copyProperties(currentAccount, currentBankAccountDTO);
	currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
	return currentBankAccountDTO;
	
}
public CurrentAccount fromCurrentAccountDTO(CurrentBankAccountDTO currentBankAccountDTO) {
	CurrentAccount currentAccount=new CurrentAccount();
	BeanUtils.copyProperties(currentBankAccountDTO, currentAccount);
	currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
	return currentAccount;
	
}
}
