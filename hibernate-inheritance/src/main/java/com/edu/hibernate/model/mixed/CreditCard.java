package com.edu.hibernate.model.mixed;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("CC")
@SecondaryTable(
        name = "CREDITCARD",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "CREDITCARD_ID")
)
public class CreditCard extends BillingDetails {

    @NotNull // Ignored by JPA for DDL, strategy is SINGLE_TABLE!
    @Column(table = "CREDITCARD", nullable = false) // Override the primary table
    protected String cardNumber;

    @Column(table = "CREDITCARD", nullable = false)
    protected String expMonth;

    @Column(table = "CREDITCARD", nullable = false)
    protected String expYear;

    public CreditCard(String owner, String cardNumber, String expMonth, String expYear) {
        super(owner);
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
    }

}
