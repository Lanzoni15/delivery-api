package com.deliverytech.delivery.repository;

import com.deliverytech.delivery.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteId(Long clienteId);
    Page<Pedido> findByStatus(Pageable pageable);
    Page<Pedido> findByDataPedidoBetween(LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
    Page<Pedido> findByRestauranteId(Long restauranteId, Pageable pageable);
}
