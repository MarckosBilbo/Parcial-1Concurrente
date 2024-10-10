package org.example.parcial1concurrente.service;

import org.example.parcial1concurrente.model.BolaDTO;
import org.example.parcial1concurrente.model.EstacionDeProduccionDTO;
import org.example.parcial1concurrente.repos.EstacionDeProduccionRepository;
import org.example.parcial1concurrente.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstacionDeProduccionService {

    private final EstacionDeProduccionRepository estacionDeProduccionRepository;

    public EstacionDeProduccionService(final EstacionDeProduccionRepository estacionDeProduccionRepository) {
        this.estacionDeProduccionRepository = estacionDeProduccionRepository;
    }

    public List<EstacionDeProduccionDTO> findAll() {
        return estacionDeProduccionRepository.findAll(Sort.by("id")).stream()
                .map(estacion -> mapToDTO(estacion, new EstacionDeProduccionDTO()))
                .collect(Collectors.toList());
    }

    public EstacionDeProduccionDTO get(final Long id) {
        return estacionDeProduccionRepository.findById(id)
                .map(estacion -> mapToDTO(estacion, new EstacionDeProduccionDTO()))
                .orElseThrow(() -> new NotFoundException("Estación de producción no encontrada"));
    }

    public Long create(final EstacionDeProduccionDTO estacionDTO) {
        EstacionDeProduccion estacion = new EstacionDeProduccion();
        mapToEntity(estacionDTO, estacion);
        return estacionDeProduccionRepository.save(estacion).getId();
    }

    public void update(final Long id, final EstacionDeProduccionDTO estacionDTO) {
        final EstacionDeProduccion estacion = estacionDeProduccionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estación de producción no encontrada"));
        mapToEntity(estacionDTO, estacion);
        estacionDeProduccionRepository.save(estacion);
    }

    public void delete(final Long id) {
        estacionDeProduccionRepository.deleteById(id);
    }

    private EstacionDeProduccionDTO mapToDTO(final EstacionDeProduccion estacion, final EstacionDeProduccionDTO estacionDTO) {
        estacionDTO.setId(estacion.getId());
        estacionDTO.setNombre(estacion.getNombre());
        estacionDTO.setCantidadMaxima(estacion.getCantidadMaxima());
        estacionDTO.setBolasProducidas(estacion.getBolasProducidas().stream()
                .map(bola -> {
                    BolaDTO bolaDTO = new BolaDTO();
                    bolaDTO.setId(bola.getId());
                    bolaDTO.setColor(bola.getColor());
                    bolaDTO.setPosicion(bola.getPosicion());
                    return bolaDTO;
                }).collect(Collectors.toList()));
        return estacionDTO;
    }

    private EstacionDeProduccion mapToEntity(final EstacionDeProduccionDTO estacionDTO, final EstacionDeProduccion estacion) {
        estacion.setNombre(estacionDTO.getNombre());
        estacion.setCantidadMaxima(estacionDTO.getCantidadMaxima());
        estacion.setBolasProducidas(estacionDTO.getBolasProducidas().stream()
                .map(bolaDTO -> {
                    Bola bola = new Bola();
                    bola.setId(bolaDTO.getId());
                    bola.setColor(bolaDTO.getColor());
                    bola.setPosicion(bolaDTO.getPosicion());
                    return bola;
                }).collect(Collectors.toList()));
        return estacion;
    }
}