package com.aayush.cli.models;

import java.time.LocalDate;

public class DigitalResource extends Resource {
    private String licenseKey;

    public DigitalResource(String id, String name, ResourceType type, AllocationType allocationType, ResourceStatus status, String allocatedTo, LocalDate lastMaintenance, LocalDate purchaseDate, String licenseKey) {
        super(id, name, type, allocationType, status, allocatedTo, lastMaintenance, purchaseDate);
        this.licenseKey = licenseKey;
    }

    public DigitalResource(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
