package com.edu.hibernate.model.associations.manytoone;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotNull
    protected String username;

    @ManyToOne(fetch = FetchType.LAZY)
    protected BillingDetails defaultBilling;

    public User(String username) {
        this.username = username;
    }

}
