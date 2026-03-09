package com.groceryrun.app.services;

import com.groceryrun.app.dto.item.ItemDTO;
import com.groceryrun.app.dto.item.ItemDTOMapper;
import com.groceryrun.app.dto.item.ItemNameChangeDTO;
import com.groceryrun.app.dto.item.NewItemDTO;
import com.groceryrun.app.entities.Item;
import com.groceryrun.app.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemDTOMapper itemDTOMapper;

    public ItemService(ItemRepository itemRepository, ItemDTOMapper itemDTOMapper) {
        this.itemRepository = itemRepository;
        this.itemDTOMapper = itemDTOMapper;
    }

    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll().stream().map(itemDTOMapper).collect(Collectors.toList());
    }

    public ItemDTO getItemById(Integer id) {
        return itemRepository.findById(id).map(itemDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void addItem(NewItemDTO newItemDTO) {
        Item item = new Item(newItemDTO.name());
        itemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        boolean exists = itemRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        itemRepository.deleteById(id);
    }

    public void updateItemName(Integer id, ItemNameChangeDTO itemNameChangeDTO) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        item.setName(itemNameChangeDTO.newName());
        itemRepository.save(item);
    }
}
