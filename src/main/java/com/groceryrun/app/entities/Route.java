package com.groceryrun.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Route() {

    }

    public Integer getId() {
        return id;
    }
}
