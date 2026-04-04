package com.groceryrun.app.entities;

import com.groceryrun.app.enums.Role;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "user_grocery_Lists")
    private List<GroceryList> groceryLists;

    @ManyToMany
    @JoinTable(name = "user_stores",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private List<Store> stores;

    public User() {}

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.groceryLists = new ArrayList<>();
        this.stores = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<GroceryList> getGroceryLists() {
        return groceryLists;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setGroceryLists(List<GroceryList> groceryLists) {
        this.groceryLists = groceryLists;
    }

    public void setStores(List<Store> stores) {
        this.stores = stores;
    }
}
