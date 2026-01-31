package com.deliverytech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Handler global para capturar e tratar exceções em toda a aplicação.
 * Centraliza a lógica de tratamento de erros, retornando respostas
 * padronizadas.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Captura excecões de validação (ex. @NotBlank, @Size), lançadas quando um DTO
     * é inválido.
     * Retorna uma resposta HTTP 400 (Nad Request) com os detalhes de cada campo que
     * falhou.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                error:"Erro de validação",
                "Campos inválidos na requisição",
                request.getDescription(false).replace("uri=", ""),
                details
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Captura exceções do tipo EntityNotFoundException e retorna uma resposta HTTP 404 (Not Found).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                error:"Recurso não encontrado",
                ex.getMessage(),
                request.getDescription(false).replace(target:"uri=", replacement:"")
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }

    /**
     * Captura exceções do tipo ConflictException e retorna uma resposta HTTP 409
     * (Conflict).
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflito de dados",
                ex.getMessage(),
                request.getDescription(false).replace(target:"uri=", replacement:""));
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Captura exceções genéricas (erros não esperados) e retorna uma resposta HTTP
     * 500 (Internal Server Error).
     * Isso garante que a API nunca exponha stack traces para o cliente.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                error:"Erro interno do servidor",
                message:"Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getDescription(false).replace(target:"uri=", replacement:""));
        // Opcional: Logar a exceção real para depuração
        // log.error("Erro inesperado:", ex);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

