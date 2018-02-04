package com.edu.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by Kostiuk Nikita
 */
@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Telephone {

    private String number;
}
