package com.deliverytech.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteRequest {

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "A categoria não pode estar em branco")
    private String categoria;

    @NotBlank(message = "O telefone não pode estar em branco")
    private String telefone;

    @DecimalMin("0.0")
    @NotNull(message = "A taxa de entrega é obrigatória")
    private BigDecimal taxaEntrega;

    @Min(value = 10, message = "O tempo de entrega mínimo é de 10 minutos")
    @NotNull(message = "O tempo de entrega é obrigatório"
    @Max(value = 120, message = "O tempo de entrega máximo é de 120 minutos")
    private Integer tempoEntregaMinutos;
}
