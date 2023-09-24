package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.repo;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.model.AirPlaneParts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirplanePartsRepo
        extends
        JpaRepository<AirPlaneParts,Long>,
        CrudRepository<AirPlaneParts, Long> {
    List<AirPlaneParts> findByAirplaneModel(String model);

    Optional<AirPlaneParts> findByPartId(Long id);
}
