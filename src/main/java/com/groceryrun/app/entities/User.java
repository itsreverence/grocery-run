package com.groceryrun.app.entities;

import com.groceryrun.app.enums.AccountRole;
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
    private AccountRole accountRole = AccountRole.USER;

    @Column(unique = true, nullable = false, length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @OneToMany(mappedBy = "groceryListOwner")
    private List<GroceryList> groceryLists;

    public User() {}

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.groceryLists = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public AccountRole getRole() {
        return accountRole;
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

    public void setRole(AccountRole accountRole) {
        this.accountRole = accountRole;
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
}
