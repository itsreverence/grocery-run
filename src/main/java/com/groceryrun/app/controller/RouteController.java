package com.groceryrun.app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.groceryrun.app.dto.route.RouteDTO;
import com.groceryrun.app.services.RouteService;

@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("store/{storeId}")
    public List<RouteDTO> generateRoutesForStore(Principal principal, @PathVariable Integer storeId) {
        return routeService.generateRoutesForStore(principal.getName(), storeId);
    }

    @GetMapping("store/{storeId}/grocery-list/{groceryListId}")
    public RouteDTO generateRouteForGroceryList(Principal principal, @PathVariable Integer storeId, @PathVariable Integer groceryListId) {
        return routeService.generateRouteForGroceryList(principal.getName(), storeId, groceryListId);
    }
    
}
