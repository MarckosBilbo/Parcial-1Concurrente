package org.example.parcial1concurrente.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.parcial1concurrente.model.EstacionDeProduccionDTO;
import org.example.parcial1concurrente.service.EstacionDeProduccionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estaciones")
public class EstacionDeProduccionResource {

    private final EstacionDeProduccionService estacionDeProduccionService;

    public EstacionDeProduccionResource(final EstacionDeProduccionService estacionDeProduccionService) {
        this.estacionDeProduccionService = estacionDeProduccionService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Get all estaciones")
    public ResponseEntity<List<EstacionDeProduccionDTO>> getAllEstaciones() {
        return ResponseEntity.ok(estacionDeProduccionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstacionDeProduccionDTO> getEstacion(@PathVariable final Long id) {
        EstacionDeProduccionDTO estacion = estacionDeProduccionService.get(id);
        return ResponseEntity.ok(estacion);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a new estacion")
    public ResponseEntity<Long> createEstacion(@RequestBody final EstacionDeProduccionDTO estacionDTO) {
        Long createdId = estacionDeProduccionService.create(estacionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstacion(@PathVariable final Long id,
                                               @RequestBody final EstacionDeProduccionDTO estacionDTO) {
        estacionDeProduccionService.update(id, estacionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Delete an estacion")
    public ResponseEntity<Void> deleteEstacion(@PathVariable final Long id) {
        estacionDeProduccionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}