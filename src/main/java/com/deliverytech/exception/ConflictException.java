package com.deliverytech.exception;

/**
 * Exceção para indicar conflito de dados, como tentatica de criar um recurso que já existe.
 */
public class ConflictException extends BusinessException {
    /**
     * Construtor que recebe a mensagem de conflito.
     * @param message A mensagem descritiva do conflito.
     */
    public ConflictException(String message) {
        super(message);
    }
}


}
