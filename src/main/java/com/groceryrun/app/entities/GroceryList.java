package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity for a grocery list
 */
@Entity
@Table(name = "grocery_lists")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the grocery list

    @Column(nullable = false)
    private String name; // Name of the grocery list

    @ManyToOne
    @JoinColumn(name = "user_grocery_lists")
    private User owner; // Owner of the grocery list

    @ManyToMany
    @JoinTable(name = "grocery_list_items",
        joinColumns = @JoinColumn(name = "grocery_list_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> items; // Items that the grocery list contains

    /**
     * Constructor for a grocery list
     */
    public GroceryList() {}

    /**
     * Constructor for a grocery list
     * @param name name of the grocery list
     * @param owner owner of the grocery list
     */

    public GroceryList(String name, User owner) {
        this.name = name;
        this.owner = owner;
        this.items = new ArrayList<>();
    }

    /**
     * Gets the id of the grocery list
     * @return id of the grocery list
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name of the grocery list
     * @return name of the grocery list
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the owner of the grocery list
     * @return owner of the grocery list
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Gets the items that the grocery list contains
     * @return items that the grocery list contains
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets the name of the grocery list
     * @param name name of the grocery list
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the owner of the grocery list
     * @param owner owner of the grocery list
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }

    /**
     * Sets the items that the grocery list contains
     * @param items items that the grocery list contains
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
