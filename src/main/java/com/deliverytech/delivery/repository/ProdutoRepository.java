package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Page<Produto> listarPorRestaurante(Long restauranteId, Boolean disponivel, Pageable pageable);

    Page<Produto> findByCategoria(String categoria, Pageable pageable);

    List<Produto> findByNomeContaining(String nome);
}
