package com.emazon.transaction.domain.enums;

public enum SupplyStatus {
    PENDING("Pending"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String displayName;

    SupplyStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static SupplyStatus fromString(String status) {
        for (SupplyStatus supplyStatus : SupplyStatus.values()) {
            if (supplyStatus.displayName.equalsIgnoreCase(status)) {
                return supplyStatus;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}
