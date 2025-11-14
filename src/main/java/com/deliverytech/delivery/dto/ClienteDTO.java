package com.deliverytech.delivery.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteDTO {
    @NotBlank @Size(max = 100)
    private String nome;
    @Email @NotBlank
    private String email;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;
    // getters / setters
    public String getNome() { return nome; } public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; } public void setTelefone(String telefone) { this.telefone = telefone; }
    public String getEndereco() { return endereco; } public void setEndereco(String endereco) { this.endereco = endereco; }
}
