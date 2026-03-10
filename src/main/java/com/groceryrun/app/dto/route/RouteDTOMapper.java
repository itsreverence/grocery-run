package com.groceryrun.app.dto.route;

import com.groceryrun.app.entities.Route;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RouteDTOMapper implements Function<Route, RouteDTO> {
    @Override
    public RouteDTO apply(Route route) {
        return new RouteDTO(route.getId(), route.getRouteGroceryList());
    }
}
