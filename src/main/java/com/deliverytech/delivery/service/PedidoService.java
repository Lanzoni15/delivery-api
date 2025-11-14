package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface PedidoService {
    Pedido criarPedido(PedidoDTO dto);
    Pedido buscarPorId(Long id);
    Page<Pedido> listar(Pageable pageable);
    List<Pedido> buscarPorCliente(Long clienteId);
    Page<Pedido> buscarPorRestaurante(Long restauranteId, Pageable pageable);
    Pedido atualizarStatus(Long id);
    BigDecimal calcularTotalPedido(List<ItemPedidoDTO> itens);
    void cancelarPedido(Long id);
}
