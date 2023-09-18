package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.service;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.authentication.JwtService;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller.AuthenticationRequest;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller.AuthenticationResponse;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.controller.RegisterReq;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.exceptions.UserExceptions;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.model.AirplanePartsInventoryUser;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.roles.Role;
import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.usersRepo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public List<AirplanePartsInventoryUser> getAllUser() {
        return userRepo.findAll();
    }

    public Optional<AirplanePartsInventoryUser> getById(Long id){
        return userRepo.findById(id);
    }

    public Optional<AirplanePartsInventoryUser> addUser(AirplanePartsInventoryUser user) {
        user.setUUId(UUID.randomUUID().toString());
        Optional<AirplanePartsInventoryUser> addUser = Optional.ofNullable(user);
        if (addUser.isEmpty()){
            throw new UserExceptions("user not added : "+user.toString());
        }
        else {
            return Optional.of(userRepo.save(user));
        }
    }


    public Optional<AirplanePartsInventoryUser> updateUser(AirplanePartsInventoryUser user) {
        if (user.getUUId() == null){
            Optional<AirplanePartsInventoryUser> getUserFromDB = userRepo.findById(user.getId());
            user.setUUId(getUserFromDB.get().getUUId());
            return Optional.of(userRepo.save(user));
        }
        else {
            throw new UserExceptions("user not updated : "+user.getId());
        }
    }

    public void deleteById(Long id) {
        if (userRepo.findById(id).isEmpty()){
            throw new UserExceptions("the user id not found"+id);
        }
        else {
            userRepo.deleteById(id);
        }
    }

    public Optional<AirplanePartsInventoryUser> findByEmail(String em) {
        return userRepo.findByEmail(em);
    }

    public AuthenticationResponse register(RegisterReq registerReq) {
        var user = AirplanePartsInventoryUser
                .builder()
                .firstname(registerReq.getFirstname())
                .lastname(registerReq.getLastname())
                .email(registerReq.getEmail())
                .password(passwordEncoder.encode(registerReq.getPassword()))
                .role(Role.valueOf(registerReq.getRole()))
                .uUId(UUID.randomUUID().toString())
                .build();
        userRepo.save(user);
        var jwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest authReq) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authReq.getEmail(),
                        authReq.getPassword()
                )
        );
        var user = userRepo.findByEmail(authReq.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("username not found"+authReq.getEmail()));
        var jwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwToken)
                .build();
    }
}
