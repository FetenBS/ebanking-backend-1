package org.sid.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue(value = "SA")
@Data
@NoArgsConstructor@AllArgsConstructor
public class SavingAccount extends BankAccount {
private double interestRate;
private double amount;

}
