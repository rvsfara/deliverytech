package com.deliverytech.service.impl;

import com.deliverytech.model.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.deliverytech.repository.RestauranteRepository;
import com.deliverytech.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    @Override
    public Restaurante cadastrar(Restaurante restaurante) {
        if (restaurante == null) {
            throw new IllegalArgumentException("Restaurante não pode ser nulo");
        }
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Optional<Restaurante> buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo");
        }
        return restauranteRepository.findById(id);
    }
    /* 
    @Override
    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    } */
    //Modificado: A assinatura do étodo agora aceita Pageable
    @Override
    public Page<Restaurante> listarTodos(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable não pode ser nulo");
        }
        return restauranteRepository.findAll(pageable);
    }

    @Override
    public List<Restaurante> buscarPorCategoria(String categoria) {
        return restauranteRepository.findByCategoria(categoria);
    }

    @Override
    public Restaurante atualizar(Long id, Restaurante atualizado) {
        if (id == null) {
            throw new IllegalArgumentException("O id não pode ser nulo");
        }
        return restauranteRepository.findById(id)
            .map(r -> {
                r.setNome(atualizado.getNome());
                r.setTelefone(atualizado.getTelefone());
                r.setCategoria(atualizado.getCategoria());
                r.setTaxaEntrega(atualizado.getTaxaEntrega());
                r.setTempoEntregaMinutos(atualizado.getTempoEntregaMinutos());
                return restauranteRepository.save(r);
            }).orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }
}
