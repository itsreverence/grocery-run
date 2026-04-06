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
    @JoinColumn(name = "store_aisle")
    private Store store;

    @OneToMany(mappedBy = "aisle")
    private List<Category> categories;

    public Aisle() {

    }

    public Aisle(String label, Store store) {
        this.label = label;
        this.store = store;
        this.categories = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Store getStore() {
        return store;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
