package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "list_owner")
    private User groceryListOwner;

    @ManyToOne
    @JoinColumn(name = "list_store")
    private Store groceryListStore;

    @OneToOne
    @JoinColumn(name = "list_route")
    private Route listRoute;

    @ManyToMany
    @JoinTable(name = "grocery_list_items",
        joinColumns = @JoinColumn(name = "grocery_list_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> groceryListItems;

    @Column(nullable = false)
    private List<Integer> groceryListItemsQuantities;

    @Column(nullable = false)
    private List<Boolean> groceryListItemsFoundStatus;

    public GroceryList() {}

    public GroceryList(String name, User groceryListOwner) {
        this.name = name;
        this.groceryListOwner = groceryListOwner;
        this.listRoute = new Route(this);
        this.groceryListItems = new ArrayList<>();
        this.groceryListItemsQuantities = new ArrayList<>();
        this.groceryListItemsFoundStatus = new ArrayList<>();
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

    public Route getListRoute() {
        return listRoute;
    }

    public List<Item> getGroceryListItems() {
        return groceryListItems;
    }

    public List<Integer> getGroceryListItemsQuantities() {
        return groceryListItemsQuantities;
    }

    public List<Boolean> getGroceryListItemsFoundStatus() {
        return groceryListItemsFoundStatus;
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

    public void setListRoute(Route listRoute) {
        this.listRoute = listRoute;
    }

    public void setGroceryListItems(List<Item> groceryListItems) {
        this.groceryListItems = groceryListItems;
    }

    public void setGroceryListItemsQuantities(List<Integer> groceryListItemsQuantities) {
        this.groceryListItemsQuantities = groceryListItemsQuantities;
    }

    public void setGroceryListItemsFoundStatus(List<Boolean> groceryListItemsFoundStatus) {
        this.groceryListItemsFoundStatus = groceryListItemsFoundStatus;
    }
}
