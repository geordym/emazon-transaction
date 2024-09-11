package com.emazon.transaction.infraestructure.enums;

public enum SyncStatus {
    PENDING("Pendiente"),      // Sincronización aún no iniciada
    IN_PROGRESS("En Progreso"),// Sincronización en curso
    COMPLETED("Completada"),   // Sincronización completada exitosamente
    FAILED("Fallida");         // Sincronización fallida

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
