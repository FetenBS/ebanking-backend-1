package org.sid.web;

import org.sid.services.BankAccountService;
import org.springframework.web.bind.annotation.RestController;
@RestController

public class BankAccountRestAPI {
public BankAccountRestAPI(BankAccountService bankAccountService) {
		super();
		this.bankAccountService = bankAccountService;
	}

private BankAccountService bankAccountService;
}
