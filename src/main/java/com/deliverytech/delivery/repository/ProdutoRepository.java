package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
