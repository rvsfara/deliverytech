package com.deliverytech.exception;

/**
 * Exceção personalizada para representar erros de entidade não encontrada na aplicação.
 */
public class EntityNotFoundException extends BusinessException{
/**
 * Construtor que formata uma mensagem padrão de "não encontrado".
 * @param entityName O nome da entidade não encontrada (ex. "Cliente", "Produto).
 * @param id O ID da entidade não encontrada.
 */
    public EntityNotFoundException(String entityName, Long id) {
        super(String.format("%s com id %d não encontrado", entityName, id);
    }
}
