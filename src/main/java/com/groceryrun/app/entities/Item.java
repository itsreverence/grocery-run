package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "groceryListItems")
    private List<GroceryList> groceryLists;

    @ManyToOne
    @JoinColumn(name = "item_category")
    private Category itemCategory;

    public Item() {

    }

    public Item(String name, Category itemCategory) {
        this.name = name;
        this.groceryLists = new ArrayList<>();
        this.itemCategory = itemCategory;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public Category getItemCategory() {
        return itemCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    public void setItemCategory(Category itemCategory) {
        this.itemCategory = itemCategory;
    }
}
