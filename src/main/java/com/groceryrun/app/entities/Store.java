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

    @ManyToMany(mappedBy = "user_stores")
    private List<User> owners;

    @OneToOne
    @JoinColumn(name = "store_location")
    private Location location;

    @OneToMany(mappedBy = "store_aisle")
    private List<Aisle> aisles;

    public Store() {}

    public Store(String name, Location location, List<User> owners) {
        this.name = name;
        this.location = location;
        this.owners = owners;
        this.aisles = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public List<User> getOwners() {
        return owners;
    }

    public List<Aisle> getAisles() {
        return aisles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public void setAisles(List<Aisle> aisles) {
        this.aisles = aisles;
    }
}
