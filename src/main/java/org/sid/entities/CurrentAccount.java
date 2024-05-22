package org.sid.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@DiscriminatorValue(value = "CA")
@Data @AllArgsConstructor @NoArgsConstructor
public class CurrentAccount extends BankAccount {
 double overDraft;
}
