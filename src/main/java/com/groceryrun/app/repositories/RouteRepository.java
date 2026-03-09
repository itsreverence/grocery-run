package com.groceryrun.app.repositories;

import com.groceryrun.app.entities.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {
}
