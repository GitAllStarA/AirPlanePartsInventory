package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class RegisterReq {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    private String uUId;

}

