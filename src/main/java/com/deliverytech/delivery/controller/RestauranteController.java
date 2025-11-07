package com.deliverytech.delivery.controller;

import com.deliverytech.delivery.Restaurante;
import com.deliverytech.delivery.service.RestauranteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarTodos();
    }

    @PostMapping
    public Restaurante criarRestaurante(@RequestBody Restaurante restaurante) {
        return restauranteService.salvar(restaurante);
    }
}
