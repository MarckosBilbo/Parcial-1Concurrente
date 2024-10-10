package org.example.parcial1concurrente.rest;

import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controlador REST para manejar las solicitudes relacionadas con la producción y ensamblaje de componentes.
@RestController
public class ProduccionController {
    private final ProduccionService produccionService;

    // Constructor que inyecta el servicio de producción.
    @Autowired
    public ProduccionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    // Endpoint para producir un nuevo componente de manera asíncrona.
    @GetMapping("/producir/{nombre}")
    public String producir(@PathVariable String nombre) {
        produccionService.producirComponenteAsync(nombre);
        return "Componente " + nombre + " producido.";
    }

    // Endpoint para ensamblar un componente de manera asíncrona.
    @GetMapping("/ensamblar")
    public String ensamblar() throws InterruptedException {
        produccionService.ensamblarComponenteAsync();
        return "Ensamblando componente.";
    }
}
