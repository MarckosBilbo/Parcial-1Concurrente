package org.example.parcial1concurrente.repos;

import org.example.parcial1concurrente.domain.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
}