package com.groceryrun.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "listRoute")
    private GroceryList routeGroceryList;

    public Route() {

    }

    public Route(GroceryList routeGroceryList) {
        this.routeGroceryList = routeGroceryList;
    }

    public Integer getId() {
        return id;
    }

    public GroceryList getRouteGroceryList() {
        return routeGroceryList;
    }

    public void setRouteGroceryList(GroceryList routeGroceryList) {
        this.routeGroceryList = routeGroceryList;
    }
}
