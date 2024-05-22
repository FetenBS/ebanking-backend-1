package org.sid.web;

import java.util.List;

import org.sid.dto.CustomerDTO;
import org.sid.entities.Customer;
import org.sid.exceptions.CustomerNotFoundException;
import org.sid.services.BankAccountService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//	@RequestMapping("/customers")
@RestController
@AllArgsConstructor
@Slf4j

public class CustomerRestController {
private BankAccountService bankAccountService;
@GetMapping("/customers")
public List<CustomerDTO> customers(){
	return bankAccountService.listCustomers();
	
}
@GetMapping("/customers/{id}")
public CustomerDTO getCustomer(@PathVariable(name="id")Long customerId) throws CustomerNotFoundException {
	return bankAccountService.getCustomer(customerId);
	
}
@PostMapping("/customers")
public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
return	bankAccountService.saveCustomer(customerDTO);
	
}
@PutMapping("/customers/{customerId}")
public CustomerDTO upCustomerDTO(@PathVariable Long customerId,@RequestBody  CustomerDTO customerDTO) {
	 customerDTO.setId(customerId);
	return bankAccountService.updateCustomer(customerDTO);
	
}
@DeleteMapping("/customers/{id}")
public void deleteCustomer(@PathVariable Long id) {
	bankAccountService.deleteCustomer(id);
}
}
