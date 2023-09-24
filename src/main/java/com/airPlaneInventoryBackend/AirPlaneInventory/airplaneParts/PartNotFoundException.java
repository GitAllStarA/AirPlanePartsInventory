package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts;

public class PartNotFoundException  extends RuntimeException{
    public PartNotFoundException(String message){
        super(message);
    }
}
