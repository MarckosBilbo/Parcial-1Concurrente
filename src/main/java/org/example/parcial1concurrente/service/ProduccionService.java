package org.example.parcial1concurrente.service;

import org.example.parcial1concurrente.domain.Componente;
import org.example.parcial1concurrente.repos.ComponenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class ProduccionService {
    private final ComponenteRepository componenteRepository;
    private final BlockingQueue<Componente> buffer = new LinkedBlockingQueue<>();

    // Constructor que inyecta el repositorio de componentes.
    @Autowired
    public ProduccionService(ComponenteRepository componenteRepository) {
        this.componenteRepository = componenteRepository;
    }

    // Produce un nuevo componente con el nombre dado y lo guarda en el repositorio y en el buffer.
    public void producirComponente(String nombre) {
        Componente componente = new Componente();
        componente.setNombre(nombre);
        componenteRepository.save(componente);
        buffer.add(componente);
        System.out.println(nombre + " producido.");
    }

    // Consume un componente del buffer de manera bloqueante.
    public Componente consumirComponente() throws InterruptedException {
        return buffer.take();
    }

    // Produce un nuevo componente de manera asíncrona.
    @Async
    public void producirComponenteAsync(String nombre) {
        producirComponente(nombre);
    }

    // Ensambla un componente de manera asíncrona.
    @Async
    public void ensamblarComponenteAsync() throws InterruptedException {
        Componente componente = consumirComponente();
        System.out.println("Ensamblando " + componente.getNombre());
    }
}

