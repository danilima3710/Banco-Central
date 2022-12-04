package br.edu.ifsc.BancoCentral.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Entity
public class Terminal {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String nomeEstabelecimento;
    private String cnpj;
    private String endereco;
    @Value("${some.key:false}")
    private boolean status;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
