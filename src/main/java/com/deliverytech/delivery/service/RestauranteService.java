package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RestauranteService {
    Restaurante cadastrar(RestauranteDTO dto);
    Restaurante buscarPorId(Long id);
    Page<Restaurante> listar(String categoria, Boolean ativo, Pageable pageable);
    Restaurante atualizar(Long id, RestauranteDTO dto);
    void toggleStatus(Long id);
    java.math.BigDecimal calcularTaxaEntrega(Long restauranteId, String cep);
}
