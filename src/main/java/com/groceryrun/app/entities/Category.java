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
    @JoinColumn(name = "category_aisle")
    private Aisle categoryAisle;

    @OneToMany(mappedBy = "itemCategory")
    private List<Item> categoryItems;

    public Category() {

    }

    public Category(String label, Aisle categoryAisle) {
        this.label = label;
        this.categoryAisle = categoryAisle;
        this.categoryItems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Aisle getCategoryAisle() {
        return categoryAisle;
    }

    public List<Item> getCategoryItems() {
        return categoryItems;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCategoryAisle(Aisle categoryAisle) {
        this.categoryAisle = categoryAisle;
    }

    public void setCategoryItems(List<Item> categoryItems) {
        this.categoryItems = categoryItems;
    }
}
