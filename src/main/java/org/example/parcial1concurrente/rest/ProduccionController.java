package org.example.parcial1concurrente.rest;

import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/produccion")
public class ProduccionController {
    // Inyección de dependencias del servicio de producción
    private final ProduccionService produccionService;

    // Constructor con inyección de dependencias
    @Autowired
    public ProduccionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    // Endpoint para iniciar la producción de componentes
    @GetMapping("/producir")
    public String producir() {
        produccionService.producirComponentes();
        return "Producción iniciada.";
    }

    // Endpoint para iniciar el ensamblaje de la máquina
    @GetMapping("/ensamblar")
    public String ensamblar() throws InterruptedException {
        produccionService.ensamblarMaquina();
        return "Ensamblaje iniciado.";
    }

    // Endpoint para asignar valores de distribución
    @GetMapping("/asignar-valores")
    public String asignarValores() throws IOException, InterruptedException {
        produccionService.asignarValoresDistribucion();
        return "Asignación de valores de distribución iniciada.";
    }
}