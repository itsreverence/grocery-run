package com.groceryrun.app.entities;

import jakarta.persistence.*;

/**
 * Entity for a location
 */
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the location

    @Column(nullable = false)
    private String street; // Street of the location

    @Column(nullable = false)
    private String city; // City of the location

    @Column(nullable = false)
    private String state; // State of the location

    @Column(nullable = false)
    private String zip; // Zip code of the location

    @OneToOne(mappedBy = "location")
    private Store store; // Store that the location belongs to

    /**
     * Constructor for a location
     */
    public Location() {}

    /**
     * Constructor for a location
     * @param street street of the location
     * @param city city of the location
     * @param state state of the location
     * @param zip zip code of the location
     * @param store store that the location belongs to
     */
    public Location(String street, String city, String state, String zip, Store store) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.store = store;
    }

    /**
     * Gets the id of the location
     * @return id of the location
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the street of the location
     * @return street of the location
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets the city of the location
     * @return city of the location
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state of the location
     * @return state of the location
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the zip code of the location
     * @return zip code of the location
     */
    public String getZip() {
        return zip;
    }

    /**
     * Gets the store that the location belongs to
     * @return store that the location belongs to
     */
    public Store getStore() {
        return store;
    }

    /**
     * Sets the street of the location
     * @param street street of the location
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Sets the city of the location
     * @param city city of the location
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Sets the state of the location
     * @param state state of the location
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Sets the zip code of the location
     * @param zip zip code of the location
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * Sets the store that the location belongs to
     * @param store store that the location belongs to
     */
    public void setStore(Store store) {
        this.store = store;
    }
}
