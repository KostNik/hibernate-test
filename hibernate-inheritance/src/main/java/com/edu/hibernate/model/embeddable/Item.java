package com.edu.hibernate.model.embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NotNull
    @Size(min = 2
            , max = 255
            , message = "Name is required, maximum 255 characters.")
    protected String name;

    protected Dimensions dimensions;

    protected Weight weight;

    public Item(String name, Dimensions dimensions, Weight weight) {
        this.name = name;
        this.dimensions = dimensions;
        this.weight = weight;
    }

}
