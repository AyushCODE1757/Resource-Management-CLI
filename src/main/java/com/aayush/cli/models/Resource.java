package com.aayush.cli.models;

import java.time.LocalDate;

public class Resource {
    private String id;
    private String name;
    private ResourceType type;
    private AllocationType allocationType;
    private ResourceStatus status;
    private String allocatedTo;
    private LocalDate lastMaintenance;
    private LocalDate purchaseDate;

    public Resource() {
    }

    public Resource(String id, String name, ResourceType type, AllocationType allocationType,
            ResourceStatus status, String allocatedTo, LocalDate lastMaintenance, LocalDate purchaseDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.allocationType = allocationType;
        this.status = status;
        this.allocatedTo = allocatedTo;
        this.lastMaintenance = lastMaintenance;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public AllocationType getAllocationType() {
        return allocationType;
    }

    public void setAllocationType(AllocationType allocationType) {
        this.allocationType = allocationType;
    }

    public ResourceStatus getStatus() {
        return status;
    }

    public void setStatus(ResourceStatus status) {
        this.status = status;
    }

    public String getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(String allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-12s | %-10s | %-15s | %-15s",
                id, name, type, status, allocatedTo != null ? allocatedTo : "N/A", allocationType);
    }
}
