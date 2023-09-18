package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.service;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.repo.AirplanePartsRepo;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.model.AirPlaneParts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AirPlanePartsServiceLayer {

    private final AirplanePartsRepo airplanePartsRepo;

    @Autowired
    public AirPlanePartsServiceLayer(AirplanePartsRepo airplanePartsRepo) {
        this.airplanePartsRepo = airplanePartsRepo;
    }


    public List<AirPlaneParts> getAllParts() {
        return airplanePartsRepo.findAll();
    }

    public List<AirPlaneParts> getByModel(String model) {
        List<AirPlaneParts> airPlaneParts = airplanePartsRepo.findByAirplaneModel(model);
        return airPlaneParts.stream().sorted((a,b)-> (int) (a.getPrice()-b.getPrice())).toList();
    }

    public AirPlaneParts addParts(AirPlaneParts airPlaneParts) {
        airPlaneParts.setUUID(UUID.randomUUID().toString());
        return airplanePartsRepo.save(airPlaneParts);
    }

    public AirPlaneParts updateParts(AirPlaneParts airPlaneParts) {
        if (airPlaneParts.getUUID().equals("") || airPlaneParts.getUUID() == null) {
            Optional<AirPlaneParts> findAirPartID = airplanePartsRepo.findById(airPlaneParts.getPartId());
            airPlaneParts.setUUID(findAirPartID.get().getUUID());
        }
        return airplanePartsRepo.save(airPlaneParts);
    }

    public void deleteParts(Long id) {
        airplanePartsRepo.deleteById(id);
    }


}
