package com.deliverytech.delivery.service;

import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente cadastrar(ClienteDTO dto);
    Cliente buscarPorId(Long id);
    Cliente buscarPorEmail(String email);
    Cliente atualizar(Long id, ClienteDTO dto);
    void toggleStatus(Long id);
    List<Cliente> listarAtivos();
}
