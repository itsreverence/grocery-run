package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "aisles")
public class Aisle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String label;

    @ManyToOne
    @JoinColumn(name = "aisle_store")
    private Store aisleStore;

    @OneToMany(mappedBy = "categoryAisle")
    private List<Category> aisleCategories;

    public Aisle() {

    }

    public Aisle(String label, Store aisleStore) {
        this.label = label;
        this.aisleStore = aisleStore;
        this.aisleCategories = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Store getAisleStore() {
        return aisleStore;
    }

    public List<Category> getAisleCategories() {
        return aisleCategories;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAisleStore(Store aisleStore) {
        this.aisleStore = aisleStore;
    }

    public void setAisleCategories(List<Category> aisleCategories) {
        this.aisleCategories = aisleCategories;
    }
}
