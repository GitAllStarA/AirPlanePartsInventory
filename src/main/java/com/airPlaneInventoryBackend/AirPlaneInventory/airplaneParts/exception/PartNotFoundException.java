package com.airPlaneInventoryBackend.AirPlaneInventory.airplaneParts.exception;

public class PartNotFoundException  extends RuntimeException{
    public PartNotFoundException(String message){
        super(message);
    }
}
