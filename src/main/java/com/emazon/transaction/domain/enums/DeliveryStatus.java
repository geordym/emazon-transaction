package com.emazon.transaction.domain.enums;

public enum DeliveryStatus {
    IN_TRANSIT("In way"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String description;

    DeliveryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
