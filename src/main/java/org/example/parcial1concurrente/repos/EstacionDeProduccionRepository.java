package org.example.parcial1concurrente.repos;

import org.example.parcial1concurrente.domain.EstacionDeProduccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionDeProduccionRepository extends JpaRepository<EstacionDeProduccion, Long> {
}