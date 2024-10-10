package org.example.parcial1concurrente.rest;

import org.example.parcial1concurrente.domain.Componente;
import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

// Controlador REST para manejar la visualización de componentes en tiempo real.
@RestController
public class VisualizacionController {
    private final ProduccionService produccionService;

    // Constructor que inyecta el servicio de producción.
    @Autowired
    public VisualizacionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    // Endpoint para iniciar un stream de Server-Sent Events (SSE) que envía nombres de componentes en tiempo real.
    @GetMapping("/stream")
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        new Thread(() -> {
            try {
                while (true) {
                    // Consume un componente del buffer y envía su nombre a través del SSE.
                    Componente componente = produccionService.consumirComponente();
                    emitter.send(componente.getNombre());
                }
            } catch (Exception e) {
                // Completa el SSE con un error si ocurre una excepción.
                emitter.completeWithError(e);
            }
        }).start();
        return emitter;
    }
}
