package com.edu.hibernate.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Kostiuk Nikita
 */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Producer.getAll", query = "Select i from Producer as i"),
        @NamedQuery(name = "Producer.findByName", query = "SELECT a FROM Producer a WHERE name like :name")
})
public class Producer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    private String name;

    @Embedded
    private ContactInfo home;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address.city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "address.country", column = @Column(name = "WORK_COUNTRY")),
            @AttributeOverride(name = "telephone.number", column = @Column(name = "WORK_TEL_NUMBER"))
    })
    private ContactInfo work;


}
