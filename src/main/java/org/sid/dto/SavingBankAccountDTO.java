package org.sid.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.sid.entities.AccountOperation;
import org.sid.entities.Customer;
import org.sid.enums.AccountStatus;

import lombok.Data;

@Data
public  class SavingBankAccountDTO extends BankAccountDTO {
	private Long id;
	private double balance;
	private Date createdAt;
	private AccountStatus status;
	private CustomerDTO customerDTO;
	private double interestRate;

	
	}

