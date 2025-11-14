package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ApiResponse;
import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.Produto;
import com.deliverytech.delivery.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;
    public ProdutoController(ProdutoService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<ApiResponse<Produto>> criar(@Valid @RequestBody ProdutoDTO dto) {
        Produto p = service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/produtos/" + p.getId())).body(ApiResponse.of(p, "Produto criado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        return ResponseEntity.ok(ApiResponse.of(service.atualizar(id, dto), "Produto atualizado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.buscarPorId(id); // throw if not exists
        service.alterarDisponibilidade(id, false); // soft remove pattern
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<Void> toggleDisponibilidade(@PathVariable Long id, @RequestParam boolean disponivel) {
        service.alterarDisponibilidade(id, disponivel);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<ApiResponse<List<Produto>>> porRestaurante(@PathVariable Long restauranteId,
                                                                     @RequestParam(required = false) Boolean disponivel,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        var pageResult = service.listarPorRestaurante(restauranteId, disponivel, pageable);
        return ResponseEntity.ok(ApiResponse.of(pageResult.getContent()));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ApiResponse<?>> porCategoria(@PathVariable String categoria,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        var p = service.listarPorCategoria(categoria, PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.of(p.getContent()));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<Produto>>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorNome(nome)));
    }
}
