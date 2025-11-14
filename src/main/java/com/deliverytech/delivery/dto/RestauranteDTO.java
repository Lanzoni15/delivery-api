package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RestauranteDTO {
    @NotBlank private String nome;
    @NotBlank private String categoria;
    @NotNull private BigDecimal taxaEntrega;
    // getters / setters
    public String getNome() { return nome; } public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; } public void setCategoria(String categoria) { this.categoria = categoria; }
    public BigDecimal getTaxaEntrega() { return taxaEntrega; } public void setTaxaEntrega(BigDecimal taxaEntrega) { this.taxaEntrega = taxaEntrega; }
}
