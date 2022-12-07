package br.edu.ifsc.BancoCentral.repository;

import br.edu.ifsc.BancoCentral.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartaoRepository extends JpaRepository<UUID, Cartao> {
}
