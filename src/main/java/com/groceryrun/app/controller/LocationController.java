package com.groceryrun.app.controller;

import com.groceryrun.app.dto.location.*;
import com.groceryrun.app.services.LocationService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDTO> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("{id}")
    public LocationDTO getLocation(@PathVariable Integer id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("admin")
    public void addNewLocation(Principal principal, @ModelAttribute NewLocationDTO newLocationDTO) {
        locationService.addLocation(principal.getName(), newLocationDTO);
    }

    @DeleteMapping("admin/{id}")
    public void deleteLocation(Principal principal, @PathVariable Integer id) {
        locationService.deleteLocation(principal.getName(), id);
    }

    @PutMapping("admin/{id}/street")
    public void updateLocationStreet(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStreetChangeDTO locationStreetChangeDTO) {
        locationService.updateLocationStreet(principal.getName(), id, locationStreetChangeDTO);
    }

    @PutMapping("admin/{id}/city")
    public void updateLocationCity(Principal principal, @PathVariable Integer id, @ModelAttribute LocationCityChangeDTO locationCityChangeDTO) {
        locationService.updateLocationCity(principal.getName(), id, locationCityChangeDTO);
    }

    @PutMapping("admin/{id}/state")
    public void updateLocationState(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStateChangeDTO locationStateChangeDTO) {
        locationService.updateLocationState(principal.getName(), id, locationStateChangeDTO);
    }

    @PutMapping("admin/{id}/zip")
    public void updateLocationZip(Principal principal, @PathVariable Integer id, @ModelAttribute LocationZipChangeDTO locationZipChangeDTO) {
        locationService.updateLocationZip(principal.getName(), id, locationZipChangeDTO);
    }

    @PutMapping("admin/{id}/store")
    public void updateLocationStore(Principal principal, @PathVariable Integer id, @ModelAttribute LocationStoreChangeDTO locationStoreChangeDTO) {
        locationService.updateLocationStore(principal.getName(), id, locationStoreChangeDTO);
    }
}
