package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ApiResponse;
import com.deliverytech.delivery.dto.PedidoDTO;
import com.deliverytech.delivery.dto.ItemPedidoDTO;
import com.deliverytech.delivery.Pedido;
import com.deliverytech.delivery.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Pedido>> criar(@Valid @RequestBody PedidoDTO dto) {
        Pedido p = service.criarPedido(dto);
        return ResponseEntity.created(URI.create("/api/pedidos/" + p.getId()))
                .body(ApiResponse.of(p, "Pedido criado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pedido>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorId(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        var pedidos = service.listar(pageable);

        return ResponseEntity.ok(ApiResponse.of(pedidos.getContent()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Pedido>> atualizarStatus(
            @PathVariable Long id) {

        return ResponseEntity.ok(ApiResponse.of(service.atualizarStatus(id), "Status atualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<ApiResponse<List<Pedido>>> historicoCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorCliente(clienteId)));
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<ApiResponse<?>> pedidosRestaurante(
            @PathVariable Long restauranteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        var pedidos = service.buscarPorRestaurante(restauranteId, pageable);

        return ResponseEntity.ok(ApiResponse.of(pedidos.getContent()));
    }

    @PostMapping("/calcular")
    public ResponseEntity<ApiResponse<java.math.BigDecimal>> calcularTotal(
            @Valid @RequestBody List<ItemPedidoDTO> itens) {
        return ResponseEntity.ok(ApiResponse.of(service.calcularTotalPedido(itens)));
    }
}
