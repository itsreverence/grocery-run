package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for item data
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {
    /**
     * Finds an item by its name
     * @param name name of the item
     * @return optional item
     */
    Optional<Item> findByName(String name);
}
