package org.example.parcial1concurrente.repos;

import org.example.parcial1concurrente.domain.Bola;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolaRepository extends JpaRepository<Bola, Long> {
}