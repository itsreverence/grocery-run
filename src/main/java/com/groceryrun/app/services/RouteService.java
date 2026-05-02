package com.groceryrun.app.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.route.RouteDTO;
import com.groceryrun.app.dto.route.RouteStopDTO;
import com.groceryrun.app.entities.Category;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.Store;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.UserRepository;
import com.groceryrun.app.repositories.GroceryListRepository;
import com.groceryrun.app.repositories.StoreRepository;

/**
 * Route service which contains the business logic for generating routes for grocery lists
 */
@Service
public class RouteService {

    private final UserRepository userRepository; // Repository for user data
    private final StoreRepository storeRepository; // Repository for store data
    private final GroceryListRepository groceryListRepository; // Repository for grocery list data

    /**
     * Constructor for route service
     * @param userRepository repository for user data
     * @param storeRepository repository for store data
     * @param groceryListRepository repository for grocery list data
     */
    public RouteService(UserRepository userRepository, StoreRepository storeRepository,
            GroceryListRepository groceryListRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.groceryListRepository = groceryListRepository;
    }

    /**
     * Generates routes for all grocery lists owned by the user against a store
     * @param username username of the user
     * @param storeId id of the store
     * @return list of route DTOs
     * @throws IllegalStateException if the user is not found or the store is not found
     */
    public List<RouteDTO> generateRoutesForStore(String username, Integer storeId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));

        List<GroceryList> groceryLists = user.getGroceryLists();
        List<RouteDTO> routes = new ArrayList<>();
        for (GroceryList groceryList : groceryLists) {
            routes.add(generateRouteForGroceryList(username, storeId, groceryList.getId()));
        }
        return routes;
    }

    /**
     * Generates a route for a grocery list
     * @param username username of the user
     * @param storeId id of the store
     * @param groceryListId id of the grocery list
     * @return route DTO
     * @throws IllegalStateException if the user, store, or grocery list is not found, or if the user is not the owner of the grocery list
     */
    public RouteDTO generateRouteForGroceryList(String username, Integer storeId, Integer groceryListId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new IllegalStateException(storeId + " not found"));

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new IllegalStateException(groceryListId + " not found"));

        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }

        List<Item> groceryListItems = groceryList.getItems();
        List<RouteStopDTO> stops = new ArrayList<>();
        Set<String> matchedItemNames = new HashSet<>();

        for (Aisle aisle : store.getAisles()) {
            List<ItemDTO> itemsInAisle = new ArrayList<>();

            for (Category category : aisle.getCategories()) {
                for (Item item : category.getItems()) {
                    if (item.getName() != null
                            && groceryListItems.stream().anyMatch(listItem -> listItem.getName() != null
                                    && listItem.getName().trim().equalsIgnoreCase(item.getName().trim()))
                            && matchedItemNames.add(item.getName().trim().toLowerCase())) {
                        itemsInAisle.add(new ItemDTO(item.getId(), item.getName()));
                    }
                }
            }

            if (!itemsInAisle.isEmpty()) {
                stops.add(new RouteStopDTO(aisle.getId(), aisle.getLabel(), itemsInAisle));
            }
        }

        List<ItemDTO> unmatchedItems = new ArrayList<>();
        for (Item item : groceryListItems) {
            if (item.getName() == null || !matchedItemNames.contains(item.getName().trim().toLowerCase())) {
                unmatchedItems.add(new ItemDTO(item.getId(), item.getName()));
            }
        }

        return new RouteDTO(
                store.getId(),
                store.getName(),
                groceryList.getId(),
                groceryList.getName(),
                stops,
                unmatchedItems);
    }

}
