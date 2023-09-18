package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Setter
@Getter
@Entity
public class AirPlaneParts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private String airplaneModel;
    private int quantity;
    private int reorderThreshold;
    private float price;
    @Column(nullable = false)
    private String supplier;
    private String expectedDeliveryDate;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false,updatable = false)
    private String UUID;

}
