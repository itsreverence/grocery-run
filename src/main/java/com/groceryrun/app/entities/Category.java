package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Entity for a category in an aisle
 */
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the category

    @Column(nullable = false)
    private String label; // Label for the category

    @ManyToOne
    @JoinColumn(name = "aisle_category")
    private Aisle aisle; // Aisle that the category belongs to

    @OneToMany(mappedBy = "category")
    private List<Item> items; // Items that the category contains

    /**
     * Constructor for a category
     */
    public Category() {

    }

    /**
     * Constructor for a category
     * @param label label for the category
     * @param aisle aisle that the category belongs to
     */
    public Category(String label, Aisle aisle) {
        this.label = label;
        this.aisle = aisle;
        this.items = new ArrayList<>();
    }

    /**
     * Gets the id of the category
     * @return id of the category
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the label of the category
     * @return label of the category
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the aisle that the category belongs to
     * @return aisle that the category belongs to
     */
    public Aisle getAisle() {
        return aisle;
    }

    /**
     * Gets the items that the category contains
     * @return items that the category contains
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Sets the label of the category
     * @param label label for the category
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Sets the aisle that the category belongs to
     * @param aisle aisle that the category belongs to
     */
    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    /**
     * Sets the items that the category contains
     * @param items items that the category contains
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
