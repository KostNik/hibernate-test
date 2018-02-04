package com.edu.hibernate.model;

import lombok.Data;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kostiuk Nikita
 */
@Data
@Entity
// 1. override Hibernate’s default remove operation
@SQLDelete(sql = "UPDATE Item SET state = 'DELETED' WHERE ID = ? ", check = ResultCheckStyle.COUNT)
// 3. add state != DELETED to all selected Item queries. Because EntityManager.find*** methods don’t know about the semantic of the state attribute
@Where(clause = "state <> 'DELETED'")
@NamedQueries({
        @NamedQuery(name = "Item.getAll", query = "Select i from Item as i"),
        @NamedQuery(name = "Item.findByName", query = "SELECT a FROM Item a WHERE name like :name")
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    private String description;

    private Date auctionEnd;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ItemState state;

    // 2. set DELETE state in current session
    @PreRemove
    public void deleteUser() {
        this.state = ItemState.DELETED;
    }

}
