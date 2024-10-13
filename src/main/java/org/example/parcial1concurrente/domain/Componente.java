package org.example.parcial1concurrente.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "componentes")
@Getter // Anotación de Lombok para generar los métodos 'getter'.
@Setter // Anotación de Lombok para generar los métodos 'setter'.
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // Campo que representa el ID de la entidad.
    private String tipo; // Campo que representa el tipo del componente.
    private String nombre; // Campo que representa el nombre del componente.
}