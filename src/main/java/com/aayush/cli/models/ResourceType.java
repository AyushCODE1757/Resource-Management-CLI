package com.aayush.cli.models;

public enum ResourceType {
    LAPTOP("Electronics"), MONITOR("Electronics"),
    KEYBOARD("Electronics"), MOUSE("Electronics"),
    CHAIR("Furniture"), DESK("Furniture"),
    OTHER("Miscellaneous");

    private final String category;

    ResourceType(String category) { this.category = category; }

    public String getCategory() { return category; }
}