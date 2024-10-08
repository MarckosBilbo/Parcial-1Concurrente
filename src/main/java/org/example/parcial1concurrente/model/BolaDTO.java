package org.example.parcial1concurrente.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class BolaDTO {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String color;

    @NotNull
    private Long estacionDeProduccionId;

    @NotNull
    private Long maquinaId;

    private Integer posicion;  // Posición de la bola en la máquina (opcional)
}
