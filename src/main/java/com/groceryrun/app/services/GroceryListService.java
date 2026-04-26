package com.groceryrun.app.services;


import com.groceryrun.app.dto.grocerylist.GroceryListDTO;
import com.groceryrun.app.dto.grocerylist.GroceryListDTOMapper;
import com.groceryrun.app.dto.grocerylist.GroceryListTransferDTO;
import com.groceryrun.app.dto.grocerylist.GroceryListTransferItemDTO;
import com.groceryrun.app.dto.grocerylist.NewGroceryListDTO;
import com.groceryrun.app.dto.shared.NameChangeDTO;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.entities.User;
import com.groceryrun.app.repositories.GroceryListRepository;
import com.groceryrun.app.repositories.UserRepository;
import com.groceryrun.app.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Grocery list service which contains the business logic for retrieving, adding, deleting, and updating grocery lists
 */
@Service
public class GroceryListService {
    private final GroceryListRepository groceryListRepository; // Repository for grocery list data
    private final UserRepository userRepository; // Repository for user data
    private final ItemRepository itemRepository; // Repository for item data
    private final GroceryListDTOMapper groceryListDTOMapper; // Mapper for grocery list data

    /**
     * Constructor for grocery list service
     * @param groceryListRepository repository for grocery list data
     * @param userRepository repository for user data
     * @param itemRepository repository for item data
     * @param groceryListDTOMapper mapper for grocery list data
     */
    public GroceryListService(GroceryListRepository groceryListRepository, UserRepository userRepository, ItemRepository itemRepository, GroceryListDTOMapper groceryListDTOMapper) {
        this.groceryListRepository = groceryListRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.groceryListDTOMapper = groceryListDTOMapper;
    }

    /**
     * Retrieves all grocery lists from the database
     * @param username username of the user
     * @return list of grocery list DTOs
     * @throws IllegalStateException if the user is not found
     */
    public List<GroceryListDTO> getAllGroceryLists(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        return user.getGroceryLists().stream().map(groceryListDTOMapper).collect(Collectors.toList());
    }

    /**
     * Retrieves a grocery list by its id
     * @param username username of the user
     * @param id id of the grocery list
     * @return grocery list DTO
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public GroceryListDTO getGroceryListById(String username, Integer id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        return groceryListDTOMapper.apply(groceryList);
    }

    /**
     * Exports a grocery list as a transfer object
     * @param username username of the user
     * @param id id of the grocery list
     * @return grocery list transfer DTO
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public GroceryListTransferDTO exportGroceryList(String username, Integer id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        return new GroceryListTransferDTO(groceryList.getName(), groceryList.getItems().stream().map(item -> new GroceryListTransferItemDTO(item.getName())).collect(Collectors.toList()));
    }

    /**
     * Adds a grocery list to a user
     * @param username username of the user
     * @param newGroceryListDTO new grocery list data
     * @throws IllegalStateException if the user is not found
     */
    public void addGroceryList(String username, NewGroceryListDTO newGroceryListDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = new GroceryList(newGroceryListDTO.groceryListName(), user);
        groceryListRepository.save(groceryList);
        user.getGroceryLists().add(groceryList);
        userRepository.save(user);
    }

    /**
     * Adds an item to a grocery list
     * @param username username of the user
     * @param id id of the grocery list
     * @param itemId id of the item
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public void addItemToGroceryList(String username, Integer id, Integer itemId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException(itemId + " not found"));
        groceryList.getItems().add(item);
        groceryListRepository.save(groceryList);
    }

    /**
     * Imports a grocery list from a transfer object
     * @param username username of the user
     * @param groceryListTransferDTO grocery list transfer data
     * @throws IllegalStateException if the user is not found
     */
    public void importGroceryList(String username, GroceryListTransferDTO groceryListTransferDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        List<Item> importedItems = new ArrayList<>();
        for (GroceryListTransferItemDTO item : groceryListTransferDTO.items()) {
            Item importedItem = itemRepository.findByName(item.name()).orElseThrow(() -> new IllegalStateException(item.name() + " not found"));
            importedItems.add(importedItem);
        }
        GroceryList groceryList = new GroceryList(groceryListTransferDTO.groceryListName(), user);
        groceryList.setItems(importedItems);
        groceryListRepository.save(groceryList);
        user.getGroceryLists().add(groceryList);
        userRepository.save(user);
    }

    /**
     * Deletes a grocery list
     * @param username username of the user
     * @param id id of the grocery list
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public void deleteGroceryList(String username, Integer id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        boolean exists = groceryListRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        groceryListRepository.deleteById(id);
        user.getGroceryLists().remove(groceryList);
        userRepository.save(user);
    }

    /**
     * Updates the name of a grocery list
     * @param username username of the user
     * @param id id of the grocery list
     * @param nameChangeDTO new name
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public void updateGroceryListName(String username, Integer id, NameChangeDTO nameChangeDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        groceryList.setName(nameChangeDTO.newName());
        groceryListRepository.save(groceryList);
    }

    /**
     * Removes an item from a grocery list
     * @param username username of the user
     * @param id id of the grocery list
     * @param itemId id of the item
     * @throws IllegalStateException if the grocery list is not found or the user is not the owner of the grocery list
     */
    public void removeItemFromGroceryList(String username, Integer id, Integer itemId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException(itemId + " not found"));
        groceryList.getItems().remove(item);
        groceryListRepository.save(groceryList);
    }
}
