package com.deliverytech.delivery.service;

import com.deliverytech.delivery.Cliente;
import com.deliverytech.delivery.dto.ClienteDTO;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Override
    @CacheEvict(value = "clientes", allEntries = true)
    public Cliente cadastrar(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        c.setAtivo(true);
        return repository.save(c);
    }

    @Override
    @Cacheable(value = "clientes", key = "#id")
    public Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    @Cacheable(value = "clientes", key = "'email:' + #email")
    public Cliente buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email não encontrado"));
    }

    @Override
    @CachePut(value = "clientes", key = "#id")
    public Cliente atualizar(Long id, ClienteDTO dto) {
        Cliente c = buscarPorId(id);
        c.setNome(dto.getNome());
        c.setEmail(dto.getEmail());
        return repository.save(c);
    }

    @Override
    @CacheEvict(value = "clientes", key = "#id")
    public void alterarStatus(Long id) {
        Cliente c = buscarPorId(id);
        c.setAtivo(!c.isAtivo());
        repository.save(c);
    }

    @Override
    @Cacheable(value = "clientes", key = "'lista:' + #pageable.pageNumber")
    public Page<Cliente> listarClientes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Cacheable(value = "clientes", key = "'ativos:' + #pageable.pageNumber")
    public Page<Cliente> listarAtivos(Pageable pageable) {
        return repository.findByAtivoTrue(pageable);
    }
}
