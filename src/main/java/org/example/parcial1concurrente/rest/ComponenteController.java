package org.example.parcial1concurrente.rest;

import org.example.parcial1concurrente.domain.Componente;
import org.example.parcial1concurrente.domain.Bola;
import org.example.parcial1concurrente.repos.ComponenteRepository;
import org.example.parcial1concurrente.repos.BolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/componentes")
public class ComponenteController {
    // Repositorio de componentes
    private final ComponenteRepository componenteRepository;
    // Repositorio de bolas
    private final BolaRepository bolaRepository;

    // Constructor con inyección de dependencias
    @Autowired
    public ComponenteController(ComponenteRepository componenteRepository, BolaRepository bolaRepository) {
        this.componenteRepository = componenteRepository;
        this.bolaRepository = bolaRepository;
    }

    // Endpoint para obtener componentes de tipo "Clavo"
    @GetMapping("/clavos")
    public List<Componente> getClavos() {
        return componenteRepository.findByTipo("Clavo");
    }

    // Endpoint para obtener componentes de tipo "Contenedor"
    @GetMapping("/contenedores")
    public List<Componente> getContenedores() {
        return componenteRepository.findByTipo("Contenedor");
    }

    // Endpoint para obtener todas las bolas
    @GetMapping("/bolas")
    public List<Bola> getBolas() {
        return bolaRepository.findAll();
    }
}