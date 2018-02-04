package com.edu.hibernate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by Kostiuk Nikita
 */
@Data
@Embeddable
@NoArgsConstructor
public class Address {


    private String city;
    private String country;

}
