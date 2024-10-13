package org.example.parcial1concurrente.repos;

import org.example.parcial1concurrente.domain.Componente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {
    List<Componente> findByTipo(String tipo);
}
