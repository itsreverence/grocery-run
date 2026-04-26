package com.groceryrun.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.groceryrun.app.dto.route.RouteDTO;
import com.groceryrun.app.services.RouteService;

/**
 * Controller for routes
 */
@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    private final RouteService routeService; // Service for route data

    /**
     * Constructor for route controller
     * @param routeService service for route data
     */
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    /**
     * Generates routes for all grocery lists owned by the user against a store
     * @param principal principal of the user
     * @param storeId id of the store
     * @return list of route DTOs
     */
    @GetMapping("store/{storeId}")
    public List<RouteDTO> generateRoutesForStore(Principal principal, @PathVariable Integer storeId) {
        return routeService.generateRoutesForStore(principal.getName(), storeId);
    }

    /**
     * Generates a route for a grocery list
     * @param principal principal of the user
     * @param storeId id of the store
     * @param groceryListId id of the grocery list
     * @return route DTO
     */
    @GetMapping("store/{storeId}/grocery-list/{groceryListId}")
    public RouteDTO generateRouteForGroceryList(Principal principal, @PathVariable Integer storeId, @PathVariable Integer groceryListId) {
        return routeService.generateRouteForGroceryList(principal.getName(), storeId, groceryListId);
    }
    
}
