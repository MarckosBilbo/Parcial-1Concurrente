package org.example.parcial1concurrente.service;

import org.example.parcial1concurrente.repos.MaquinaRepository;
import org.example.parcial1concurrente.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaquinaService {

    private final MaquinaRepository maquinaRepository;

    public MaquinaService(final MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    public List<MaquinaDTO> findAll() {
        return maquinaRepository.findAll(Sort.by("id")).stream()
                .map(maquina -> mapToDTO(maquina, new MaquinaDTO()))
                .collect(Collectors.toList());
    }

    public MaquinaDTO get(final Long id) {
        return maquinaRepository.findById(id)
                .map(maquina -> mapToDTO(maquina, new MaquinaDTO()))
                .orElseThrow(() -> new NotFoundException("Maquina no encontrada"));
    }

    public Long create(final MaquinaDTO maquinaDTO) {
        Maquina maquina = new Maquina();
        mapToEntity(maquinaDTO, maquina);
        return maquinaRepository.save(maquina).getId();
    }

    public void update(final Long id, final MaquinaDTO maquinaDTO) {
        final Maquina maquina = maquinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Maquina no encontrada"));
        mapToEntity(maquinaDTO, maquina);
        maquinaRepository.save(maquina);
    }

    public void delete(final Long id) {
        maquinaRepository.deleteById(id);
    }

    private MaquinaDTO mapToDTO(final Maquina maquina, final MaquinaDTO maquinaDTO) {
        maquinaDTO.setId(maquina.getId());
        maquinaDTO.setNombre(maquina.getNombre());
        // Assuming you want to map the list of BolaDTOs as well
        maquinaDTO.setBolas(maquina.getBolas().stream()
                .map(bola -> {
                    BolaDTO bolaDTO = new BolaDTO();
                    bolaDTO.setId(bola.getId());
                    bolaDTO.setColor(bola.getColor());
                    bolaDTO.setPosicion(bola.getPosicion());
                    return bolaDTO;
                }).collect(Collectors.toList()));
        return maquinaDTO;
    }

    private Maquina mapToEntity(final MaquinaDTO maquinaDTO, final Maquina maquina) {
        maquina.setNombre(maquinaDTO.getNombre());
        // Assuming you want to map the list of Bolas as well
        maquina.setBolas(maquinaDTO.getBolas().stream()
                .map(bolaDTO -> {
                    Bola bola = new Bola();
                    bola.setId(bolaDTO.getId());
                    bola.setColor(bolaDTO.getColor());
                    bola.setPosicion(bolaDTO.getPosicion());
                    return bola;
                }).collect(Collectors.toList()));
        return maquina;
    }
}