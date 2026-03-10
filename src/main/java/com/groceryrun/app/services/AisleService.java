package com.groceryrun.app.services;

import com.groceryrun.app.dto.aisle.AisleDTO;
import com.groceryrun.app.dto.aisle.AisleDTOMapper;
import com.groceryrun.app.dto.aisle.AisleLabelChangeDTO;
import com.groceryrun.app.dto.aisle.AisleStoreChangeDTO;
import com.groceryrun.app.dto.aisle.NewAisleDTO;
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

    public void addAisle(NewAisleDTO newAisleDTO) {
        Aisle aisle = new Aisle(newAisleDTO.label());
        aisleRepository.save(aisle);
    }

    public void deleteAisle(Integer id) {
        boolean exists = aisleRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException(id + " not found");
        }
        aisleRepository.deleteById(id);
    }

    public void updateAisleLabel(Integer id, AisleLabelChangeDTO aisleLabelChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setLabel(aisleLabelChangeDTO.newLabel());
        aisleRepository.save(aisle);
    }

    public void updateAisleStore(Integer id, AisleStoreChangeDTO aisleStoreChangeDTO) {
        Aisle aisle = aisleRepository.findById(id).orElseThrow(() -> new IllegalStateException(id + " not found"));
        aisle.setAisleStore(aisleStoreChangeDTO.newAisleStore());
        aisleRepository.save(aisle);
    }
}
