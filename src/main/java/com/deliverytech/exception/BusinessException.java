package com.deliverytech.exception;
/**
 * Exceção personalizada para representar erros de regra de negócio na aplicação.
 * Servira como base para exceções mais especificas.
 */

public class BusinessException extends RuntimeException {
    /**
     * Construtor que recebe a mensagem de erro.
     * @param message A mensagem de erro a ser exibida.
     */
    public BusinessException(String message) {
        super(message);
    }


}
