package com.groceryrun.app.services;

import com.groceryrun.app.dto.route.NewRouteDTO;
import com.groceryrun.app.dto.route.RouteDTO;
import com.groceryrun.app.dto.route.RouteDTOMapper;
import com.groceryrun.app.dto.route.RouteGroceryListChangeDTO;
import com.groceryrun.app.entities.Route;
import com.groceryrun.app.repositories.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final RouteDTOMapper routeDTOMapper;

    public RouteService(RouteRepository routeRepository, RouteDTOMapper routeDTOMapper) {
        this.routeRepository = routeRepository;
        this.routeDTOMapper = routeDTOMapper;
    }

    public List<RouteDTO> getAllRoutes() {
        return routeRepository.findAll().stream().map(routeDTOMapper).collect(Collectors.toList());
    }

    public RouteDTO getRouteById(Integer id) {
        return routeRepository.findById(id).map(routeDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void addRoute(NewRouteDTO newRouteDTO) {
        Route route = new Route(newRouteDTO.routeGroceryList());
        routeRepository.save(route);
    }

    public void deleteRoute(Integer id) {
        boolean exists = routeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        routeRepository.deleteById(id);
    }

    public void updateRouteGroceryList(Integer id, RouteGroceryListChangeDTO routeGroceryListChangeDTO) {
        Route route = routeRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        route.setRouteGroceryList(routeGroceryListChangeDTO.newRouteGroceryList());
        routeRepository.save(route);
    }
}
