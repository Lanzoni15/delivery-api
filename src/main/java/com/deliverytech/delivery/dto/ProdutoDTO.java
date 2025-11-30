package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoDTO {

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal preco;

    @NotBlank
    private String categoria;

    private boolean disponivel = true;

    @NotNull
    private Long restauranteId;

    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getCategoria() { return categoria; }
    public boolean isDisponivel() { return disponivel; }
    public Long getRestauranteId() { return restauranteId; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
}
