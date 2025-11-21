package com.deliverytech.delivery.service;

import com.deliverytech.delivery.Cliente;
import com.deliverytech.delivery.dto.ClienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Override
    public Cliente cadastrar(ClienteDTO dto) {
        return null;
    }

    @Override
    public Cliente buscarPorId(Long id) {
        return null;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return null;
    }

    @Override
    public Cliente atualizar(Long id, ClienteDTO dto) {
        return null;
    }

    @Override
    public void toggleStatus(Long id) {
    }

    @Override
    public List<Cliente> listarAtivos() {
        return List.of();
    }
}
