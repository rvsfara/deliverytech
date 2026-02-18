package com.deliverytech.service.impl;

import com.deliverytech.model.Produto;
import com.deliverytech.repository.ProdutoRepository;
import com.deliverytech.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto cadastrar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> buscarPorRestaurante(Long restauranteId) {
        return produtoRepository.findByRestauranteId(restauranteId);
    }

    @Override
    public Produto atualizar(Long id, Produto atualizado) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return produtoRepository.findById(id)
            .map(p -> {
                p.setNome(atualizado.getNome());
                p.setDescricao(atualizado.getDescricao());
                p.setCategoria(atualizado.getCategoria());
                p.setPreco(atualizado.getPreco());
                return produtoRepository.save(p);
            }).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public void alterarDisponibilidade(Long id, boolean disponivel) {
        if (id == null) {
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        produtoRepository.findById(id).ifPresent(p -> {
            p.setDisponivel(disponivel);
            produtoRepository.save(p);
        });
    }
}
