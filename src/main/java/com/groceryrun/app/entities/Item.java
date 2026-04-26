package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity for an item
 */
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the item

    @Column(nullable = false)
    private String name; // Name of the item

    @ManyToMany(mappedBy = "items")
    private List<GroceryList> groceryLists; // Grocery lists that the item belongs to

    @ManyToOne
    @JoinColumn(name = "category_item")
    private Category category; // Category that the item belongs to

    /**
     * Constructor for an item
     */
    public Item() {

    }

    /**
     * Constructor for an item
     * @param name name of the item
     * @param category category that the item belongs to
     */
    public Item(String name, Category category) {
        this.name = name;
        this.groceryLists = new ArrayList<>();
        this.category = category;
    }

    /**
     * Gets the id of the item
     * @return id of the item
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name of the item
     * @return name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the grocery lists that the item belongs to
     * @return grocery lists that the item belongs to
     */
    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    /**
     * Gets the category that the item belongs to
     * @return category that the item belongs to
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the name of the item
     * @param name name of the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the grocery lists that the item belongs to
     * @param groceryLists grocery lists that the item belongs to
     */
    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    /**
     * Sets the category that the item belongs to
     * @param category category that the item belongs to
     */
    public void setCategory(Category category) {
        this.category = category;
    }
}
