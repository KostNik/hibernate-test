package com.edu.hibernate.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Kostiuk Nikita
 */
@Data
@Entity
@NamedQuery(name = "Item.getAll", query = "Select i from Item as i")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Version
    private long version;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull(message = "Auction end must be a future date and time.")
    @Future(message = "Auction end must be a future date and time.")
    private Date auctionEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false)
    @org.hibernate.annotations.Generated(
            org.hibernate.annotations.GenerationTime.ALWAYS)
    protected Date lastModified;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;


}
