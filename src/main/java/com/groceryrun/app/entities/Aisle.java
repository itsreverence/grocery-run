package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity for an aisle in a store
 */
@Entity
@Table(name = "aisles")
public class Aisle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the aisle

    @Column(nullable = false)
    private String label; // Label for the aisle

    @ManyToOne
    @JoinColumn(name = "store_aisle")
    private Store store; // Store that the aisle belongs to

    @OneToMany(mappedBy = "aisle")
    private List<Category> categories; // Categories that the aisle contains

    /**
     * Constructor for an aisle
     */
    public Aisle() {

    }

    /**
     * Constructor for an aisle
     * @param label label for the aisle
     * @param store store that the aisle belongs to
     */
    public Aisle(String label, Store store) {
        this.label = label;
        this.store = store;
        this.categories = new ArrayList<>();
    }

    /**
     * Gets the id of the aisle
     * @return id of the aisle
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the label of the aisle
     * @return label of the aisle
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the store that the aisle belongs to
     * @return store that the aisle belongs to
     */
    public Store getStore() {
        return store;
    }

    /**
     * Gets the categories that the aisle contains
     * @return categories that the aisle contains
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Sets the label of the aisle
     * @param label label for the aisle
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Sets the store that the aisle belongs to
     * @param store store that the aisle belongs to
     */
    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * Sets the categories that the aisle contains
     * @param categories categories that the aisle contains
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
