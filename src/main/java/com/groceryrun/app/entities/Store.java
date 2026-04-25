package com.groceryrun.app.entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity for a store
 */
@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the store

    @Column(nullable = false)
    private String name; // Name of the store

    @ManyToMany(mappedBy = "stores")
    private List<User> owners; // Owners of the store

    @OneToOne
    @JoinColumn(name = "store_location")
    private Location location; // Location of the store

    @OneToMany(mappedBy = "store")
    private List<Aisle> aisles; // Aisles of the store

    /**
     * Constructor for a store
     */
    public Store() {}

    /**
     * Constructor for a store
     * @param name name of the store
     * @param location location of the store
     * @param owners owners of the store
     */
    public Store(String name, Location location, List<User> owners) {
        this.name = name;
        this.location = location;
        this.owners = owners;
        this.aisles = new ArrayList<>();
    }

    /**
     * Gets the id of the store
     * @return id of the store
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the name of the store
     * @return name of the store
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the location of the store
     * @return location of the store
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the owners of the store
     * @return owners of the store
     */
    public List<User> getOwners() {
        return owners;
    }

    /**
     * Gets the aisles of the store
     * @return aisles of the store
     */
    public List<Aisle> getAisles() {
        return aisles;
    }

    /**
     * Sets the name of the store
     * @param name name of the store
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the location of the store
     * @param location location of the store
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the owners of the store
     * @param owners owners of the store
     */
    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    /**
     * Sets the aisles of the store
     * @param aisles aisles of the store
     */
    public void setAisles(List<Aisle> aisles) {
        this.aisles = aisles;
    }
}
