package org.sid.services;

import javax.transaction.Transactional;

import org.sid.entities.BankAccount;
import org.sid.entities.CurrentAccount;
import org.sid.entities.SavingAccount;
import org.sid.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
	@Autowired
private BankAccountRepository bankAccountRepository;

public void consulter() {
	BankAccount bankAccount=bankAccountRepository.findById((long) 2).orElse(null) ;
	if(bankAccount !=null) {
		System.out.println("*****************");
		System.out.println(bankAccount.getId());
		System.out.println(bankAccount.getBalance());
		System.out.println(bankAccount.getStatus());
		System.out.println(bankAccount.getCreatedAt());
		System.out.println(bankAccount.getCustomer().getName());
		System.out.println(bankAccount.getClass().getSimpleName());
		 
		System.out.println("*****************");
		if (bankAccount instanceof CurrentAccount) {
		System.out.println("getOverDraft: " +	((CurrentAccount)bankAccount).getOverDraft());

		} else if (bankAccount instanceof SavingAccount){
			System.out.println("SavingAccount: "+((SavingAccount)bankAccount).getInterestRate());
		}
			bankAccount.getAccountOperations().forEach(op->{
				System.out.println("+++++++++++++++");
				System.out.println(op.getType()
				+"/t" +op.getOperationDate()+"/t"+
				op.getAmount());
			
			});

		}
}
}
