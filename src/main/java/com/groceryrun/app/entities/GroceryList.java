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
    @JoinColumn(name = "list_owner")
    private User groceryListOwner;

    @ManyToOne
    @JoinColumn(name = "list_store")
    private Store groceryListStore;

    @OneToOne
    @JoinColumn(name = "list_route")
    private Route listRoute;

    public GroceryList() {}

    public GroceryList(String name, User groceryListOwner) {
        this.name = name;
        this.groceryListOwner = groceryListOwner;
        this.listRoute = new Route(this);
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
}
