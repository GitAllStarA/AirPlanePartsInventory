package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    private String uUId;

}

