package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Aisle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AisleRepository extends JpaRepository<Aisle, Integer> {
}
