package org.example.parcial1concurrente.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class MaquinaDTO {
    private Long id;

    @NotNull
    @Size(max = 255)
    private String nombre;

    private List<BolaDTO> bolas;
}
