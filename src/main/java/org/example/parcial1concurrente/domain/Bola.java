package org.example.parcial1concurrente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "bolas")
@Getter // Anotación de Lombok para generar los métodos 'getter'.
@Setter // Anotación de Lombok para generar los métodos 'setter'.
public class Bola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Campo que representa el ID de la entidad.
    private String color; // Campo que representa el color de la bola.
    private Double valorDistribucion; // Campo que representa el valor de distribución de la bola.
}