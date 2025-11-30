package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ApiResponse;
import com.deliverytech.delivery.dto.PagedResponse;
import com.deliverytech.delivery.dto.PageMetadata;
import com.deliverytech.delivery.dto.RestauranteDTO;
import com.deliverytech.delivery.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    private final RestauranteService service;
    private final ModelMapper mapper;

    public RestauranteController(RestauranteService service, ModelMapper mapper) {
        this.service = service; this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Restaurante>> criar(@Valid @RequestBody RestauranteDTO dto) {
        Restaurante r = service.cadastrar(dto);
        return ResponseEntity.created(URI.create("/api/restaurantes/" + r.getId())).body(ApiResponse.of(r, "Restaurante criado"));
    }

    @GetMapping
    public ResponseEntity<PagedResponse<Restaurante>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Sort.Direction dir = Sort.Direction.fromString(sort[1].toUpperCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sort[0]));
        Page<Restaurante> p = service.listar(categoria, ativo, pageable);
        List<Restaurante> content = p.getContent();
        PagedResponse<Restaurante> resp = new PagedResponse<>(
            content,
            PagedResponse.pageMetadata(p.getNumber(), p.getSize(), p.getTotalElements()),
            Map.of("first", "/api/restaurantes?page=0&size=" + p.getSize(), "last", "/api/restaurantes?page=" + (p.getTotalPages() -1))
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurante>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Restaurante>> atualizar(@PathVariable Long id, @Valid @RequestBody RestauranteDTO dto) {
        return ResponseEntity.ok(ApiResponse.of(service.atualizar(id, dto), "Restaurante atualizado"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggle(@PathVariable Long id) {
        service.toggleStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ApiResponse<List<Restaurante>>> porCategoria(@PathVariable String categoria) {
        Page<Restaurante> p = service.listar(categoria, null, PageRequest.of(0, 50));
        return ResponseEntity.ok(ApiResponse.of(p.getContent()));
    }

    @GetMapping("/{id}/taxa-entrega/{cep}")
    public ResponseEntity<ApiResponse<java.math.BigDecimal>> taxaEntrega(@PathVariable Long id, @PathVariable String cep) {
        return ResponseEntity.ok(ApiResponse.of(service.calcularTaxaEntrega(id, cep)));
    }

    @GetMapping("/proximos/{cep}")
    public ResponseEntity<ApiResponse<List<Restaurante>>> proximos(@PathVariable String cep) {
        Page<Restaurante> p = service.listar(null, true, PageRequest.of(0, 50));
        return ResponseEntity.ok(ApiResponse.of(p.getContent()));
    }
}
