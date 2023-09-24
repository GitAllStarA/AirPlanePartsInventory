package com.airPlaneInventoryBackend.AirPlaneInventory.airplanePartsUsers.roles;

public class RoleUtils {
    public static Role stringToEnum(String role) {
        try {
            return Role.valueOf(role.toUpperCase());
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role :"+ role);
        }
    }
}
