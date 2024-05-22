package org.sid.dto;

import java.util.List;

import javax.persistence.OneToMany;

import org.sid.entities.BankAccount;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
  @Data
public class CustomerDTO {
	private Long id;
	private String name;
	private String email;
	

}
