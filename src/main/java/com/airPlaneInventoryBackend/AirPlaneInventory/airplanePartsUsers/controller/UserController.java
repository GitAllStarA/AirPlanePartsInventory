package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.model.AirplanePartsInventoryUser;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @GetMapping("/getUsers")
    public ResponseEntity<Optional<List<AirplanePartsInventoryUser>>> getAllUsers() {
        return new ResponseEntity<>(Optional.of(userService.getAllUser()), HttpStatus.OK);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Optional<AirplanePartsInventoryUser>> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Optional<AirplanePartsInventoryUser>> addUser(@RequestBody AirplanePartsInventoryUser user){
        return new ResponseEntity<>(userService.addUser(user),HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Optional<AirplanePartsInventoryUser>> updateUser(@RequestBody AirplanePartsInventoryUser updateUser){
        return new ResponseEntity<>(userService.updateUser(updateUser),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.deleteById(id);
        return new ResponseEntity<>("Deleted User :"+id, HttpStatus.ACCEPTED);
    }

}
