package org.example.parcial1concurrente.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.parcial1concurrente.model.BolaDTO;
import org.example.parcial1concurrente.service.BolaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/bolas")
public class BolaResource {

    private final BolaService bolaService;

    public BolaResource(final BolaService bolaService) {
        this.bolaService = bolaService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Get all bolas")
    public ResponseEntity<List<BolaDTO>> getAllBolas() {
        return ResponseEntity.ok(bolaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BolaDTO> getBola(@PathVariable final Long id) {
        BolaDTO bola = bolaService.get(id);
        return ResponseEntity.ok(bola);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a new bola")
    public ResponseEntity<Long> createBola(@RequestBody final BolaDTO bolaDTO) {
        Long createdId = bolaService.create(bolaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBola(@PathVariable final Long id,
                                           @RequestBody final BolaDTO bolaDTO) {
        bolaService.update(id, bolaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Delete a bola")
    public ResponseEntity<Void> deleteBola(@PathVariable final Long id) {
        bolaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}