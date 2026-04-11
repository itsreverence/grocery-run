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

@Service
public class GroceryListService {
    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final GroceryListDTOMapper groceryListDTOMapper;

    public GroceryListService(GroceryListRepository groceryListRepository, UserRepository userRepository, ItemRepository itemRepository, GroceryListDTOMapper groceryListDTOMapper) {
        this.groceryListRepository = groceryListRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.groceryListDTOMapper = groceryListDTOMapper;
    }

    public List<GroceryListDTO> getAllGroceryLists(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        return user.getGroceryLists().stream().map(groceryListDTOMapper).collect(Collectors.toList());
    }

    public GroceryListDTO getGroceryListById(String username, Integer id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        return groceryListDTOMapper.apply(groceryList);
    }

    public GroceryListTransferDTO exportGroceryList(String username, Integer id) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        return new GroceryListTransferDTO(groceryList.getName(), groceryList.getItems().stream().map(item -> new GroceryListTransferItemDTO(item.getName())).collect(Collectors.toList()));
    }

    public void addGroceryList(String username, NewGroceryListDTO newGroceryListDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = new GroceryList(newGroceryListDTO.groceryListName(), user);
        groceryListRepository.save(groceryList);
        user.getGroceryLists().add(groceryList);
        userRepository.save(user);
    }

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

    public void updateGroceryListName(String username, Integer id, NameChangeDTO nameChangeDTO) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalStateException(username + " not found"));
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        if (!groceryList.getOwner().equals(user)) {
            throw new IllegalStateException("You are not the owner of this grocery list");
        }
        groceryList.setName(nameChangeDTO.newName());
        groceryListRepository.save(groceryList);
    }

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
