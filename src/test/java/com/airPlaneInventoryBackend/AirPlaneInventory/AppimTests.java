package com.airPlaneInventoryBackend.AirPlaneInventory;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.PartNotFoundException;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.model.AirPlaneParts;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.repo.AirplanePartsRepo;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.service.AirPlanePartsServiceLayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;



import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppimTests extends RuntimeException {


    @InjectMocks
    AirPlanePartsServiceLayer airPlanePartsServiceLayer;

    @Mock
    AirplanePartsRepo airplanePartsRepo;

    @Test
    public void testFindByIdService() {
        // Arrange
        AirPlaneParts a = new AirPlaneParts();
        //Long id = 1L;
        Long partId = 12L;
        String name = "Left Wing Flap";
        String description = "Main flap for the left wing";
        String airplaneModel = "Airbus A319";
        int quantity = 1;
        int reorderThreshold = 5;
        float price = 1800.5F;
        String supplier = "Airbus co.";
        String expectedDeliveryDate = "2023-10-20";
        String location = "Warehouse A";
        String uuid = "c2381efb-f719-423e-8015-b20ecd79b334";

        a.setPartId(partId);
        a.setName(name);
        a.setDescription(description);
        a.setAirplaneModel(airplaneModel);
        a.setQuantity(quantity);
        a.setReorderThreshold(reorderThreshold);
        a.setPrice(price);
        a.setSupplier(supplier);
        a.setExpectedDeliveryDate(expectedDeliveryDate);
        a.setLocation(location);
        a.setUUID(uuid);


        // Mock the repo
        var mockRepo = airplanePartsRepo.findById(partId);
        when(mockRepo).thenReturn(Optional.of(a));

        // Act
        Optional<AirPlaneParts> resultSvc = airPlanePartsServiceLayer.findById(partId);
        AirPlaneParts result = resultSvc.get();

        // Assert
        assertEquals(name, result.getName());
        assertEquals(partId, result.getPartId());
        assertEquals(uuid, result.getUUID());
    }


//    @Test(expected = PartNotFoundException.class)
//    public void testBookNotFound() {
//        // Arrange
//        Long id = 2L;
//
//        // Mock
//        when(airplanePartsRepo.findById(id)).thenReturn(Optional.empty());
//
//        // Act
//        airPlanePartsServiceLayer.findById(id);
//
//
//        // Assert
//        PartNotFoundException partNotFoundException = assertThrows(PartNotFoundException.class, () -> airPlanePartsServiceLayer.findById(id));
//        assertTrue(partNotFoundException.getMessage().contains("not found"));
//
//
//    }
}