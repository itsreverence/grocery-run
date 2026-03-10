package com.groceryrun.app.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "groceryListStore")
    private List<GroceryList> groceryLists;

    @OneToOne
    @JoinColumn(name = "store_location")
    private Location storeLocation;

    public Store() {}

    public Store(String name, Location storeLocation) {
        this.name = name;
        this.groceryLists = new ArrayList<>();
        this.storeLocation = storeLocation;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getStoreLocation() {
        return storeLocation;
    }

    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    public void setStoreLocation(Location storeLocation) {
        this.storeLocation = storeLocation;
    }
}
