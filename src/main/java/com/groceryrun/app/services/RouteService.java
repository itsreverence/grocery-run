package com.groceryrun.app.services;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class RouteService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final GroceryListRepository groceryListRepository;

    public RouteService(UserRepository userRepository, StoreRepository storeRepository,
            GroceryListRepository groceryListRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.groceryListRepository = groceryListRepository;
    }

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
        List<Integer> matchedItemIds = new ArrayList<>();

        for (Aisle aisle : store.getAisles()) {
            List<ItemDTO> itemsInAisle = new ArrayList<>();

            for (Category category : aisle.getCategories()) {
                for (Item item : category.getItems()) {
                    if (groceryListItems.contains(item)) {
                        itemsInAisle.add(new ItemDTO(item.getId(), item.getName()));
                        matchedItemIds.add(item.getId());
                    }
                }
            }

            if (!itemsInAisle.isEmpty()) {
                stops.add(new RouteStopDTO(aisle.getId(), aisle.getLabel(), itemsInAisle));
            }
        }

        List<ItemDTO> unmatchedItems = new ArrayList<>();
        for (Item item : groceryListItems) {
            if (!matchedItemIds.contains(item.getId())) {
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
