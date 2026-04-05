package com.groceryrun.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String label;

    @ManyToOne
    @JoinColumn(name = "aisle_category")
    private Aisle aisle;

    @OneToMany(mappedBy = "category")
    private List<Item> items;

    public Category() {

    }

    public Category(String label, Aisle aisle) {
        this.label = label;
        this.aisle = aisle;
        this.items = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Aisle getAisle() {
        return aisle;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
