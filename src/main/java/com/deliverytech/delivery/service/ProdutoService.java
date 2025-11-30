package com.deliverytech.delivery.service;

import com.deliverytech.delivery.Produto;
import com.deliverytech.delivery.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoService {

    Produto cadastrar(ProdutoDTO dto);

    Produto buscarPorId(Long id);

    Page<Produto> listarPorRestaurante(Long restauranteId, Boolean disponivel, Pageable pageable);

    Produto atualizar(Long id, ProdutoDTO dto);

    void alterarDisponibilidade(Long id, boolean disponivel);

    Page<Produto> listarPorCategoria(String categoria, Pageable pageable);

    List<Produto> buscarPorNome(String nome);
}
