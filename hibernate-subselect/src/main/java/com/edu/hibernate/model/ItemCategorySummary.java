package com.edu.hibernate.model;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * Created by Kostiuk Nikita
 */

@Entity
@Immutable
@Subselect(
        value = "select c.ID as CATEGORYID, c.CATEGORY_NAME as NAME, " +
                "count(i.ID) as NUMBEROFITEMS " +
                "from CATEGORY c left outer join ITEM i on c.ID = i.CATEGORY_ID " +
                "group by c.ID, c.CATEGORY_NAME"
)
@Data
@org.hibernate.annotations.Synchronize({"Item", "Category"})
@NamedQuery(name = "ItemCategorySummary.byID", query = "select ibs from ItemCategorySummary ibs where ibs.categoryId = :id")
public class ItemCategorySummary {

    @Id
    private String categoryId;

    private long numberOfItems;

    private String name;

}
