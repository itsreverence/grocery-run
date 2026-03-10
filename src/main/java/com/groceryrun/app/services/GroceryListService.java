package com.groceryrun.app.services;

import com.groceryrun.app.dto.grocerylist.*;
import com.groceryrun.app.entities.GroceryList;
import com.groceryrun.app.repositories.GroceryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryListService {
    private final GroceryListRepository groceryListRepository;
    private final GroceryListDTOMapper groceryListDTOMapper;

    public GroceryListService(GroceryListRepository groceryListRepository, GroceryListDTOMapper groceryListDTOMapper) {
        this.groceryListRepository = groceryListRepository;
        this.groceryListDTOMapper = groceryListDTOMapper;
    }

    public List<GroceryListDTO> getAllGroceryLists() {
        return groceryListRepository.findAll().stream().map(groceryListDTOMapper).collect(Collectors.toList());
    }

    public GroceryListDTO getGroceryListById(Integer id) {
        return groceryListRepository.findById(id).map(groceryListDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void addGroceryList(NewGroceryListDTO newGroceryListDTO) {
        GroceryList groceryList = new GroceryList(newGroceryListDTO.groceryListName(), newGroceryListDTO.groceryListOwner());
        groceryListRepository.save(groceryList);
    }

    public void deleteGroceryList(Integer id) {
        boolean exists = groceryListRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        groceryListRepository.deleteById(id);
    }

    public void updateGroceryListName(Integer id, GroceryListNameChangeDTO groceryListNameChangeDTO) {
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        groceryList.setName(groceryListNameChangeDTO.newName());
        groceryListRepository.save(groceryList);
    }

    public void updateGroceryListOwner(Integer id, GroceryListOwnerChangeDTO groceryListOwnerChangeDTO) {
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        groceryList.setGroceryListOwner(groceryListOwnerChangeDTO.newGroceryListOwner());
        groceryListRepository.save(groceryList);
    }

    public void updateGroceryListStore(Integer id, GroceryListStoreChangeDTO groceryListStoreChangeDTO) {
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        groceryList.setGroceryListStore(groceryListStoreChangeDTO.newStore());
        groceryListRepository.save(groceryList);
    }

    public void updateGroceryListRoute(Integer id, GroceryListRouteChangeDTO groceryListRouteChangeDTO) {
        GroceryList groceryList = groceryListRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        groceryList.setListRoute(groceryListRouteChangeDTO.newRoute());
        groceryListRepository.save(groceryList);
    }
}
