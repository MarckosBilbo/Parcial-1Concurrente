package org.example.parcial1concurrente.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Getter
@Setter
public class EstacionDeProduccionDTO {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    @NotNull
    private int cantidadMaxima;  // Cantidad máxima que puede producir la estación

    private List<BolaDTO> bolasProducidas;  // Lista de las bolas producidas como DTOs
}
