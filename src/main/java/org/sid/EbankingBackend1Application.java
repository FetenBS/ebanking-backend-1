package org.sid;

import java.rmi.server.UID;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

import org.sid.entities.AccountOperation;
import org.sid.entities.BankAccount;
import org.sid.entities.CurrentAccount;
import org.sid.entities.Customer;
import org.sid.entities.SavingAccount;
import org.sid.enums.AccountStatus;
import org.sid.enums.OperationType;
import org.sid.repositories.AccountOperationRepository;
import org.sid.repositories.BankAccountRepository;
import org.sid.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class EbankingBackend1Application {
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	public static void main(String[] args) {
		SpringApplication.run(EbankingBackend1Application.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository) {
		return args ->{
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

			
			
					
	}	;}		
						
						
		
		
	
//@Bean
CommandLineRunner start(CustomerRepository customerRepository,BankAccountRepository bankAccountRepository,AccountOperationRepository accountOperationRepository ) {
	repositoryRestConfiguration.exposeIdsFor(BankAccount.class,Customer.class);
	return args ->{
		Stream.of("Hassan","Yassine","Aicha").forEach(name ->{
			Customer customer=new Customer();
			customer.setName(name);
			
			customer.setEmail(name+"@gmail.com");
			customerRepository.save(customer);
		});
		customerRepository.findAll().forEach(cust->{
			CurrentAccount currentAccount=new CurrentAccount();
	
			currentAccount.setBalance(Math.random()*9000);
			currentAccount.setCreatedAt(new Date());
			currentAccount.setStatus(AccountStatus.CREATED);
			currentAccount.setCustomer(cust);
			currentAccount.setOverDraft(9000);
			bankAccountRepository.save(currentAccount);
		});
		
		
		
		
		customerRepository.findAll().forEach(cust->{
			SavingAccount savingAccount=new SavingAccount();
			savingAccount.setBalance(Math.random()*9000);
			savingAccount.setCreatedAt(new Date());
			savingAccount.setStatus(AccountStatus.CREATED);
			savingAccount.setCustomer(cust);
			//savingAccount.setAmount(8000);
			savingAccount.setInterestRate(5.5);
			bankAccountRepository.save(savingAccount);
		});
		bankAccountRepository.findAll().forEach(acc->{
			for(int i=0;i<10;i++) {
				AccountOperation accountOperation=new AccountOperation();
				accountOperation.setOperationDate(new Date());
				accountOperation.setAmount(Math.random()*120000);
                accountOperation.setType(Math.random()>0.5? OperationType.DEBIT: OperationType.CREDIT);
                accountOperation.setBankAccount(acc);
                accountOperationRepository.save(accountOperation);
			}
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

		});
		
		
		};
	
}
}
