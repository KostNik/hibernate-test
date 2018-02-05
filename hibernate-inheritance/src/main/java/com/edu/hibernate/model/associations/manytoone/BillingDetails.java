package com.edu.hibernate.model.associations.manytoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// Can not be @MappedSuperclass when it's a target class in associations!

@Getter
@Setter
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetails {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotNull
    protected String owner;

    protected BillingDetails(String owner) {
        this.owner = owner;
    }

    // Use property instead of field access, so calling getId() doesn't initialize a proxy!

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

}