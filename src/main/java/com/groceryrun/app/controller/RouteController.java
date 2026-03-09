package com.groceryrun.app.controller;

import com.groceryrun.app.dto.route.NewRouteDTO;
import com.groceryrun.app.dto.route.RouteDTO;
import com.groceryrun.app.services.RouteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public List<RouteDTO> getAllRoutes() {
        return routeService.getAllRoutes();
    }

    @GetMapping("{id}")
    public RouteDTO getRoute(@PathVariable Integer id) {
        return routeService.getRouteById(id);
    }

    @PostMapping
    public void addRoute(@ModelAttribute NewRouteDTO newRouteDTO) {
        routeService.addRoute(newRouteDTO);
    }

    @DeleteMapping("{id}")
    public void deleteRoute(@PathVariable Integer id) {
        routeService.deleteRoute(id);
    }
}
