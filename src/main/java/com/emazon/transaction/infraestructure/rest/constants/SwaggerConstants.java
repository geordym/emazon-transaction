package com.emazon.transaction.infraestructure.rest.constants;

public class SwaggerConstants {

    // Información general sobre la operación
    public static final String CONFIRM_SUPPLY_RECEIPT_SUMMARY = "Confirm Supply Receipt";
    public static final String CONFIRM_SUPPLY_RECEIPT_DESCRIPTION = "Confirms the receipt of a supply based on the provided supply ID.";

    // Descripciones para las respuestas
    public static final String CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_200_DESCRIPTION = "Supply receipt confirmed successfully.";
    public static final String CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_403_DESCRIPTION = "Access denied, user is not authorized.";
    public static final String CONFIRM_SUPPLY_RECEIPT_API_RESPONSES_404_DESCRIPTION = "Supply not found with the provided ID.";

    // Mensaje de respuesta genérico
    public static final String CONFIRM_RECEIVED_MESSAGE = "Supply receipt confirmed.";
    public static final String CONFIRM_REJECTED_MESSAGE = "Supply rejected has been received.";




    //CREATE SUPPLY
    // Información general sobre la operación
    public static final String CREATE_SUPPLY_SUMMARY = "Create Supply";
    public static final String CREATE_SUPPLY_DESCRIPTION = "Creates a new supply based on the provided supply details.";

    // Descripciones para las respuestas
    public static final String CREATE_SUPPLY_API_RESPONSES_201_DESCRIPTION = "Supply created successfully.";
    public static final String CREATE_SUPPLY_API_RESPONSES_400_DESCRIPTION = "Invalid input provided.";
    public static final String CREATE_SUPPLY_API_RESPONSES_403_DESCRIPTION = "Access denied, user is not authorized.";

    // Mensajes de respuesta genéricos
    public static final String SUPPLY_CREATED_MESSAGE = "Supply has been created.";


    // Información general sobre la operación de compra
    public static final String BUY_SUMMARY = "Buy Operation";
    public static final String BUY_DESCRIPTION = "Initiates a buy operation and returns a status message.";

    // Descripciones para las respuestas
    public static final String BUY_API_RESPONSES_200_DESCRIPTION = "Buy operation completed successfully.";
    public static final String BUY_API_RESPONSES_400_DESCRIPTION = "Invalid request parameters.";
    public static final String BUY_API_RESPONSES_403_DESCRIPTION = "Access denied, user is not authorized.";

    // Mensajes de respuesta genéricos
    public static final String BUY_SUCCESS_MESSAGE = "Compra realizada con éxito.";
}
