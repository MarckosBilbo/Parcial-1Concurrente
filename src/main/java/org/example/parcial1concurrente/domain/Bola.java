package org.example.parcial1concurrente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bolas")
@Getter
@Setter
public class Bola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String color;  // Característica adicional (puede cambiarse a otro atributo si es necesario)

    @ManyToOne
    @JoinColumn(name = "estacion_id")
    private EstacionDeProduccion estacionDeProduccion;  // Estación que produjo la bola

    @ManyToOne
    @JoinColumn(name = "maquina_id")
    private Maquina maquina;  // Máquina donde se ensamblará la bola

    // Posición en la máquina (opcional, si se necesita)
    private Integer posicion;
}
