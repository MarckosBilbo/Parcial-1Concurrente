package org.example.parcial1concurrente.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.parcial1concurrente.service.MaquinaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/maquinas")
public class MaquinaResource {

    private final MaquinaService maquinaService;

    public MaquinaResource(final MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Get all maquinas")
    public ResponseEntity<List<MaquinaDTO>> getAllMaquinas() {
        return ResponseEntity.ok(maquinaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaquinaDTO> getMaquina(@PathVariable final Long id) {
        MaquinaDTO maquina = maquinaService.get(id);
        return ResponseEntity.ok(maquina);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a new maquina")
    public ResponseEntity<Long> createMaquina(@RequestBody final MaquinaDTO maquinaDTO) {
        Long createdId = maquinaService.create(maquinaDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMaquina(@PathVariable final Long id,
                                              @RequestBody final MaquinaDTO maquinaDTO) {
        maquinaService.update(id, maquinaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204", description = "Delete a maquina")
    public ResponseEntity<Void> deleteMaquina(@PathVariable final Long id) {
        maquinaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}