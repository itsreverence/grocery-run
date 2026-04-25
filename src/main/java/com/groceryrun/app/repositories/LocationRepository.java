package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for location data
 */
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
