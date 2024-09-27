package com.emazon.transaction.domain.enums;

public enum SyncStatus {
    WAITING_FOR_RECEIPT("Waiting for receipt delivery"),
    PENDING("Pending"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    FAILED("Failure");

    private final String description;

    SyncStatus(String description) {
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
