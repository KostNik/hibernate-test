package com.edu.hibernate.model.associations.onetomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

// Can not be @MappedSuperclass when it's a target class in associations!

@Getter
@Setter
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    protected User user;

    @NotNull
    protected String owner;

    protected BillingDetails(String owner) {
        this.owner = owner;
    }

}
