package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.controller;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.model.AirPlaneParts;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.service.AirPlanePartsServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping("/app/parts")
public class AirPlanePartsController {

    private final AirPlanePartsServiceLayer airPlanePartsServiceLayer;

    @Autowired
    public AirPlanePartsController(AirPlanePartsServiceLayer airPlanePartsServiceLayer) {
        this.airPlanePartsServiceLayer = airPlanePartsServiceLayer;
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, Object>> getTest() {

        long time = System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(time);
        ZonedDateTime cst = instant.atZone(ZoneId.of("America/Chicago"));

        Map<String, Object> registerData = new HashMap<>();
        registerData.put("firstname", "<name>");
        registerData.put("lastname", "<lastname>");
        registerData.put("email", "<a@example.com>");
        registerData.put("password", "<123456>");
        registerData.put("role", "<ADMIN || USER || MAINTENANCE_STAFF || PROCUREMENT_STAFF>");

        Map<String, String> authenticateData = new HashMap<>();
        authenticateData.put("email", "a@example.com");
        authenticateData.put("password", "123456");

        Map<String, Object> jsonResponse = new HashMap<>();
        jsonResponse.put("timeInCST", cst.toString());
        jsonResponse.put("register", registerData);
        jsonResponse.put("authenticate", authenticateData);

        return ResponseEntity.ok(jsonResponse);
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<AirPlaneParts>> getAll() {
        return new ResponseEntity<>(airPlanePartsServiceLayer.getAllParts(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MAINTENANCE_STAFF')")
    public ResponseEntity<AirPlaneParts> addPart(@RequestBody AirPlaneParts airPlaneParts) {
        return new ResponseEntity<>(airPlanePartsServiceLayer.addParts(airPlaneParts), HttpStatus.CREATED);
    }

    @GetMapping("/model/{name}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MAINTENANCE_STAFF') or hasAuthority('PROCUREMENT_STAFF')")
    public ResponseEntity<List<AirPlaneParts>> getModelParts(@PathVariable("name") String name) {
        return new ResponseEntity<>(airPlanePartsServiceLayer.getByModel(name), HttpStatus.OK);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MAINTENANCE_STAFF') or hasAuthority('PROCUREMENT_STAFF')")
    public ResponseEntity<AirPlaneParts> updatePart(@RequestBody AirPlaneParts airPlaneParts) {
        AirPlaneParts updatedAirPlaneParts = airPlanePartsServiceLayer.updateParts(airPlaneParts);
        return new ResponseEntity<>(updatedAirPlaneParts, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePart(@PathVariable("id") Long id) {
        airPlanePartsServiceLayer.deleteParts(id);
        return new ResponseEntity<>("Deleted", HttpStatus.valueOf("Deleted the air plane part"));
    }
}
