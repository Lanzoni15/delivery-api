package com.deliverytech.delivery;

import com.deliverytech.delivery.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal preco;

    private String categoria;

    private boolean disponivel = true;

    @Column(name = "restaurante_id")
    private Long restauranteId;

    // Construtor que recebe o DTO
    public Produto(ProdutoDTO dto) {
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.categoria = dto.getCategoria();
        this.disponivel = dto.isDisponivel();
        this.restauranteId = dto.getRestauranteId();
    }
}
