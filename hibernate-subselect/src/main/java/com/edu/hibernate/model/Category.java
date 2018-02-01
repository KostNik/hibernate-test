package com.edu.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "CATEGORY_NAME")
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Item> items = new HashSet<>();

    public void addItem(Item item) {
        items.add(item);
        item.setCategory(this);
    }
}
