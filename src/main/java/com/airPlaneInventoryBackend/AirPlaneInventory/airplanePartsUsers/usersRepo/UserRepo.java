package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.usersRepo;

import com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.model.AirplanePartsInventoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AirplanePartsInventoryUser,Long>, CrudRepository<AirplanePartsInventoryUser,Long> {
    Optional<AirplanePartsInventoryUser> findByEmail(String email);
}
