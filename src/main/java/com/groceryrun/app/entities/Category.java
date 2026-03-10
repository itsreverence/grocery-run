package com.groceryrun.app.entities;

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

    public Category() {

    }

    public Category(String label, Aisle categoryAisle) {
        this.label = label;
        this.categoryAisle = categoryAisle;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public void setCategoryAisle(Aisle categoryAisle) {
        this.categoryAisle = categoryAisle;
    }
}
