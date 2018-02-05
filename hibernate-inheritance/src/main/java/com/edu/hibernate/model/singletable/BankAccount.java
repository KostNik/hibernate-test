package com.edu.hibernate.model.singletable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("BA")
public class BankAccount extends BillingDetails {

    @NotNull
    protected String account;

    @NotNull
    protected String bankName;

    @NotNull
    protected String swift;

    public BankAccount(String owner, String account, String bankName, String swift) {
        super(owner);
        this.account = account;
        this.bankName = bankName;
        this.swift = swift;
    }

}