package org.sid.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;
import org.sid.enums.AccountStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",length = 4,discriminatorType = DiscriminatorType.STRING)
public class BankAccount {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private double balance;
private Date createdAt;
@Enumerated(EnumType.STRING)
private AccountStatus status;
@ManyToOne
private Customer customer;
@OneToMany(mappedBy ="bankAccount",fetch = FetchType.EAGER)
private List<AccountOperation> accountOperations;
}
