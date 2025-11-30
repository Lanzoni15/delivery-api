package com.deliverytech.delivery.service;

import com.deliverytech.delivery.Produto;
import com.deliverytech.delivery.dto.ProdutoDTO;
import com.deliverytech.delivery.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Override
    @CacheEvict(value = "produtos", allEntries = true)
    public Produto cadastrar(ProdutoDTO dto) {
        Produto p = new Produto(dto);
        return repository.save(p);
    }

    @Override
    @Cacheable(value = "produtos", key = "#id")
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    @Cacheable(value = "produtos", key = "'rest:' + #restauranteId + ':' + #disponivel + ':' + #pageable.pageNumber")
    public Page<Produto> listarPorRestaurante(Long restauranteId, Boolean disponivel, Pageable pageable) {
        return repository.listarPorRestaurante(restauranteId, disponivel, pageable);
    }

    @Override
    @CachePut(value = "produtos", key = "#id")
    public Produto atualizar(Long id, ProdutoDTO dto) {
        Produto p = buscarPorId(id);
        p.setNome(dto.getNome());
        p.setPreco(dto.getPreco());
        p.setCategoria(dto.getCategoria());
        return repository.save(p);
    }

    @Override
    @CacheEvict(value = "produtos", key = "#id")
    public void alterarDisponibilidade(Long id, boolean disponivel) {
        Produto p = buscarPorId(id);
        p.setDisponivel(disponivel);
        repository.save(p);
    }

    @Override
    @Cacheable(value = "produtos", key = "'cat:' + #categoria + ':' + #pageable.pageNumber")
    public Page<Produto> listarPorCategoria(String categoria, Pageable pageable) {
        return repository.findByCategoria(categoria, pageable);
    }

    @Override
    @Cacheable(value = "produtos", key = "'nome:' + #nome")
    public List<Produto> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome);
    }
}
