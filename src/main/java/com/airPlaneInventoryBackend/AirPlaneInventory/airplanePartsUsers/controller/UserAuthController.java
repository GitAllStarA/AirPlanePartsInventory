package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/login")
public class UserAuthController {
    private final UserService userService;

    @Autowired
    public UserAuthController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterReq registerReq) {
        return ResponseEntity.ok(userService.register(registerReq));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authReq) {
        return ResponseEntity.ok(userService.authenticateUser(authReq));
    }
}
