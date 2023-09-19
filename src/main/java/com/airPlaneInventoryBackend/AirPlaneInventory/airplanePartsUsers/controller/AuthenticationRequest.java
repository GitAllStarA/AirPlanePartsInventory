package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationRequest {
    private String email;
    private String password;
}
