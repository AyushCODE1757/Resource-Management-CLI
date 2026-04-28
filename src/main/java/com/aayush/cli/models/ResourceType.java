package com.aayush.cli.models;

public enum ResourceType {
    LAPTOP,
    MONITOR,
    KEYBOARD,
    MOUSE,
    CHAIR,
    DESK,
    OTHER;
    private  String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
