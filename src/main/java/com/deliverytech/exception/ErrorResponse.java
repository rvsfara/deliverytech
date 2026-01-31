package com.deliverytech.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;


/**
 * Representa a estrutura de resposta de erro padronizado, seguido o padrão RFC 7807.
 * Campos nulos não serão incluídos na resposta JSON
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final Map<String, String> details;
    //Contrutor alternativo para erros que não possuem detalhes de campos específicos.
    public ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, Map<String, String> details) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.details = details;
    }
    //Getters para que os campos sejam serializados em JSON.
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public int getStatus() {
        return status;
    }
    public String getError() {
        return error;
    }
    public String getMessage() {
        return message;
    }
    public String getPath() {
        return path;
    }
    public Map<String, String> getDetails() {
        return details;
    }

}
