package com.deliverytech.delivery.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoDTO {
    @NotNull private Long clienteId;
    @NotNull private Long restauranteId;
    @NotBlank private String enderecoEntrega;
    @Valid private List<ItemPedidoDTO> itens;
    // getters / setters
    public Long getClienteId() { return clienteId; } public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getRestauranteId() { return restauranteId; } public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
    public String getEnderecoEntrega() { return enderecoEntrega; } public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
    public List<ItemPedidoDTO> getItens() { return itens; } public void setItens(List<ItemPedidoDTO> itens) { this.itens = itens; }
}
