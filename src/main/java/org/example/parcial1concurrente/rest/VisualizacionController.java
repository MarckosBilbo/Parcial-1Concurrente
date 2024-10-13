package org.example.parcial1concurrente.rest;

import org.example.parcial1concurrente.domain.Bola;
import org.example.parcial1concurrente.service.ProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/visualizacion")
public class VisualizacionController {
    // Inyección de dependencias del servicio de producción
    private final ProduccionService produccionService;

    // Constructor con inyección de dependencias
    @Autowired
    public VisualizacionController(ProduccionService produccionService) {
        this.produccionService = produccionService;
    }

    // Endpoint para iniciar el streaming de valores de distribución de bolas
    @GetMapping("/stream")
    public SseEmitter stream() {
        // Crear un nuevo SseEmitter para enviar eventos del lado del servidor
        SseEmitter emitter = new SseEmitter();
        // Iniciar un nuevo hilo para consumir bolas y enviar sus valores de distribución
        new Thread(() -> {
            try {
                while (true) {
                    // Consumir una bola del buffer
                    Bola bola = produccionService.consumirBola();
                    // Enviar el valor de distribución de la bola a través del SseEmitter
                    emitter.send(bola.getValorDistribucion());
                }
            } catch (Exception e) {
                // Completar el SseEmitter con un error en caso de excepción
                emitter.completeWithError(e);
            }
        }).start();
        // Devolver el SseEmitter para iniciar el streaming
        return emitter;
    }
}