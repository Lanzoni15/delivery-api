package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.dto.ApiResponse;
import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.Cliente;
import com.deliverytech.delivery.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Cliente>> criar(@Valid @RequestBody ClienteDTO dto) {
        Cliente c = service.cadastrar(dto);
        URI location = URI.create("/api/clientes/" + c.getId());
        return ResponseEntity.created(location).body(ApiResponse.of(c, "Cliente criado"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorId(id), "Cliente recuperado"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Cliente>>> listar(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.of(service.listarClientes(pageable)));
    }

    @GetMapping("/ativos")
    public ResponseEntity<ApiResponse<Page<Cliente>>> listarAtivos(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.of(service.listarAtivos(pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Cliente>> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(ApiResponse.of(service.atualizar(id, dto), "Cliente atualizado"));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> alterarStatus(@PathVariable Long id) {
        service.alterarStatus(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Cliente>> buscarPorEmail(@PathVariable String email) {
        return ResponseEntity.ok(ApiResponse.of(service.buscarPorEmail(email)));
    }
}
