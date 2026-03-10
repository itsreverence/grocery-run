package com.groceryrun.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grocery_lists")
public class GroceryList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User groceryListOwner;

    @ManyToOne
    @JoinColumn(name = "list_store")
    private Store groceryListStore;

    public GroceryList() {}

    public GroceryList(String name, User groceryListOwner) {
        this.name = name;
        this.groceryListOwner = groceryListOwner;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getGroceryListOwner() {
        return groceryListOwner;
    }

    public Store getGroceryListStore() {
        return groceryListStore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroceryListOwner(User groceryListOwner) {
        this.groceryListOwner = groceryListOwner;
    }

    public void setGroceryListStore(Store groceryListStore) {
        this.groceryListStore = groceryListStore;
    }
}
