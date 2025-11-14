package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoDTO {
    @NotNull private Long produtoId;
    @Min(1) private int quantidade;
    public Long getProdutoId() { return produtoId; } public void setProdutoId(Long produtoId) { this.produtoId = produtoId; }
    public int getQuantidade() { return quantidade; } public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
