package com.groceryrun.app.services;

import com.groceryrun.app.dto.aisle.AisleCategoriesChangeDTO;
import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.aisle.AisleStoreChangeDTO;
import com.groceryrun.app.dto.shared.LabelChangeDTO;
import com.groceryrun.app.entities.Aisle;
import com.groceryrun.app.repositories.AisleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AisleService {
    private final AisleRepository aisleRepository;
    private final AisleDTOMapper aisleDTOMapper;

    public AisleService(AisleRepository aisleRepository, AisleDTOMapper aisleDTOMapper) {
        this.aisleRepository = aisleRepository;
        this.aisleDTOMapper = aisleDTOMapper;
    }

    public List<AisleDTO> getAllAisles() {
        return aisleRepository.findAll().stream().map(aisleDTOMapper).collect(Collectors.toList());
    }

    public AisleDTO getAisleById(Integer id) {
        return aisleRepository.findById(id).map(aisleDTOMapper).orElseThrow(() -> new IllegalStateException(id + " not found"));
    }

    public void updateAisleLabel(Integer id, LabelChangeDTO labelChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setLabel(labelChangeDTO.newLabel());
        aisleRepository.save(aisle);
    }

    public void updateAisleStore(Integer id, AisleStoreChangeDTO aisleStoreChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setStore(aisleStoreChangeDTO.newAisleStore());
        aisleRepository.save(aisle);
    }

    public void updateAisleCategories(Integer id, AisleCategoriesChangeDTO aisleCategoriesChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setCategories(aisleCategoriesChangeDTO.newAisleCategories());
        aisleRepository.save(aisle);
    }
}
