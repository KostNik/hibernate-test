package com.edu.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

import static com.edu.hibernate.Constants.ID_GENERATOR;

/**
 * Created by Kostiuk Nikita
 */
@Data
@Entity
@NamedQuery(name = "Item.getAll", query = "Select i from Item as i")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_GENERATOR)
    private Long id;

    @NotNull
    @Version
    private long version;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, minimum 2, maximum 255 characters."
    )
    private String name;

    @NotNull
    @Size(
            min = 10,
            max = 4000,
            message = "Description is required, minimum 10, maximum 4000 characters."
    )
    private String description;

    @NotNull(message = "Auction end must be a future date and time.")
    @Future(message = "Auction end must be a future date and time.")
    private Date auctionEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
        category.getItems().add(this);
    }

}
