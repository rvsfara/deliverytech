package com.deliverytech.dto.request;

import com.deliverytech.model.Endereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "O ID do restaurante é obrigatório")
    private Long restauranteId;

    @NotNull(message = "O endereço de entrega é obrigatório")
    @Valid// <--Importante: Valida os campos dentro do objeto Endereco
    private Endereco enderecoEntrega;

    @NotNull(message = "A lista de itens do pedido é obrigatória")
    @Valid// <--Importante: Valida os campos dentro de cada item do pedido
    private List<ItemPedidoRequest> itens;
}
