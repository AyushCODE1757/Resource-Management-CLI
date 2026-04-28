package com.aayush.cli.models;

import java.time.LocalDate;

public class FurnitureResource extends Resource {
    private String location;

    public FurnitureResource(String location) {
        this.location = location;
    }

    public FurnitureResource(String id, String name, ResourceType type, AllocationType allocationType, ResourceStatus status, String allocatedTo, LocalDate lastMaintenance, LocalDate purchaseDate, String location) {
        super(id, name, type, allocationType, status, allocatedTo, lastMaintenance, purchaseDate);
        this.location = location;
    }
}

