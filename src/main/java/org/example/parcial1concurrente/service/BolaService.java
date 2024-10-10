package org.example.parcial1concurrente.service;

import org.example.parcial1concurrente.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BolaService {

    private final BolaRepository bolaRepository;
    private final EstacionDeProduccionRepository estacionDeProduccionRepository;
    private final MaquinaRepository maquinaRepository;

    public BolaService(final BolaRepository bolaRepository,
                       final EstacionDeProduccionRepository estacionDeProduccionRepository,
                       final MaquinaRepository maquinaRepository) {
        this.bolaRepository = bolaRepository;
        this.estacionDeProduccionRepository = estacionDeProduccionRepository;
        this.maquinaRepository = maquinaRepository;
    }

    public List<BolaDTO> findAll() {
        return bolaRepository.findAll(Sort.by("id")).stream()
                .map(bola -> mapToDTO(bola, new BolaDTO()))
                .collect(Collectors.toList());
    }

    public BolaDTO get(final Long id) {
        return bolaRepository.findById(id)
                .map(bola -> mapToDTO(bola, new BolaDTO()))
                .orElseThrow(() -> new NotFoundException("Bola no encontrada"));
    }

    public Long create(final BolaDTO bolaDTO) {
        Bola bola = new Bola();
        mapToEntity(bolaDTO, bola);
        return bolaRepository.save(bola).getId();
    }

    public void update(final Long id, final BolaDTO bolaDTO) {
        final Bola bola = bolaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bola no encontrada"));
        mapToEntity(bolaDTO, bola);
        bolaRepository.save(bola);
    }

    public void delete(final Long id) {
        bolaRepository.deleteById(id);
    }

    private BolaDTO mapToDTO(final Bola bola, final BolaDTO bolaDTO) {
        bolaDTO.setId(bola.getId());
        bolaDTO.setColor(bola.getColor());
        bolaDTO.setEstacionDeProduccionId(bola.getEstacionDeProduccion() == null ? null : bola.getEstacionDeProduccion().getId());
        bolaDTO.setMaquinaId(bola.getMaquina() == null ? null : bola.getMaquina().getId());
        bolaDTO.setPosicion(bola.getPosicion());
        return bolaDTO;
    }

    private Bola mapToEntity(final BolaDTO bolaDTO, final Bola bola) {
        bola.setColor(bolaDTO.getColor());

        final EstacionDeProduccion estacionDeProduccion = bolaDTO.getEstacionDeProduccionId() == null
                ? null
                : estacionDeProduccionRepository.findById(bolaDTO.getEstacionDeProduccionId())
                .orElseThrow(() -> new NotFoundException("Estación de producción no encontrada"));
        bola.setEstacionDeProduccion(estacionDeProduccion);

        final Maquina maquina = bolaDTO.getMaquinaId() == null
                ? null
                : maquinaRepository.findById(bolaDTO.getMaquinaId())
                .orElseThrow(() -> new NotFoundException("Máquina no encontrada"));
        bola.setMaquina(maquina);

        bola.setPosicion(bolaDTO.getPosicion());
        return bola;
    }
}