package br.edu.ifsc.BancoCentral.repository;

import br.edu.ifsc.BancoCentral.model.ModeloTeste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TesteRepository extends JpaRepository<ModeloTeste, Long> {
}
