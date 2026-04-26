package com.groceryrun.app.entities;

import com.groceryrun.app.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity for a user
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Unique identifier for the user

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER; // Role of the user

    @Column(unique = true, nullable = false, length = 100)
    private String username; // Username of the user

    @Column(nullable = false)
    private String passwordHash; // Password hash of the user

    @OneToMany(mappedBy = "owner")
    private List<GroceryList> groceryLists; // Grocery lists that the user owns

    @ManyToMany
    @JoinTable(name = "user_stores",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> stores; // Stores that the user owns

    /**
     * Constructor for a user
     */
    public User() {}

    /**
     * Constructor for a user
     * @param username username of the user
     * @param passwordHash password hash of the user
     */
    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.groceryLists = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    /**
     * Gets the id of the user
     * @return id of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Gets the role of the user
     * @return role of the user
     */
    public Role getRole() {
        return role;
    }

    /**
     * Gets the username of the user
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password hash of the user
     * @return password hash of the user
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Gets the grocery lists that the user owns
     * @return grocery lists that the user owns
     */
    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    /**
     * Gets the stores that the user owns
     * @return stores that the user owns
     */
    public List<Store> getStores() {
        return stores;
    }

    /**
     * Sets the role of the user
     * @param role role of the user
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Sets the username of the user
     * @param username username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password hash of the user
     * @param passwordHash password hash of the user
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Sets the grocery lists that the user owns
     * @param groceryLists grocery lists that the user owns
     */
    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    /**
     * Sets the stores that the user owns
     * @param stores stores that the user owns
     */
    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
