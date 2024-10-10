package org.example.parcial1concurrente.repos;

import org.example.parcial1concurrente.domain.Componente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponenteRepository extends JpaRepository<Componente, Long> {
}
