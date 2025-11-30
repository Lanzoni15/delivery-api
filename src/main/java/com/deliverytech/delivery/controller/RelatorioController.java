package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ApiResponse;
import com.deliverytech.delivery.repository.PedidoRepository;
import com.deliverytech.delivery.repository.ProdutoRepository;
import com.deliverytech.delivery.repository.RestauranteRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    public RelatorioController(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping("/vendas-por-restaurante")
    public ResponseEntity<ApiResponse<?>> vendasPorRestaurante(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(ApiResponse.of(Map.of("message", "Relatório pronto - implementar agregação")));
    }

    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<ApiResponse<?>> produtosMaisVendidos() {
        return ResponseEntity.ok(ApiResponse.of(Map.of("message", "Top produtos - implementar agregação")));
    }

    @GetMapping("/clientes-ativos")
    public ResponseEntity<ApiResponse<?>> clientesAtivos() {
        return ResponseEntity.ok(ApiResponse.of(Map.of("message", "Clientes ativos - implementar agregação")));
    }

    @GetMapping("/pedidos-por-periodo")
    public ResponseEntity<ApiResponse<?>> pedidosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return ResponseEntity.ok(ApiResponse.of(Map.of("message", "Pedidos por período - implementar agregação")));
    }
}
