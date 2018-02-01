package com.edu.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static com.edu.hibernate.Constants.ID_GENERATOR;

/**
 * @author Kostiuk Nikita
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString(exclude = "items")
@EqualsAndHashCode(exclude = "items")
@NamedQuery(name = "Category.getAll", query = "Select c from Category as c")
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_GENERATOR)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<>();

}
