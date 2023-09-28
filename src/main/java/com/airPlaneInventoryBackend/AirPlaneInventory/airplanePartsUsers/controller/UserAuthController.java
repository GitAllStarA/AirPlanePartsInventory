package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.roles.Role;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.roles.RoleUtils;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin({"http://localhost:4200"})
@RestController
@RequestMapping("/app/login/")
public class UserAuthController {
    private final UserService userService;

    @Autowired
    public UserAuthController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq registerReq) {
        Role role;
        try {
            role = Role.valueOf(registerReq.getRole().toUpperCase());
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role: " + registerReq.getRole());
        }
        if (userService.hasEmailRegistered(registerReq.getEmail())){
            Map<String,String> errorMap = new HashMap<>();
            errorMap.put("error",registerReq.getEmail()+" email registered has already, please use different email");
            return ResponseEntity.badRequest().body(errorMap);
        }

        return ResponseEntity.ok(userService.register(registerReq));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authReq) {
        return ResponseEntity.ok(userService.authenticateUser(authReq));
    }
}
