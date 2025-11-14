package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Page<Restaurante> findByCategoriaAndAtivo(String categoria, Boolean ativo, Pageable pageable);
    Page<Restaurante> findByAtivo(Boolean ativo, Pageable pageable);
    List<Restaurante> findByTaxaEntregaLessThanEqual(BigDecimal taxa);
}
