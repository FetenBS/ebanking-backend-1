package org.sid.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;
import org.sid.enums.OperationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class AccountOperation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Date OperationDate;
private double amount;
@Enumerated(EnumType.STRING)
private OperationType type;
@ManyToOne
private BankAccount bankAccount;
private String Description;
}
