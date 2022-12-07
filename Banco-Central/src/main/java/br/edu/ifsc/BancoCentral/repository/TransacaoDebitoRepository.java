package br.edu.ifsc.BancoCentral.repository;

import br.edu.ifsc.BancoCentral.model.entity.TransacaoDebitoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoDebitoRepository extends JpaRepository<TransacaoDebitoEntity, UUID> {
}
