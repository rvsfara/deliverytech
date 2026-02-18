package com.deliverytech.service.impl;

import com.deliverytech.model.Pedido;
import com.deliverytech.model.StatusPedido;
import com.deliverytech.repository.PedidoRepository;
import com.deliverytech.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido criar(Pedido pedido) {
        pedido.setStatus(StatusPedido.CRIADO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> listarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Pedido> listarPorRestaurante(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Pedido atualizarStatus(Long id, StatusPedido status) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo");
        }
        return pedidoRepository.findById(id)
            .map(p -> {
                p.setStatus(status);
                return pedidoRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    @Override
    public void cancelar(Long id) {
        if (id == null) {
            return;
        }
        pedidoRepository.findById(id).ifPresent(p -> {
            p.setStatus(StatusPedido.CANCELADO);
            pedidoRepository.save(p);
        });
    }
}
