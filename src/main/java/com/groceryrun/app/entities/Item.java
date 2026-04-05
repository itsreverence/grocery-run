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

    @ManyToMany(mappedBy = "items")
    private List<GroceryList> groceryLists;

    @ManyToOne
    @JoinColumn(name = "category_item")
    private Category category;

    public Item() {

    }

    public Item(String name, Category category) {
        this.name = name;
        this.groceryLists = new ArrayList<>();
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
