package com.groceryrun.app.entities;

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

    public Aisle() {

    }

    public Aisle(String label) {
        this.label = label;
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

    public void setLabel(String label) {
        this.label = label;
    }

    public void setAisleStore(Store aisleStore) {
        this.aisleStore = aisleStore;
    }
}
